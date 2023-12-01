package com.example.VodReco.controller;

import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.mypage.ViewMyRatingListDto;
import com.example.VodReco.dto.mypage.ViewMyWatchListDto;
import com.example.VodReco.dto.mypage.ViewMyWishListDto;
import com.example.VodReco.service.mypage.viewMyRatingList.UserRatingListViewServiceImpl;
import com.example.VodReco.service.mypage.viewMyWatchList.UserWatchListViewServiceImpl;
import com.example.VodReco.service.mypage.viewMyWishList.UserWishListViewServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mypage")
public class MypageController {
    private final UserRatingListViewServiceImpl userRatingListViewService;
    private final UserWishListViewServiceImpl userWishListViewService;
    private final UserWatchListViewServiceImpl userWatchListViewService;

    public MypageController(UserRatingListViewServiceImpl userRatingListViewService, UserWishListViewServiceImpl userWishListViewService, UserWatchListViewServiceImpl userWatchListViewService) {
        this.userRatingListViewService = userRatingListViewService;
        this.userWishListViewService = userWishListViewService;
        this.userWatchListViewService = userWatchListViewService;
    }
    // [jjae] - 코드 변경 : rating, wish, replay -> 모두 리턴 타입 변경 + 메소드형식(?) 변경
    // [세연] 실패한 경우와 리턴 분리 요망(231128) --> [jjae] 주석 제거 시 해당 vod가 없는 경우 '콘텐츠 없음'을 반환

    @PostMapping("/rating")
    public ResponseEntity<List<ViewMyRatingListDto>> findMyRatingList(@RequestBody UserDto userDto){
        Optional<List<ViewMyRatingListDto>> myRatingListDtos = userRatingListViewService.findMyRatingList(userDto.getSubsr());
        if (!myRatingListDtos.isPresent() || myRatingListDtos.get().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(myRatingListDtos.get());
    }

    @PostMapping("/wish")
    public ResponseEntity<List<ViewMyWishListDto>> findMyWishList(@RequestBody UserDto userDto){
        Optional<List<ViewMyWishListDto>> myWishListDtos = userWishListViewService.findMyWishList(userDto.getSubsr());
        if (!myWishListDtos.isPresent() || myWishListDtos.get().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(myWishListDtos.get());
    }

    @PostMapping("/replay")
    public ResponseEntity<Optional<List<ViewMyWatchListDto>>> findMyWatchList(@RequestBody UserDto userDto){
        Optional<List<ViewMyWatchListDto>> myWatchListDtos = userWatchListViewService.findMyWatchList(userDto.getSubsr());
        if (!myWatchListDtos.isPresent() || myWatchListDtos.get().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(myWatchListDtos);
    }

}
