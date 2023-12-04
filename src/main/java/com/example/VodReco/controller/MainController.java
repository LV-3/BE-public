package com.example.VodReco.controller;

import com.example.VodReco.aspects.LogExecutionTime;
import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.client.MainResponseDto;
import com.example.VodReco.dto.client.ToClient1stDto;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.dto.model.fromModel.receivedContentIds.ReceivedDescriptionContentIds;
import com.example.VodReco.dto.model.fromModel.receivedContentIds.ReceivedMoodContentIds;
import com.example.VodReco.dto.model.fromModel.receivedContentIds.ReceivedPersonalContentIds;
import com.example.VodReco.service.mainPage.getReco.VodGetRecoServiceImpl;
import com.example.VodReco.service.mainPage.getReco.VodReloadServiceImpl;
import com.example.VodReco.service.mainPage.viewVodsByMood.VodviewVodsByMoodServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.List;

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

//    @Lazy
    public MainController(VodviewVodsByMoodServiceImpl vodviewVodsByMoodService, VodGetRecoServiceImpl vodGetRecoService, VodReloadServiceImpl vodReloadService, ReceivedDescriptionContentIds receivedDescriptionContentIds, ReceivedMoodContentIds receivedMoodContentIds, ReceivedPersonalContentIds receivedPersonalContentIds) {
        this.vodviewVodsByMoodService = vodviewVodsByMoodService;
        this.vodGetRecoService = vodGetRecoService;
        this.vodReloadService = vodReloadService;
        this.receivedDescriptionContentIds = receivedDescriptionContentIds;
        this.receivedMoodContentIds = receivedMoodContentIds;
        this.receivedPersonalContentIds = receivedPersonalContentIds;
    }

    @LogExecutionTime
    @PostMapping("")
    public Mono<MainResponseDto> getAllRecoFromModel(@RequestBody UserDto userDto) {
        return vodGetRecoService.getAllContentIdsFromModel(userDto.getSubsr());
    }

    //최초 접속이 아닌 새로고침 시에는 subsr 필요 없음. 수정 요망(231126)
    @LogExecutionTime
    @PostMapping("/reload1")
    public List<ToClient1stDto> reloadDescriptionModel() {
//        System.out.println("줄거리 인덱스 확인 = " + receivedDescriptionContentIds.getReceivedDescriptionDataList());
        return vodReloadService.reloadRec(receivedDescriptionContentIds.getReceivedDescriptionDataList());
    }

    @PostMapping("/reload2")
    public List<ToClient1stDto> reloadMoodModel() {
//        System.out.println("무드 인덱스 확인 = " + receivedMoodContentIds.getReceivedMoodDataList());
        return vodReloadService.reloadRec(receivedMoodContentIds.getReceivedMoodDataList());
    }

    @PostMapping("/reload3")
    public List<ToClient1stDto> reloadPersonalModel() {
//        System.out.println("퍼스널 인덱스 확인 = " + receivedPersonalContentIds.getReceivedPersonalDataList());
        return vodReloadService.reloadRec(receivedPersonalContentIds.getReceivedPersonalDataList());
    }


    @GetMapping("/{mood}")
    public List<BasicInfoOfVodDto> sendEachMoodVods (@PathVariable String mood){
        return vodviewVodsByMoodService.sendEachMoodVods(mood);
    }


}