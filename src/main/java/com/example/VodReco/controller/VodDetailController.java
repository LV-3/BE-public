package com.example.VodReco.controller;

import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.rating.UpdateMyRatingRequestDto;
import com.example.VodReco.dto.rating.ViewEveryRatingResponseDto;
import com.example.VodReco.dto.vodDetail.VodDetailResponseDto;
import com.example.VodReco.dto.wish.UpdateMyWishRequestDto;
import com.example.VodReco.dto.wish.ViewMyWishResponseDto;
import com.example.VodReco.service.vodDetailPage.update.deleteMyRating.UserRatingDeleteMyRatingServiceImpl;
import com.example.VodReco.service.vodDetailPage.update.updateMyRating.UserRatingUpdateMyRatingServiceImpl;
import com.example.VodReco.service.vodDetailPage.view.viewEveryRating.UserRatingViewEveryRatingServiceImpl;
import com.example.VodReco.service.vodDetailPage.view.viewDetailInfo.VodViewDetailInfoServiceImpl;
import com.example.VodReco.service.vodDetailPage.update.updateMyWish.UserWishUpdateMyWishServiceImpl;
import com.example.VodReco.service.vodDetailPage.view.viewMyWish.UserWishViewMyWishServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//vod별 상세페이지

@RestController // = @Controller + @ResponseBody 객체 리턴하면 json으로 만들어주는 역할
@RequestMapping("/detail")
@Transactional
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
    //[에러코드 관련] - 상세페이지 열때 모든 정보는 DB에서 꺼내오기 때문에 오류발생확률 낮음 -> GlobalExceptionHandler에서 처리
    // 조회 결과 없는 경우에는 null(200) 전송

    //1. 기본정보
    @GetMapping(value = "/{content_id}")
    @Operation(summary = "vod별 상세페이지", description = "url에 있는 content_id 해당 vod의 상세페이지")
    public ResponseEntity<VodDetailResponseDto> getVodDetail(@PathVariable("content_id") String contentId) {//변하는 값을 얻을 때는 @PathVariable 써야함
        //return vodViewDetailInfoService.getVodByContentId(contentId);
        // [jjae] - 변경코드
        VodDetailResponseDto vodDetailResponseDto = vodViewDetailInfoService.getVodByContentId(contentId);
//        if (vodDetailResponseDto != null) {
            return ResponseEntity.ok(vodDetailResponseDto);
//        } else {
//            //에러 코드 404
//            return ResponseEntity.notFound().build();
//        }
    }


    //2. 기존 wish 조회
    //별도의 requestDto 만들지 않고 동일하게 subsr 필드 하나만 있는 UserDto 사용함(231119)
    @PostMapping(value = "/{content_id}/mywish")
    @Operation(summary = "vod별 찜 조회", description = "상세페이지 열릴 때 해당 사용자의 wish 있는지 DB 조회")
    public ResponseEntity<Integer> findMyWish(@PathVariable("content_id")
                                  @Schema(description = "content_id", example = "22222")
                                  @Parameter(name = "content_id", description = "컨텐츠 고유id", example = "20200622", required = true)
                                  String contentId,
                                             @RequestBody UserDto userDto) {
        ViewMyWishResponseDto viewMyWishResponseDto = userWishViewMyWishService.findMyWish(userDto.getSubsr(), contentId);
        //[jjae] - 변경코드
        if (viewMyWishResponseDto == null) {
            //[세연] 에러 코드 404
            //return ResponseEntity.notFound().build();
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.ok(viewMyWishResponseDto.getWish());
    }

    //3. content_id에 해당하는 Vod에 대한 모든 사용자의 rating + review 조회

    @GetMapping(value = "/{content_id}/rating")
    @Operation(summary = "vod별 전체 rating 조회", description = "상세페이지 열릴 때 해당 vod에 대한 전체 사용자의 rating 조회")
//    public Optional<List<ViewEveryRatingResponseDto>> findEveryRating(@PathVariable("content_id")
//                                                                          @Schema(description = "content_id", example = "22222") String contentId) {
    public ResponseEntity<List<ViewEveryRatingResponseDto>> findEveryRating(@PathVariable("content_id")
                                                                          @Schema(description = "content_id", example = "22222") String contentId) {
        //return Optional.ofNullable(userRatingViewEveryRatingService.findEveryUserRating(contentId));
        List<ViewEveryRatingResponseDto> ratings = userRatingViewEveryRatingService.findEveryUserRating(contentId);
        //[jjae] - 변경코드
        if (ratings.isEmpty()) {
            //[세연] 조회 결과 없으면 204(231128), wish조회 API와 status 통일 요망
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.ok(ratings);
    }


//    상세페이지에서 찜 or 평점 변경

    //  wish insert(0or1)
    @PostMapping(value = "/{content_id}/wish")
    @Operation(summary = "vod별 찜 최초 등록", description = "상세페이지에서 wish 최초 매기기 또는 기존 wish 변경")
//    public void saveMyFirstWish(@PathVariable("content_id")
//                                    @Schema(description = "content_id", example = "22222")
    public ResponseEntity<Void> saveMyFirstWish(@PathVariable("content_id")
                                    @Schema(description = "content_id", example = "22222")
                                    String contentId,
                                @Parameter(name = "content_id", description = "컨텐츠 고유id", example = "22222", required = true)
                                    @RequestBody UpdateMyWishRequestDto updateMyWishRequestDto) {
        //[jjae] - 변경코드
        //[jjae]- [ 발생가능한 에러 ]
        //유효성 검사 오류: 클라이언트가 잘못된 데이터를 보낸 경우 (예: 필수 필드 누락, 잘못된 형식 등)
        //데이터베이스 오류: 데이터 저장 시 데이터베이스 연결이 실패하는 경우, 저장 실패 등
//        try {
            userWishUpdateMyWishService.saveWish(updateMyWishRequestDto, contentId);
            return ResponseEntity.ok().build();
//        } catch (Exception e) {
////            에러 코드 500
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }

        //처리 오류: 어떤 이유로 인해 찜을 저장하는 로직이 실패하는 경우
    }

    // rating 최초 insert
    @PostMapping(value = "/{content_id}/rating")
    @Operation(summary = "vod별 평점 매기기", description = "상세페이지에서 rating, review 최초 등록")
    public ResponseEntity<String> saveMyFirstRating(@PathVariable("content_id") String contentId, @RequestBody UpdateMyRatingRequestDto updateMyRatingRequestDto) {
        if (updateMyRatingRequestDto.getRating() == 0 || updateMyRatingRequestDto.getReview().isEmpty()) {
            return ResponseEntity.badRequest().body("Rating cannot be 0 and Review cannot be null.");
        } else {
            userRatingUpdateMyRatingService.saveRating(contentId, updateMyRatingRequestDto);
            return ResponseEntity.ok().build();
        }
        //       userRatingUpdateMyRatingService.saveRating(contentId, updateMyRatingRequestDto);
        //[jjae] - 변경코드
        // [jjae] - 발생할 수 있는 에러
        //입력 유효성 검사 오류: 클라이언트가 잘못된 데이터를 전송하는 경우
        //데이터베이스 저장 오류: 데이터 저장 시 데이터베이스에서 오류가 발생하는 경우
        //인증 및 권한 오류: 사용자가 권한이 없는 작업을 수행하려고 시도하는 경우 --> 권한이 없을일은,, 로그인하지 않은 사용자인데 이건 불가능한 에러
//        try {
//            userRatingUpdateMyRatingService.saveRating(contentId, updateMyRatingRequestDto);
//            return ResponseEntity.ok().build();
//        } catch (ValidationException e) { //-> 클라이언트가 잘못된 데이터를 전송하는 경우 //이것도 가능성 없으면 빼도 될덧
//            return ResponseEntity.badRequest().build();//400에러
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500에러
//        }

    }

    //rating 변경
    @PutMapping(value = "/{content_id}/rating")
    @Operation(summary = "vod별 평점 변경", description = "상세페이지에서 기존 rating, review 변경한 뒤 저장")
    public ResponseEntity<Void> updateMyRating(@PathVariable("content_id") String contentId, @RequestBody UpdateMyRatingRequestDto updateMyRatingRequestDto) {
        if (updateMyRatingRequestDto.getRating() == 0 || updateMyRatingRequestDto.getReview().isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            // 유효한 경우에만 변경 로직을 수행하도록 처리
            userRatingUpdateMyRatingService.saveRating(contentId, updateMyRatingRequestDto);
            return ResponseEntity.ok().build();
        }
        //[jjae] - 변경코드
//        try {
            //userRatingUpdateMyRatingService.saveRating(contentId, updateMyRatingRequestDto);
            //던져지는 exception때문에 롤백됨 -> 에러. 일단 200만 남기고 1차 구현(231206)
            //return ResponseEntity.ok().build();
//        } catch (Exception e) {
////            에러코드 500
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }

    //rating 삭제
//    @Transactional
    @DeleteMapping(value = "/{content_id}/rating")
    @Operation(summary = "vod별 평점 삭제", description = "상세페이지에서 rating, review 삭제")

    public ResponseEntity<Void> deleteMyRating(@PathVariable("content_id") String contentId, @RequestBody UserDto userDto) {
        System.out.println("삭제된 rating 정보 = " + contentId);
        //[jjae] - 변경코드
//        try {
            userRatingDeleteMyRatingService.deleteRating(contentId, userDto.getSubsr());
            return ResponseEntity.ok().build();
//        } catch (Exception e) {
////            에러코드 500
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }
}




