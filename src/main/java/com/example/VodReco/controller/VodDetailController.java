package com.example.VodReco.controller;

import com.example.VodReco.dto.rating.RatingRequestDto;
import com.example.VodReco.dto.rating.RatingResponseDto;
import com.example.VodReco.dto.vodDetail.VodDetailResponseDto;
import com.example.VodReco.dto.wish.UpdateMyWishRequestDto;
import com.example.VodReco.dto.wish.UpdateMyWishResponseDto;
import com.example.VodReco.dto.wish.ViewMyWishRequestDto;
import com.example.VodReco.service.VodServiceImpl;
import com.example.VodReco.service.vodDetailPage.updateMyWish.UserWishUpdateMyWishServiceImpl;
import com.example.VodReco.service.vodDetailPage.viewDetailInfo.VodViewDetailInfoServiceImpl;
import com.example.VodReco.service.vodDetailPage.viewMyWish.UserWishViewMyWishServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



//vod별 상세페이지

@RestController // = @Controller + @ResponseBody 객체 리턴하면 json으로 만들어줌
@RequestMapping("/detail")
@Tag(name = "Vod detail", description = "Vod API detail") //swagger 어노테이션(231110)
public class VodDetailController {
    private final VodServiceImpl vodServiceImpl;
    private final VodViewDetailInfoServiceImpl vodViewDetailInfoService;
    private final UserWishViewMyWishServiceImpl userWishViewMyWishService;
    private final UserWishUpdateMyWishServiceImpl userWishUpdateMyWishService;


    @Autowired
    public VodDetailController(VodServiceImpl vodServiceImpl, VodViewDetailInfoServiceImpl vodViewDetailInfoService, UserWishViewMyWishServiceImpl userWishViewMyWishService, UserWishUpdateMyWishServiceImpl userWishUpdateMyWishService) {
        this.vodServiceImpl = vodServiceImpl;
        this.vodViewDetailInfoService = vodViewDetailInfoService;
        this.userWishViewMyWishService = userWishViewMyWishService;
        this.userWishUpdateMyWishService = userWishUpdateMyWishService;
    }


    //테스트 시 실제DB에 들어있는 content_id 가져올 것

    //상세페이지 열릴 때

    //1. 기본정보
    @GetMapping(value = "/{content_id}")
    @Operation(summary = "vod별 상세페이지", description = "url에 있는 content_id 해당 vod의 상세페이지")
    public VodDetailResponseDto getVodDetail(@PathVariable("content_id") String contentId) {//변하는 값을 얻을 때는 @PathVariable 써야함
        return this.vodViewDetailInfoService.getVodByContentId(contentId);
    }



    //2. 기존 wish 조회

    @GetMapping(value = "/{content_id}/wish")
    @Operation(summary = "vod별 찜 조회", description = "상세페이지 열릴 때 해당 사용자의 wish 있는지 DB 조회")
    public Integer wish(@PathVariable("content_id")
                                @Schema(description = "content_id", example = "20200622")
                                String contentId,
                                @Parameter(name = "content_id", description = "컨텐츠 고유id", example = "20200622", required = true)
                                @RequestBody ViewMyWishRequestDto viewMyWishRequestDto) {
        UpdateMyWishResponseDto updateMyWishResponseDto = userWishViewMyWishService.findMyWish(viewMyWishRequestDto.getSubsr(), contentId);
//            콘솔 확인
        System.out.println("콘솔 확인용 = " + updateMyWishResponseDto);
        if (updateMyWishResponseDto == null) {
            return null;
        }
        return updateMyWishResponseDto.getWish();
    }

//    4. wish 변경
//    @PostMapping(value = "/{content_id}/wish")
//    @Operation(summary = "vod별 찜 수정", description = "상세페이지에서 wish 변경")
//    public UpdateMyWishResponseDto wish(@PathVariable("content_id")
//                                    @Schema(description = "content_id", example = "20200622")
//                                    String contentId,
//                                        @Parameter(name = "content_id", description = "컨텐츠 고유id", example = "20200622", required = true)
//                                @RequestBody UpdateMyWishRequestDto updateMyWishRequestDto) {
//        UpdateMyWishResponseDto updateMyWishResponseDto = UpdateMyWishResponseDto.builder().subsr(updateMyWishRequestDto.getSubsr())
//                .contentId(contentId).wish(updateMyWishRequestDto.getWish()).build();
////            확인
//        System.out.println("콘솔 확인용 = " + updateMyWishResponseDto);
//        userWishUpdateMyWishService.saveWish(updateMyWishResponseDto.toWishEntity(updateMyWishResponseDto));
////        API 테스트용 리턴, 추후 리턴 void로 변경(231119)
//        return this.userWishViewMyWishService.findMyWish(updateMyWishRequestDto.getSubsr(), contentId);
//    }


    //rating
//    @PostMapping(value = "/{content_id}/rating")
//    @Operation(summary = "vod별 평점", description = "클라이언트가 보내는 content_id에 해당하는 vod의 상세페이지에서 평점 매기기")
//    //@RequestBody가 들어오는 json데이터 미리 선언한 엔터티 객체로 매핑해서 들어오게 해줌
//    //{"rating":1~5} 형식으로 데이터 받아서 vodDetailRating객체로 받기(내부 필드 rating)
//    public RatingResponseDto rating(@PathVariable("content_id") String contentId, @RequestBody RatingRequestDto ratingRequestDto) {
//        RatingResponseDto ratingResponseDto = RatingResponseDto.builder().subsr(ratingRequestDto.getSubsr())
//                .contentId(contentId).rating(ratingRequestDto.getRating()).comment(ratingRequestDto.getComment()).build();
////            확인
//        System.out.println("평점 = " + ratingResponseDto.getRating());
//        ratingServiceImpl.saveRating(ratingResponseDto.toRatingEntity(ratingResponseDto));
////        API 테스트용 리턴
//        return this.ratingServiceImpl.findUserRatingByContentId(contentId);
//
//    }
}

