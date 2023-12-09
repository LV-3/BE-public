package com.example.VodReco.controller;

import com.example.VodReco.aspects.LogExecutionTime;
import com.example.VodReco.domain.PopularVod;
import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.client.MainResponseDto;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.dto.model.fromModel.receivedContentIds.ReceivedDescriptionContentIds;
import com.example.VodReco.dto.model.fromModel.receivedContentIds.ReceivedMoodContentIds;
import com.example.VodReco.dto.model.fromModel.receivedContentIds.ReceivedPersonalContentIds;
import com.example.VodReco.dto.popular.ViewPopularVodsDto;
import com.example.VodReco.dto.search.SearchRequestDto;
import com.example.VodReco.service.mainPage.getReco.VodGetRecoServiceImpl;
import com.example.VodReco.service.mainPage.getReco.VodReloadServiceImpl;
import com.example.VodReco.service.mainPage.searchVods.SearchVodService;
import com.example.VodReco.service.mainPage.searchVods.SearchVodServiceImpl;
import com.example.VodReco.service.mainPage.viewPopularVods.ViewPopularVodsServiceImpl;
import com.example.VodReco.service.mainPage.viewVodsByMood.VodviewVodsByMoodServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//develop branch commit test

@RestController
//@RequiredArgsConstructor
@Slf4j
@RequestMapping("/main")
public class MainController {
    private final VodviewVodsByMoodServiceImpl vodviewVodsByMoodService;
    private final VodGetRecoServiceImpl vodGetRecoService;
    private final VodReloadServiceImpl vodReloadService;

    //content_id 목록 리스트에 담아둘 객체 스프링 빈에 등록해서 사용(231130)
    private final ReceivedDescriptionContentIds receivedDescriptionContentIds;
    private final ReceivedMoodContentIds receivedMoodContentIds;
    private final ReceivedPersonalContentIds receivedPersonalContentIds;
    private final ViewPopularVodsServiceImpl viewPopularVodsService;
    private final SearchVodServiceImpl searchVodService;

    @Lazy
//    @Autowired
//    두 개 붙으면 Lazy가 이김
    public MainController(VodviewVodsByMoodServiceImpl vodviewVodsByMoodService, VodGetRecoServiceImpl vodGetRecoService, VodReloadServiceImpl vodReloadService,
                          ReceivedDescriptionContentIds receivedDescriptionContentIds, ReceivedMoodContentIds receivedMoodContentIds, ReceivedPersonalContentIds receivedPersonalContentIds,
                          ViewPopularVodsServiceImpl viewPopularVodsService, SearchVodServiceImpl searchVodService) {
        this.vodviewVodsByMoodService = vodviewVodsByMoodService;
        this.vodGetRecoService = vodGetRecoService;
        this.vodReloadService = vodReloadService;
        this.receivedDescriptionContentIds = receivedDescriptionContentIds;
        this.receivedMoodContentIds = receivedMoodContentIds;
        this.receivedPersonalContentIds = receivedPersonalContentIds;
        this.viewPopularVodsService = viewPopularVodsService;
        this.searchVodService = searchVodService;
    }

    @LogExecutionTime
    @PostMapping("")
//    public ResponseEntity<Mono<MainResponseDto>> getAllRecoFromModel(@RequestBody UserDto userDto) {
    public ResponseEntity<MainResponseDto> getAllRecoFromModel(@RequestBody UserDto userDto) {
        if (vodGetRecoService.getAllContentIdsFromModel(userDto.getSubsr()) != null) {
            return ResponseEntity.ok(vodGetRecoService.getAllContentIdsFromModel(userDto.getSubsr()));
        }
        //에러코드 204
            return ResponseEntity.noContent().build();
    }
        //최초 접속이 아닌 새로고침 시에는 subsr 필요 없음. 수정 요망(231126)
//    @LogExecutionTime
//    @PostMapping("/reload1")
//    public List<ToClient1stDto> reloadDescriptionModel() {
////        System.out.println("줄거리 인덱스 확인 = " + receivedDescriptionContentIds.getReceivedDescriptionDataList());
//        return vodReloadService.reloadRec(receivedDescriptionContentIds.getReceivedDescriptionDataList());
//    }
//
//    @PostMapping("/reload2")
//    public List<ToClient1stDto> reloadMoodModel() {
////        System.out.println("무드 인덱스 확인 = " + receivedMoodContentIds.getReceivedMoodDataList());
//        return vodReloadService.reloadRec(receivedMoodContentIds.getReceivedMoodDataList());
//    }
//
//    @PostMapping("/reload3")
//    public List<ToClient1stDto> reloadPersonalModel() {
////        System.out.println("퍼스널 인덱스 확인 = " + receivedPersonalContentIds.getReceivedPersonalDataList());
//        return vodReloadService.reloadRec(receivedPersonalContentIds.getReceivedPersonalDataList());
//    }


    @GetMapping("/{mood}")
    public ResponseEntity<List<BasicInfoOfVodDto>> sendEachMoodVods (@PathVariable String mood){
        if (vodviewVodsByMoodService.sendEachMoodVods(mood).isEmpty()) {
            //에러코드 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vodviewVodsByMoodService.sendEachMoodVods(mood));
    }

    @PostMapping("/popular")
    public ResponseEntity<List<PopularVod>> getTop10Vods() {
        List<PopularVod> popularVods = viewPopularVodsService.getTop10PopularVods();
            return ResponseEntity.ok(popularVods);
        }


    @GetMapping("/search")
    public ResponseEntity<List<VodDto>> searchVods(@RequestParam(value ="searchTerm",required = false) String searchTerm) {
        // searchTerm이 null인지 확인 후 처리
        if (searchTerm != null) {
            searchTerm = searchTerm.replaceAll("\\s+", ""); // 모든 공백 제거
        }
        System.out.print(searchTerm);
        List<VodDto> foundVods = searchVodService.searchVods(searchTerm);
        // 검색 결과 없는 경우,
        if (foundVods.isEmpty()) {
            //System.out.print(foundVods);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // 검색 결과 반환
        //System.out.print(foundVods);
        return new ResponseEntity<>(foundVods, HttpStatus.OK);
    }
}


