package com.example.VodReco.controller;

import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.dto.popular.ViewPopularVodsDto;
import com.example.VodReco.service.mainPage.searchVods.SearchVodServiceImpl;
import com.example.VodReco.service.mainPage.viewPopularVods.ViewPopularVodsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PopularSearchController {

    private final ViewPopularVodsServiceImpl viewPopularVodsService;
    private final SearchVodServiceImpl searchVodService;

    @PostMapping("/main/popular")
    public ResponseEntity<List<ViewPopularVodsDto>> getTop10Vods() {
        List<ViewPopularVodsDto> popularVods = viewPopularVodsService.getTop10PopularVods();
        return ResponseEntity.ok(popularVods);
    }

    @GetMapping("/search/{input}")
    public ResponseEntity<List<BasicInfoOfVodDto>> searchVods(@PathVariable("input") String searchTerm) {
        List<BasicInfoOfVodDto> foundVods = searchVodService.searchVods(searchTerm);
        if (searchTerm == null || searchTerm.isEmpty()) {
            // 사용자가 아무것도 입력하지 않은 경우
            return ResponseEntity.ok().body(null);
        }
//        if (foundVods.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
        // 검색 결과 반환
        return new ResponseEntity<>(foundVods, HttpStatus.OK);
    }
}


