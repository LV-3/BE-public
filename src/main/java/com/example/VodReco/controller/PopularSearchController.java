package com.example.VodReco.controller;

import com.example.VodReco.domain.PopularVod;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.service.mainPage.getReco.VodGetRecoServiceImpl;
import com.example.VodReco.service.mainPage.searchVods.SearchVodServiceImpl;
import com.example.VodReco.service.mainPage.viewPopularVods.ViewPopularVodsServiceImpl;
import com.example.VodReco.service.mainPage.viewVodsByTag.VodviewVodsByTagServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    public ResponseEntity<List<PopularVod>> getTop10Vods() {
        List<PopularVod> popularVods = viewPopularVodsService.getTop10PopularVods();
        return ResponseEntity.ok(popularVods);
    }

    @GetMapping("/search/{input}")
    public ResponseEntity<List<VodDto>> searchVods(@PathVariable("input") String searchTerm) {
        List<VodDto> foundVods = searchVodService.searchVods(searchTerm);
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


