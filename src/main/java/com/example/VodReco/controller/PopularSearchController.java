package com.example.VodReco.controller;

import com.example.VodReco.domain.PopularVod;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.service.mainPage.searchVods.SearchVodServiceImpl;
import com.example.VodReco.service.mainPage.viewPopularVods.ViewPopularVodsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class PopularSearchController {

    private final ViewPopularVodsServiceImpl viewPopularVodsService;
    private final SearchVodServiceImpl searchVodService;

    @PostMapping("/popular")
    public ResponseEntity<List<PopularVod>> getTop10Vods() {
        List<PopularVod> popularVods = viewPopularVodsService.getTop10PopularVods();
        return ResponseEntity.ok(popularVods);
    }


    @GetMapping("/search")
    public ResponseEntity<List<VodDto>> searchVods(@RequestParam(value ="searchTerm",required = false) String searchTerm) {
        List<VodDto> foundVods = searchVodService.searchVods(searchTerm);
        if (foundVods.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // 검색 결과 반환
        return new ResponseEntity<>(foundVods, HttpStatus.OK);
    }
}


