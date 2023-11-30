package com.example.VodReco.controller;

import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
//import com.example.VodReco.service.genre.viewGenres.GenreViewGenresServiceImpl;
import com.example.VodReco.service.genre.viewGenres.GenreViewGenresServiceImpl;
import com.example.VodReco.service.genre.viewVodsByGenre.VodViewVodsByGenreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class VodGenreController {
    private final GenreViewGenresServiceImpl genreViewGenresService;
    private final VodViewVodsByGenreServiceImpl vodViewVodsByGenreService;

    //장르 목록 보기
    @GetMapping("")
    public ResponseEntity<List<String>> sendGenreList() throws UnsupportedEncodingException {
        //return genreViewGenresService.viewGenreList();
        //[jjae] - 변경코드
        List<String> genreList = genreViewGenresService.viewGenreList();
            //[세연] genreList는 서비스 레이어에서 이미 new ArrayList()로 선언했기 때문에
            //조회 결과가 없어도 null X. 빈 리스트만 가능(231128)
//        if (genreList != null && !genreList.isEmpty()) {
        if (!genreList.isEmpty()) {
            return ResponseEntity.ok(genreList);
        } else {
            //204
            return ResponseEntity.noContent().build();
        }

    }

    //장르별 Vod 목록 보기
    @GetMapping("/{genre}")
    public ResponseEntity<List<BasicInfoOfVodDto>> sendGenreVodList(@PathVariable String genre) throws UnsupportedEncodingException {
        //return vodViewVodsByGenreService.viewVodsByGenre(genre);
        //[jjae] - 변경코드
        List<BasicInfoOfVodDto> vodList = vodViewVodsByGenreService.viewVodsByGenre(genre);
//        if (vodList != null && !vodList.isEmpty()) {
        ///[세연] genre API와 동일
        if (!vodList.isEmpty()) {
            return ResponseEntity.ok(vodList);
        } else {
            //204
            return ResponseEntity.noContent().build();
        }
    }
}

