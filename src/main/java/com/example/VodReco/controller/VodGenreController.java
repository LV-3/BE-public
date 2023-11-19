package com.example.VodReco.controller;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.repository.VodMapping;
import com.example.VodReco.service.genre.viewGenres.VodViewGenresServiceImpl;
import com.example.VodReco.service.genre.viewVodsByGenre.VodViewVodsByGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vods")
public class VodGenreController {
    private final VodViewGenresServiceImpl vodViewGenresService;
    private final VodViewVodsByGenreService vodViewVodsByGenreService;

    @GetMapping("")
    public List<VodMapping> sendGenreList(String genre) {
        return vodViewGenresService.viewGenreList();
    }

    @GetMapping("/{genre}") //VodDto로 변환 메서드 추가 필요(231116)
    public List<Vod> sendGenreVodList(@PathVariable String genre) {
        return vodViewVodsByGenreService.viewVodsByGenre(genre);
    }
}