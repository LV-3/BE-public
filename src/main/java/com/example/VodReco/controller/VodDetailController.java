package com.example.VodReco.controller;

import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.rating.UpdateMyRatingRequestDto;
import com.example.VodReco.dto.rating.UpdateMyRatingDto;
import com.example.VodReco.dto.rating.ViewEveryRatingResponseDto;
import com.example.VodReco.dto.vodDetail.VodDetailResponseDto;
import com.example.VodReco.dto.wish.UpdateMyWishRequestDto;
import com.example.VodReco.dto.wish.UpdateMyWishDto;
import com.example.VodReco.dto.wish.ViewMyWishResponseDto;
import com.example.VodReco.service.vodDetailPage.updateMyRating.UserRatingUpdateMyRatingServiceImpl;
import com.example.VodReco.service.vodDetailPage.updateMyWish.UserWishUpdateMyWishServiceImpl;
import com.example.VodReco.service.vodDetailPage.viewDetailInfo.VodViewDetailInfoServiceImpl;
import com.example.VodReco.service.vodDetailPage.viewEveryRating.UserRatingViewEveryRatingServiceImpl;
import com.example.VodReco.service.vodDetailPage.viewMyWish.UserWishViewMyWishServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//vod별 상세페이지

@RestController // = @Controller + @ResponseBody 객체 리턴하면 json으로 만들어줌
@RequestMapping("/detail")
@Tag(name = "Vod detail", description = "Vod API detail") //swagger 어노테이션(231110)
public class VodDetailController {
    private final VodViewDetailInfoServiceImpl vodViewDetailInfoService;
    private final UserWishViewMyWishServiceImpl userWishViewMyWishService;
    private final UserWishUpdateMyWishServiceImpl userWishUpdateMyWishService;
    private final UserRatingViewEveryRatingServiceImpl userRatingViewEveryRatingService;
    private final UserRatingUpdateMyRatingServiceImpl userRatingUpdateMyRatingService;


    @Autowired
    public VodDetailController(VodViewDetailInfoServiceImpl vodViewDetailInfoService, UserWishViewMyWishServiceImpl userWishViewMyWishService, UserWishUpdateMyWishServiceImpl userWishUpdateMyWishService, UserRatingViewEveryRatingServiceImpl userRatingViewEveryRatingService, UserRatingUpdateMyRatingServiceImpl userRatingUpdateMyRatingService) {
        this.vodViewDetailInfoService = vodViewDetailInfoService;
        this.userWishViewMyWishService = userWishViewMyWishService;
        this.userWishUpdateMyWishService = userWishUpdateMyWishService;
        this.userRatingViewEveryRatingService = userRatingViewEveryRatingService;
        this.userRatingUpdateMyRatingService = userRatingUpdateMyRatingService;
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

    //별도의 requestDto 만들지 않고 동일하게 subsr 필드 하나만 있는 UserDto 사용함(231119)
    @GetMapping(value = "/{content_id}/wish")
    @Operation(summary = "vod별 찜 조회", description = "상세페이지 열릴 때 해당 사용자의 wish 있는지 DB 조회")
    public Integer findMyWish(@PathVariable("content_id")
                                @Schema(description = "content_id", example = "20200622")
                                @Parameter(name = "content_id", description = "컨텐츠 고유id", example = "20200622", required = true)
                                String contentId,
                                @RequestBody UserDto userDto) {
        ViewMyWishResponseDto viewMyWishResponseDto = userWishViewMyWishService.findMyWish(userDto.getSubsr(), contentId);
//            콘솔 확인
        System.out.println("콘솔 확인용 = " + viewMyWishResponseDto);
        if (viewMyWishResponseDto == null) {
            return null;
        }
        return viewMyWishResponseDto.getWish();
    }

    //3. content_id에 해당하는 Vod에 대한 모든 사용자의 rating + review 조회

    @GetMapping(value = "/{content_id}/rating")
    @Operation(summary = "vod별 전체 rating 조회", description = "상세페이지 열릴 때 해당 vod에 대한 전체 사용자의 rating 조회")
    public Optional<List<ViewEveryRatingResponseDto>> findEveryRating(@PathVariable("content_id")
                        @Schema(description = "content_id", example = "20200622") String contentId) {
        System.out.println("콘솔 확인용 = " + Optional.ofNullable(userRatingViewEveryRatingService.findEveryUserRating(contentId)));
        return Optional.ofNullable(userRatingViewEveryRatingService.findEveryUserRating(contentId));
    }


//    상세페이지에서 찜 or 평점 변경

//    4. wish 변경
    @PostMapping(value = "/{content_id}/wish")
    @Operation(summary = "vod별 찜 매기기", description = "상세페이지에서 wish 최초 매기기 또는 기존 wish 변경")
    public ViewMyWishResponseDto updateMyWish(@PathVariable("content_id")
                                    @Schema(description = "content_id", example = "20200622")
                                    String contentId,
                                        @Parameter(name = "content_id", description = "컨텐츠 고유id", example = "20200622", required = true)
                                @RequestBody UpdateMyWishRequestDto updateMyWishRequestDto) {
        UpdateMyWishDto updateMyWishDto = UpdateMyWishDto.builder().subsr(updateMyWishRequestDto.getSubsr())
                .contentId(contentId).wish(updateMyWishRequestDto.getWish()).build();
//            확인
        System.out.println("콘솔 확인용 = " + updateMyWishDto);
        userWishUpdateMyWishService.saveWish(updateMyWishDto);
//        API 테스트용 리턴, 추후 리턴 void로 변경(231119)
        return this.userWishViewMyWishService.findMyWish(updateMyWishRequestDto.getSubsr(), contentId);
    }


//    rating
    @PostMapping(value = "/{content_id}/rating")
    @Operation(summary = "vod별 평점 매기기", description = "상세페이지에서 rating, review 최초 매기기 또는 기존 rating, review 변경")
    public void updateMyRating(@PathVariable("content_id") String contentId, @RequestBody UpdateMyRatingRequestDto updateMyRatingRequestDto) {
        UpdateMyRatingDto updateMyRatingDto = UpdateMyRatingDto.builder().subsr(updateMyRatingRequestDto.getSubsr())
                .contentId(contentId).rating(updateMyRatingRequestDto.getRating()).review(updateMyRatingRequestDto.getReview()).rating_date(updateMyRatingRequestDto.getRating_date()).build();
//            확인
        System.out.println("콘솔 확인용 = " + updateMyRatingDto);
        userRatingUpdateMyRatingService.saveRating(updateMyRatingDto);
    }
}

