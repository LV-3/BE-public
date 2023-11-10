package com.example.VodReco.controller;

import com.example.VodReco.dto.*;
import com.example.VodReco.jwt.TokenProvider;
import com.example.VodReco.service.RatingServiceImpl;
import com.example.VodReco.service.VodServiceImpl;
import com.example.VodReco.service.WishServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//Response <-> Request 컨트롤러 분리 고려

@RestController // = @Controller + @ResponseBody 객체 리턴하면 json으로 만들어줌
@RequestMapping("/vods")
@Tag(name = "Vod", description = "Vod API") //swagger 어노테이션(231110)
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


    //vod별 상세페이지
    //테스트 시 실제DB에 들어있는 vcode 가져올 것

    //리턴 타입 json 매핑 가능하게 바꾸기. 논의 필요(231102)
    @GetMapping(value = "/{content_id}")
    @Operation(summary = "vod별 상세페이지", description = "클라이언트가 보내는 content_id에 해당하는 vod의 상세페이지")
    public VodDto vodDetail(@PathVariable("content_id") String contentId) {//변하는 값을 얻을 때는 @PathVariable 써야함
        return this.vodServiceImpl.getVodByContentId(contentId);
    }



    //찜: 1
    //평점: 1~5

    //wish
    @PostMapping(value = "/{content_id}/wish")
    @Operation(summary = "vod별 찜", description = "클라이언트가 보내는 content_id에 해당하는 vod의 상세페이지에서 찜하기")
    //@RequestBody가 들어오는 json데이터 미리 선언한 엔터티 객체로 매핑해서 들어오게 해줌
    //token의 payload에서 현재 접속 중인 user의 email 추출해서 wishResponseDto 내부 email필드에 주입 -> 구현 성공(231031)
    //PathVariable 쓰면서 requestDto에 content_id 안 담아오는 경우 메인페이지에서도 dto를 쓰는데 여기서는 content_id를 받아올 수 없어서 -> requestDto 2개에 content_id 필드 추가함(231104)
    public WishResponseDto wish(@PathVariable("content_id")
                                    @Schema(description = "content_id", example = "20200622")
                                    String contentId,
                                @Parameter(name = "content_id", description = "컨텐츠 고유id", example = "20200622", required = true)
                                @RequestBody WishRequestFromMyDto wishRequestFromMyDto, ServletRequest servletRequest)
            throws ServletException, IOException {
        WishResponseDto wishResponseDto = WishResponseDto.builder().email(tokenProvider.getEmailFromToken(servletRequest))
                .contentId(contentId).wish(wishRequestFromMyDto.getWish()).build();
//            확인
        System.out.println("찜 = " + wishResponseDto.getWish());

        wishServiceImpl.saveWish(wishResponseDto.toWishEntity(wishResponseDto));
//        API 테스트용 리턴
        return this.wishServiceImpl.findUserWishByContentId(contentId);
    }


    //rating
    @PostMapping(value = "/{content_id}/rating")
    @Operation(summary = "vod별 평점", description = "클라이언트가 보내는 content_id에 해당하는 vod의 상세페이지에서 평점 매기기")
    //@RequestBody가 들어오는 json데이터 미리 선언한 엔터티 객체로 매핑해서 들어오게 해줌
    //{"rating":1~5} 형식으로 데이터 받아서 vodDetailRating객체로 받기(내부 필드 rating)
    //! vcode까지 프론트에서 받을지 or 서버 거 갖다쓸지 논의 필요

    //token의 payload에서 현재 접속 중인 user의 email 추출해서 ratingResponseDto 내부 email필드에 주입
    //구현 성공(231031)
    public RatingResponseDto rating(@PathVariable("content_id") String contentId, @RequestBody RatingRequestFromMyDto ratingRequestFromMyDto, ServletRequest servletRequest)
            throws ServletException, IOException {
        RatingResponseDto ratingResponseDto = RatingResponseDto.builder().email(tokenProvider.getEmailFromToken(servletRequest))
                .contentId(contentId).rating(ratingRequestFromMyDto.getRating()).comment(ratingRequestFromMyDto.getComment()).build();
//            확인
        System.out.println("평점 = " + ratingResponseDto.getRating());
        ratingServiceImpl.saveRating(ratingResponseDto.toRatingEntity(ratingResponseDto));
//        API 테스트용 리턴
        return this.ratingServiceImpl.findUserRatingByContentId(contentId);

    }
}

