package com.example.VodReco.controller;

import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
//import com.example.VodReco.service.genre.viewGenres.GenreViewGenresServiceImpl;
import com.example.VodReco.service.genre.viewGenres.GenreViewGenresServiceImpl;
import com.example.VodReco.service.genre.viewVodsByGenre.VodViewVodsByGenreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class VodGenreController {
    @Autowired
    private final GenreViewGenresServiceImpl genreViewGenresService;
    @Autowired
    private final VodViewVodsByGenreServiceImpl vodViewVodsByGenreService;

    //장르 목록 보기
    @GetMapping("")
    public ResponseEntity<List<String>> sendGenreList() {
        //return genreViewGenresService.viewGenreList();
        //[jjae] - 변경코드
        List<String> genreList = genreViewGenresService.viewGenreList();
        if (genreList != null && !genreList.isEmpty()) {
            return ResponseEntity.ok(genreList);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    //장르별 Vod 목록 보기
    @GetMapping("/{genre}")
    public ResponseEntity<List<BasicInfoOfVodDto>> sendGenreVodList(@PathVariable String genre) {
        //return vodViewVodsByGenreService.viewVodsByGenre(genre);
        //[jjae] - 변경코드
        List<BasicInfoOfVodDto> vodList = vodViewVodsByGenreService.viewVodsByGenre(genre);
        if (vodList != null && !vodList.isEmpty()) {
            return ResponseEntity.ok(vodList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}

