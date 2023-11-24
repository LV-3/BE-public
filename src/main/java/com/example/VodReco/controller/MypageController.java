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

    @PostMapping("/rating")
    public List<ViewMyRatingListDto> findMyRatingList(@RequestBody UserDto userDto){
        Optional<List<ViewMyRatingListDto>> myRatingListDtos = userRatingListViewService.findMyRatingList(userDto.getSubsr());
//        if (!myRatingListDtos.isPresent() || myRatingListDtos.get().isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(myRatingListDtos.get());
        return myRatingListDtos.get();
    }

    @PostMapping("/wish")
    public List<ViewMyWishListDto> findMyWishList(@RequestBody UserDto userDto){
        Optional<List<ViewMyWishListDto>> myWishListDtos = userWishListViewService.findMyWishList(userDto.getSubsr());
//        if (!myWishListDtos.isPresent() || myWishListDtos.get().isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(myWishListDtos.get());
        return myWishListDtos.get();
    }

    @PostMapping("/replay")
    public Optional<List<ViewMyWatchListDto>> findMyWatchList(@RequestBody UserDto userDto){
        Optional<List<ViewMyWatchListDto>> myWatchListDtos = userWatchListViewService.findMyWatchList(userDto.getSubsr());
//        if (!myWatchListDtos.isPresent() || myWatchListDtos.get().isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
        return myWatchListDtos;
    }

}
