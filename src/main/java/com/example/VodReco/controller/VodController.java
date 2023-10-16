package com.example.VodReco.controller;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.domain.UserWish;
import com.example.VodReco.domain.VodDetailWish;
import com.example.VodReco.service.VodService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


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



    //찜 <-> 평점 분리
    // 엔드포인트 2개 따로 postmapping해서 각기 다른 repository에 저장, 테이블 분리


    //찜: 1
    //평점: 1~5

    //wish
    //테스트 완료
    @PostMapping(value = "/{vcode}/wish")
    //@RequestBody가 들어오는 json데이터 미리 선언한 엔터티 객체로 매핑해서 들어오게 해줌
    //{"vcode":"20220620", "wish":1} 형식으로 데이터 받아서 vodDetailWish객체로 받기(내부 필드 vcode, wish)
    public UserWish wish(@PathVariable("vcode") String vcode, @RequestBody VodDetailWish vodDetailWish, HttpServletRequest request) {
        UserWish userWish = UserWish.builder().userEmail("1@1.com").vcode(vodDetailWish.getVcode()).wish(vodDetailWish.getWish()).build();
//            확인
        System.out.println("찜 = " + userWish.getWish());
        vodService.saveWish(userWish);
//        API 테스트용 리턴
//        return userWish;

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

//    @PostMapping(value = "/{vcode}/close")
//    // 프론트에서 localstorage(sessionstorage?)에 저장한 뒤 close이벤트 시 전달받기
//    public void wishRating(@PathVariable("vcode") String vcode, @RequestBody CloseVodDetail closeVodDetail, HttpServletRequest request) {
//        WishRating wishRating = new WishRating();
//
//        if (closeVodDetail.getWish() || closeVodDetail.getRating() != 0) {
//            wishRating.setVcode(vcode);
//            wishRating.setWish(1);
//            wishRating.setRating(closeVodDetail.getRating());
////            확인
//            System.out.println("평점 = " + wishRating.getRating());
//            wishRating.setUserEmail("1@1.com");
//            vodService.saveWishRating(wishRating);
//            //session에서 email 꺼내오기
////            HttpServletRequest session = (HttpServletRequest) request.getSession(false);
////            if (session != null) {
////                String useremail = (String) session.getAttribute("useremail");
////                if (useremail != null) {
////                    wishRating.setUserEmail(useremail);
////                    vodService.saveWishRating(wishRating);
////
////                }
////            }

        }

