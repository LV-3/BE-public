package com.example.VodReco.controller;

import com.example.VodReco.aspects.LogExecutionTime;
import com.example.VodReco.domain.PopularVod;
import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.client.MainResponseDto;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.service.mainPage.getReco.VodGetRecoServiceImpl;
import com.example.VodReco.service.mainPage.searchVods.SearchVodServiceImpl;
import com.example.VodReco.service.mainPage.viewPopularVods.ViewPopularVodsServiceImpl;
import com.example.VodReco.service.mainPage.viewVodsByTag.VodviewVodsByTagServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


//develop branch commit test

@RestController
//@RequiredArgsConstructor
@Slf4j
@RequestMapping("/main")
public class MainController {
    private final VodviewVodsByTagServiceImpl vodviewVodsByMoodService;
    private final VodGetRecoServiceImpl vodGetRecoService;

    //content_id 목록 리스트에 담아둘 객체 스프링 빈에 등록해서 사용(231130)
    private final ViewPopularVodsServiceImpl viewPopularVodsService;
    private final SearchVodServiceImpl searchVodService;

    @Lazy
//    @Autowired
//    두 개 붙으면 Lazy가 이김
    public MainController(VodviewVodsByTagServiceImpl vodviewVodsByMoodService, VodGetRecoServiceImpl vodGetRecoService,
                          ViewPopularVodsServiceImpl viewPopularVodsService, SearchVodServiceImpl searchVodService) {
        this.vodviewVodsByMoodService = vodviewVodsByMoodService;
        this.vodGetRecoService = vodGetRecoService;
        this.viewPopularVodsService = viewPopularVodsService;
        this.searchVodService = searchVodService;
    }

    @LogExecutionTime
    @PostMapping("")
//    public ResponseEntity<Mono<MainResponseDto>> getAllRecoFromModel(@RequestBody UserDto userDto) {
//    public ResponseEntity<MainResponseDto> getAllRecoFromModel(@RequestBody UserDto userDto) {
    public Mono<MainResponseDto> getAllRecoFromModel(@RequestBody UserDto userDto) {
//        if (vodGetRecoService.getAllContentIdsFromModel(userDto.getSubsr()) != null) {
//            return ResponseEntity.ok(vodGetRecoService.getAllContentIdsFromModel(userDto.getSubsr()));
            return vodGetRecoService.getAllContentIdsFromModel(userDto.getSubsr());
//        }
//        //에러코드 204
//            return ResponseEntity.noContent().build();

    }


    //태그(템플릿 단어)별 vod 조회
    @GetMapping("/{tags}")
    public ResponseEntity<List<BasicInfoOfVodDto>> sendEachTagVods (@PathVariable String tag){
        if (vodviewVodsByMoodService.sendEachTagVods(tag).isEmpty()) {
            //에러코드 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vodviewVodsByMoodService.sendEachTagVods(tag));
    }



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


