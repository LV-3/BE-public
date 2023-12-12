//package com.example.VodReco.controller;
//
//import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/category")
//public class VodCategoryGenreController {
//
//    //카테고리 목록 보기
//    @GetMapping("")
//    public ResponseEntity<List<String>> sendCategoryList() throws UnsupportedEncodingException {
//        List<String> categoryList = genreViewGenresService.viewGenreList();
//        //[세연] genreList는 서비스 레이어에서 이미 new ArrayList()로 선언했기 때문에
//        //조회 결과가 없어도 null X. 빈 리스트만 가능(231128)
//        if (!genreList.isEmpty()) {
//            return ResponseEntity.ok(genreList);
//        } else {
//            //애러코드 204
//            return ResponseEntity.noContent().build();
//        }
//
//    }
//
//    //카테고리별 장르 목로 보기
//    @GetMapping("/{category}")
//    //해당하는 카테고리 하위 모든 장르 리스트 보내주면 될 것으로 예상됨.(231211)
//    public ResponseEntity<List<BasicInfoOfVodDto>> sendGenreInCategoryList(@PathVariable String genre) throws UnsupportedEncodingException {
//        List<BasicInfoOfVodDto> vodList = vodViewVodsByGenreService.viewVodsByGenre(genre);
////        if (vodList != null && !vodList.isEmpty()) {
//        ///[세연] genre API와 동일
//        if (!vodList.isEmpty()) {
//            return ResponseEntity.ok(vodList);
//        } else {
//            //204
//            return ResponseEntity.noContent().build();
//        }
//    }
//}
//
//    //장르별 Vod 목록 보기
//    @GetMapping("/{category}/{genre}")
//    public ResponseEntity<List<BasicInfoOfVodDto>> sendGenreVodList(@PathVariable("category") String category, @PathVariable("genre") String genre) throws UnsupportedEncodingException {
//        List<BasicInfoOfVodDto> vodList = vodViewVodsByGenreService.viewVodsByGenre(genre);
//        if (!vodList.isEmpty()) {
//            return ResponseEntity.ok(vodList);
//        } else {
//            //204
//            return ResponseEntity.noContent().build();
//        }
//    }
//}
//}
