package com.example.VodReco.controller;

import com.example.VodReco.domain.CloseVodDetail;
import com.example.VodReco.domain.Vod;
import com.example.VodReco.domain.WishRating;
import com.example.VodReco.service.VodService;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


// 1. view

//@Controller
//public class MovieController {
//
//    private final MovieService movieService;
//
//    @Autowired
//    public MovieController(MovieService movieService) {
//        this.movieService = movieService;
//    }

//    @GetMapping("/movies/posters") // 포스터
//    public String displayMovies(Model model) {
//        List<Movie> movies = movieService.getAllMovies();
//        model.addAttribute("movies", movies);
//        return "movies/poster";
//    }
//
//    //상세페이지
//    @GetMapping(value = "/movies/{vcode}") //title? vcode?
//    public String detail(Model model, @PathVariable("vcode") String vcode){//변하는 id값을 얻을 때는 @PathVariable 써야함
//
//        Movie movie = this.movieService.getMovie(mcode);
//        model.addAttribute("movie", movie);
//        return "movies/movieDetail";
//
//    }
//}


// 2. API

@RestController
@RequestMapping("/vods")
public class VodController {
    private final VodService vodService;

    @Autowired
    public VodController(VodService vodService) {
        this.vodService = vodService;
    }

    //메인화면(포스터)
    @GetMapping("/posters")
    public List<String> displayVods() {
        return vodService.getAllPosterUrls();
    }

    //vod별 상세페이지
    @GetMapping(value = "/{vcode}")
    public Vod vodDetail(@PathVariable("vcode") String mcode) {//변하는 값을 얻을 때는 @PathVariable 써야함
        return this.vodService.getVod(mcode);

    }


    //기본값 false, 찜 누르면 true
    //기본값 0, 평점 매기면 1~5
    //{"wish":true} vcode는 이쪽에 있어서 안 받아도 됨
    //{"rating":1~5}
    @PostMapping(value = "/{vcode}/close")
    // 프론트에서 localstorage(sessionstorage?)에 저장한 뒤 close이벤트 시 전달받기
    public void wishRating(@PathVariable("vcode") String vcode, @RequestBody CloseVodDetail closeVodDetail, HttpServletRequest request) {
        WishRating wishRating = new WishRating();

        if (closeVodDetail.getWish() || closeVodDetail.getRating() != 0) {
            wishRating.setVcode(vcode);
            wishRating.setWish(1);
            wishRating.setRating(closeVodDetail.getRating());
//            확인
            System.out.println("평점 = " + wishRating.getRating());
            wishRating.setUserEmail("1@1.com");
            vodService.saveWishRating(wishRating);
            //session에서 email 꺼내오기
//            HttpServletRequest session = (HttpServletRequest) request.getSession(false);
//            if (session != null) {
//                String useremail = (String) session.getAttribute("useremail");
//                if (useremail != null) {
//                    wishRating.setUserEmail(useremail);
//                    vodService.saveWishRating(wishRating);
//
//                }
//            }
            //문제점: 평점은 0, 찜은 false가 기본값인데 둘 중 하나만 하고 나가버리면 평점이 0으로 들어가는 거 아닌가?
            //알아서 0과 false는 빼고 집어넣겠지??
        }
    }
}