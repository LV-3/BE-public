package com.example.VodReco.controller;

import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
//import com.example.VodReco.service.genre.viewGenres.GenreViewGenresServiceImpl;
import com.example.VodReco.service.genre.viewGenres.GenreViewGenresServiceImpl;
import com.example.VodReco.service.genre.viewVodsByGenre.VodViewVodsByGenreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<String> sendGenreList() {
        return genreViewGenresService.viewGenreList();
    }

    //장르별 Vod 목록 보기
    @GetMapping("/{genre}")
    public List<BasicInfoOfVodDto> sendGenreVodList(@PathVariable String genre) {
        return vodViewVodsByGenreService.viewVodsByGenre(genre);
    }
}

