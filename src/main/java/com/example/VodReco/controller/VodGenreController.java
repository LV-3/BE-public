package com.example.VodReco.controller;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.repository.VodMapping;
import com.example.VodReco.service.VodServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vods")
public class VodGenreController {
    private final VodServiceImpl vodServiceImpl;

    @GetMapping("")
    public List<VodMapping> sendGenreList(String genre) {
        return vodServiceImpl.sendGenreList();
    }

    @GetMapping("/{genre}") //VodDto로 변환 메서드 추가 필요(231116)
    public List<Vod> sendGenreVodList(@PathVariable String genre) {
        return vodServiceImpl.sendVodsByGenre(genre);
    }
}
