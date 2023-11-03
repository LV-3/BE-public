package com.example.VodReco.controller;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.*;
import com.example.VodReco.jwt.TokenProvider;
import com.example.VodReco.service.RatingServiceImpl;
import com.example.VodReco.service.VodServiceImpl;
import com.example.VodReco.service.WishServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//Response <-> Request 컨트롤러 분리 고려

@RestController // = @Controller + @ResponseBody 객체 리턴하면 json으로 만들어줌
@RequestMapping("/vods")
public class VodController {
    private final VodServiceImpl vodServiceImpl;
    private final WishServiceImpl wishServiceImpl;
    private final RatingServiceImpl ratingServiceImpl;
    private final TokenProvider tokenProvider;


    @Autowired
    public VodController(VodServiceImpl vodServiceImpl, WishServiceImpl wishServiceImpl, RatingServiceImpl ratingServiceImpl, TokenProvider tokenProvider) {
        this.vodServiceImpl = vodServiceImpl;
        this.wishServiceImpl = wishServiceImpl;
        this.ratingServiceImpl = ratingServiceImpl;
        this.tokenProvider = tokenProvider;
    }

    //메인화면(포스터) : 보류 -> 모델로부터 받아와야 함, MainController에 구현 예정(231101)
//    @GetMapping("/posters")
//    public List<String> displayVods() {
//        return vodService.getAllPosterUrls();
//    }


    //vod별 상세페이지
    //테스트 시 실제DB에 들어있는 vcode 가져올 것

    //리턴 타입 json 매핑 가능하게 바꾸기. 논의 필요(231102)
    @GetMapping(value = "/{vcode}")
    public VodDto vodDetail(@PathVariable("vcode") String vcode) {//변하는 값을 얻을 때는 @PathVariable 써야함
        return this.vodServiceImpl.getVod(vcode);

    }



    //찜: 1
    //평점: 1~5

    //wish
    @PostMapping(value = "/{vcode}/wish")
    //@RequestBody가 들어오는 json데이터 미리 선언한 엔터티 객체로 매핑해서 들어오게 해줌
    //{"wish":1} 형식으로 데이터 받아서 vodDetailWish객체로 받기(내부 필드 wish)

    //token의 payload에서 현재 접속 중인 user의 email 추출해서 wishResponseDto 내부 email필드에 주입
    //구현 성공(231031)
    public WishResponseDto wish(@PathVariable("vcode") String vcode, @RequestBody WishRequestDto wishRequestDto, ServletRequest servletRequest)
            throws ServletException, IOException {
        WishResponseDto wishResponseDto = WishResponseDto.builder().email(tokenProvider.getEmailFromToken(servletRequest))
                .vcode(vcode).wish(wishRequestDto.getWish()).build();
//            확인
        System.out.println("찜 = " + wishResponseDto.getWish());

        wishServiceImpl.saveWish(wishResponseDto.toWishEntity(wishResponseDto));
//        API 테스트용 리턴
        return this.wishServiceImpl.findUserWishByVcode(vcode);
    }


    //rating
    @PostMapping(value = "/{vcode}/rating")
    //@RequestBody가 들어오는 json데이터 미리 선언한 엔터티 객체로 매핑해서 들어오게 해줌
    //{"rating":1~5} 형식으로 데이터 받아서 vodDetailRating객체로 받기(내부 필드 rating)
    //! vcode까지 프론트에서 받을지 or 서버 거 갖다쓸지 논의 필요

    //token의 payload에서 현재 접속 중인 user의 email 추출해서 ratingResponseDto 내부 email필드에 주입
    //구현 성공(231031)
    public RatingResponseDto rating(@PathVariable("vcode") String vcode, @RequestBody RatingRequestDto ratingRequestDto, ServletRequest servletRequest)
            throws ServletException, IOException {
        RatingResponseDto ratingResponseDto = RatingResponseDto.builder().email(tokenProvider.getEmailFromToken(servletRequest))
                .vcode(vcode).rating(ratingRequestDto.getRating()).build();
//            확인
        System.out.println("찜 = " + ratingResponseDto.getRating());
        ratingServiceImpl.saveRating(ratingResponseDto.toRatingEntity(ratingResponseDto));
//        API 테스트용 리턴
        return this.ratingServiceImpl.findUserRatingByVcode(vcode);

    }
}

