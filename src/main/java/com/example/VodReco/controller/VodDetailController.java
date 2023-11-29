package com.example.VodReco.controller;

import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.rating.UpdateMyRatingRequestDto;
import com.example.VodReco.dto.rating.ViewEveryRatingResponseDto;
import com.example.VodReco.dto.vodDetail.VodDetailResponseDto;
import com.example.VodReco.dto.wish.UpdateMyWishRequestDto;
import com.example.VodReco.dto.wish.ViewMyWishResponseDto;
import com.example.VodReco.service.vodDetailPage.rating.deleteMyRating.UserRatingDeleteMyRatingServiceImpl;
import com.example.VodReco.service.vodDetailPage.rating.updateMyRating.UserRatingUpdateMyRatingServiceImpl;
import com.example.VodReco.service.vodDetailPage.rating.viewEveryRating.UserRatingViewEveryRatingServiceImpl;
import com.example.VodReco.service.vodDetailPage.viewDetailInfo.VodViewDetailInfoServiceImpl;
import com.example.VodReco.service.vodDetailPage.wish.updateMyWish.UserWishUpdateMyWishServiceImpl;
import com.example.VodReco.service.vodDetailPage.wish.viewMyWish.UserWishViewMyWishServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//vod별 상세페이지

@RestController // = @Controller + @ResponseBody 객체 리턴하면 json으로 만들어주는 역할
@RequestMapping("/detail")
//@RequiredArgsConsgtructor 붙이면 모든 final필드 자동으로 Autowired붙은 생성자 만들어줌
@RequiredArgsConstructor
@Tag(name = "Vod detail", description = "Vod API detail") //swagger 어노테이션(231110)
public class VodDetailController {
    private final VodViewDetailInfoServiceImpl vodViewDetailInfoService;
    private final UserWishViewMyWishServiceImpl userWishViewMyWishService;
    private final UserWishUpdateMyWishServiceImpl userWishUpdateMyWishService;
    private final UserRatingViewEveryRatingServiceImpl userRatingViewEveryRatingService;
    private final UserRatingUpdateMyRatingServiceImpl userRatingUpdateMyRatingService;
    private final UserRatingDeleteMyRatingServiceImpl userRatingDeleteMyRatingService;


    //상세페이지 열릴 때

    //1. 기본정보
    //테스트 완료(231124)
    @GetMapping(value = "/{content_id}")
    @Operation(summary = "vod별 상세페이지", description = "url에 있는 content_id 해당 vod의 상세페이지")
    public ResponseEntity<VodDetailResponseDto> getVodDetail(@PathVariable("content_id") String contentId) {//변하는 값을 얻을 때는 @PathVariable 써야함
        //return vodViewDetailInfoService.getVodByContentId(contentId);
        // [jjae] - 변경코드
        VodDetailResponseDto vodDetailResponseDto = vodViewDetailInfoService.getVodByContentId(contentId);
        if (vodDetailResponseDto != null) {
            return ResponseEntity.ok(vodDetailResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //2. 기존 wish 조회
    //테스트 완료(231124)

    //별도의 requestDto 만들지 않고 동일하게 subsr 필드 하나만 있는 UserDto 사용함(231119)
    @PostMapping(value = "/{content_id}/mywish")
    @Operation(summary = "vod별 찜 조회", description = "상세페이지 열릴 때 해당 사용자의 wish 있는지 DB 조회")
    public ResponseEntity<Integer> findMyWish(@PathVariable("content_id")
                                  @Schema(description = "content_id", example = "22222")
                                  @Parameter(name = "content_id", description = "컨텐츠 고유id", example = "20200622", required = true)
                                  String contentId,
                                             @RequestBody UserDto userDto) {
        ViewMyWishResponseDto viewMyWishResponseDto = userWishViewMyWishService.findMyWish(userDto.getSubsr(), contentId);
//        if (viewMyWishResponseDto == null) {
//            return null;
//        }
//        return viewMyWishResponseDto.getWish();
        //[jjae] - 변경코드
        if (viewMyWishResponseDto == null) {
            //[세연] 조회 결과 없으면 404에러(231128)
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(viewMyWishResponseDto.getWish());
    }

    //3. content_id에 해당하는 Vod에 대한 모든 사용자의 rating + review 조회

    @GetMapping(value = "/{content_id}/rating")
    @Operation(summary = "vod별 전체 rating 조회", description = "상세페이지 열릴 때 해당 vod에 대한 전체 사용자의 rating 조회")
//    public Optional<List<ViewEveryRatingResponseDto>> findEveryRating(@PathVariable("content_id")
//                                                                          @Schema(description = "content_id", example = "22222") String contentId) {
    public ResponseEntity<Optional<List<ViewEveryRatingResponseDto>>> findEveryRating(@PathVariable("content_id")
                                                                          @Schema(description = "content_id", example = "22222") String contentId) {
        //return Optional.ofNullable(userRatingViewEveryRatingService.findEveryUserRating(contentId));
        Optional<List<ViewEveryRatingResponseDto>> ratings = Optional.ofNullable(userRatingViewEveryRatingService.findEveryUserRating(contentId));
        //[jjae] - 변경코드
        if (ratings.isEmpty()) {
            //[세연] 조회 결과 없으면 204(231128), wish조회 API와 status 통일 요망
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ratings);
    }


//    상세페이지에서 찜 or 평점 변경

    //  wish 최초 insert
    @PostMapping(value = "/{content_id}/wish")
    @Operation(summary = "vod별 찜 최초 등록", description = "상세페이지에서 wish 최초 매기기 또는 기존 wish 변경")
//    public void saveMyFirstWish(@PathVariable("content_id")
//                                    @Schema(description = "content_id", example = "22222")
    public ResponseEntity<Void> saveMyFirstWish(@PathVariable("content_id")
                                    @Schema(description = "content_id", example = "22222")
                                    String contentId,
                                @Parameter(name = "content_id", description = "컨텐츠 고유id", example = "22222", required = true)
                                    @RequestBody UpdateMyWishRequestDto updateMyWishRequestDto) {
        userWishUpdateMyWishService.saveWish(updateMyWishRequestDto, contentId);
        //[jjae] - 변경코드
        //[세연] save 실패한 경우의 리턴과 분리 요망(231128), 카프카 도입 후 재수정
        return ResponseEntity.ok().build();
    }

//    @PutMapping(value = "/{content_id}/wish")
//    @Operation(summary = "vod별 찜 변경", description = "상세페이지에서 wish 최초 매기기 또는 기존 wish 변경")
//    public void modifyMyWish(@PathVariable("content_id")
//                                 @Schema(description = "content_id", example = "20200622")
//                                 String contentId,
//                             @Parameter(name = "content_id", description = "컨텐츠 고유id", example = "20200622", required = true)
//                                 @RequestBody UpdateMyWishRequestDto updateMyWishRequestDto) {
////        UpdateMyWishDto updateMyWishDto = UpdateMyWishDto.builder().subsr(updateMyWishRequestDto.getSubsr())
////                .contentId(contentId).wish(updateMyWishRequestDto.getWish())
////                .title(build();
////            확인
////        System.out.println("콘솔 확인용 = " + updateMyWishDto);
//        userWishUpdateMyWishService.saveWish(updateMyWishRequestDto, contentId);
//    }


    // rating 최초 insert
    // rating 최초 insert(POST)와 변경(PUT), 삭제(DELETE) 분리 요망(231124)
    @PostMapping(value = "/{content_id}/rating")
    @Operation(summary = "vod별 평점 매기기", description = "상세페이지에서 rating, review 최초 등록")
    public ResponseEntity<Void> saveMyFirstRating(@PathVariable("content_id") String contentId, @RequestBody UpdateMyRatingRequestDto updateMyRatingRequestDto) {
        userRatingUpdateMyRatingService.saveRating(contentId, updateMyRatingRequestDto);
        //[jjae] - 변경코드
        //[세연] 실패한 경우와 리턴 분리 요망. 카프카 도입 후 서비스 레이어로 로직 이동, 코드 재수정(231128)
        return ResponseEntity.ok().build();
    }

    //rating 변경
    @PutMapping(value = "/{content_id}/rating")
    @Operation(summary = "vod별 평점 매기기", description = "상세페이지에서 기존 rating, review 변경")
    public ResponseEntity<Void> updateMyRating(@PathVariable("content_id") String contentId, @RequestBody UpdateMyRatingRequestDto updateMyRatingRequestDto) {
        userRatingUpdateMyRatingService.saveRating(contentId, updateMyRatingRequestDto);
        //[jjae] - 변경코드
        //[세연] 실패한 경우와 리턴 분리 요망. 카프카 도입 후 서비스 레이어로 로직 이동, 코드 재수정(231128)
        return ResponseEntity.ok().build();
    }

    //rating 삭제
    @Transactional
    @DeleteMapping(value = "/{content_id}/rating")
    @Operation(summary = "vod별 평점 삭제", description = "상세페이지에서 rating, review 삭제")

    public ResponseEntity<Void> deleteMyRating(@PathVariable("content_id") String contentId, @RequestBody UserDto userDto) {
        userRatingDeleteMyRatingService.deleteRating(contentId, userDto.getSubsr());
        System.out.println("삭제된 rating 정보 = " + contentId);
        //[jjae] - 변경코드
        //[세연] 실패한 경우와 리턴 분리 요망. 카프카 도입 후 서비스 레이어로 로직 이동, 코드 재수정(231128)
        return ResponseEntity.ok().build();
    }

}




