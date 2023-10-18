package com.example.VodReco.controller;

import com.example.VodReco.domain.*;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.service.RatingServiceImpl;
import com.example.VodReco.service.VodServiceImpl;
import com.example.VodReco.service.WishServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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


//Response <-> Request 컨트롤러 분리 고려

@RestController // = @Controller + @ResponseBody 객체 리턴하면 json으로 만들어줌
@RequestMapping("/vods")
public class VodController {
    private final VodServiceImpl vodService;
    private final WishServiceImpl wishService;
    private final RatingServiceImpl ratingService;


    @Autowired
    public VodController(VodServiceImpl vodService, WishServiceImpl wishService, RatingServiceImpl ratingService) {
        this.vodService = vodService;
        this.wishService = wishService;
        this.ratingService = ratingService;
    }

    //메인화면(포스터) : 보류
//    @GetMapping("/posters")
//    public List<String> displayVods() {
//        return vodService.getAllPosterUrls();
//    }

    //vod별 상세페이지
    //테스트 시 실제DB에 들어있는 vcode 가져올 것
    @GetMapping(value = "/{vcode}")
    public VodDto vodDetail(@PathVariable("vcode") String vcode) {//변하는 값을 얻을 때는 @PathVariable 써야함
        return this.vodService.getVod(vcode);

    }


    //상세페이지 click 시 유저정보 조회
    //로그인 구현 이후



    //찜: 1
    //평점: 1~5

    //wish
    //테스트 완료
    @PostMapping(value = "/{vcode}/wish")
    //@RequestBody가 들어오는 json데이터 미리 선언한 엔터티 객체로 매핑해서 들어오게 해줌
    //{"wish":1} 형식으로 데이터 받아서 vodDetailWish객체로 받기(내부 필드 wish)
    public UserWish wish(@PathVariable("vcode") String vcode, @RequestBody VodDetailWish vodDetailWish, HttpServletRequest request) {
        UserWish userWish = UserWish.builder().userEmail("1@1.com").vcode(vcode).wish(vodDetailWish.getWish()).build();
//            확인
        System.out.println("찜 = " + userWish.getWish());
        wishService.saveWish(userWish);
//        API 테스트용 리턴
        return userWish;
    }


    //rating
    //테스트 완료
    @PostMapping(value = "/{vcode}/rating")
    //@RequestBody가 들어오는 json데이터 미리 선언한 엔터티 객체로 매핑해서 들어오게 해줌
    //{"rating":1~5} 형식으로 데이터 받아서 vodDetailRating객체로 받기(내부 필드 rating)
    //! vcode까지 프론트에서 받을지 or 서버 거 갖다쓸지 논의 필요
    public UserRating rating(@PathVariable("vcode") String vcode, @RequestBody VodDetailRating vodDetailRating, HttpServletRequest request) {
        UserRating userRating = UserRating.builder().userEmail("1@1.com")
                .vcode(vcode).rating(vodDetailRating.getRating()).build();
//            확인
        System.out.println("찜 = " + userRating.getRating());
        ratingService.saveRating(userRating);
//        API 테스트용 리턴
        return userRating;

    }
}

