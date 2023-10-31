package com.example.VodReco.controller;

import com.example.VodReco.dto.*;
import com.example.VodReco.service.RatingServiceImpl;
import com.example.VodReco.service.VodServiceImpl;
import com.example.VodReco.service.WishServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//Response <-> Request 컨트롤러 분리 고려

@RestController // = @Controller + @ResponseBody 객체 리턴하면 json으로 만들어줌
@RequestMapping("/vods")
public class VodController {
    private final VodServiceImpl vodServiceImpl;
    private final WishServiceImpl wishServiceImpl;
    private final RatingServiceImpl ratingServiceImpl;


    @Autowired
    public VodController(VodServiceImpl vodServiceImpl, WishServiceImpl wishServiceImpl, RatingServiceImpl ratingServiceImpl) {
        this.vodServiceImpl = vodServiceImpl;
        this.wishServiceImpl = wishServiceImpl;
        this.ratingServiceImpl = ratingServiceImpl;
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
        return this.vodServiceImpl.getVod(vcode);

    }



    //상세페이지 click 시 유저정보 조회
    //로그인 구현 이후 처리하기


    //찜: 1
    //평점: 1~5

    //wish
    @PostMapping(value = "/{vcode}/wish")
    //@RequestBody가 들어오는 json데이터 미리 선언한 엔터티 객체로 매핑해서 들어오게 해줌
    //{"wish":1} 형식으로 데이터 받아서 vodDetailWish객체로 받기(내부 필드 wish)
    public WishResponseDto wish(@PathVariable("vcode") String vcode, @RequestBody WishRequestDto wishRequestDto) {
        WishResponseDto wishResponseDto = WishResponseDto.builder().email("1@1.com").vcode(vcode).wish(wishRequestDto.getWish()).build();
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
    public RatingResponseDto rating(@PathVariable("vcode") String vcode, @RequestBody RatingRequestDto ratingRequestDto) {
        RatingResponseDto ratingResponseDto = RatingResponseDto.builder().email("1@2.com")
                .vcode(vcode).rating(ratingRequestDto.getRating()).build();
//            확인
        System.out.println("찜 = " + ratingResponseDto.getRating());
        ratingServiceImpl.saveRating(ratingResponseDto.toRatingEntity(ratingResponseDto));
//        API 테스트용 리턴
        return this.ratingServiceImpl.findUserRatingByVcode(vcode);

    }
}

