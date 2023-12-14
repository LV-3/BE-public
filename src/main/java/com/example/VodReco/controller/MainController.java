package com.example.VodReco.controller;

import com.example.VodReco.aspects.LogExecutionTime;
import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.client.MainResponseDto;
import com.example.VodReco.dto.BasicInfoOfVodDto;
import com.example.VodReco.service.mainPage.getReco.VodGetRecoServiceImpl;
import com.example.VodReco.service.mainPage.viewVodsByTag.VodviewVodsByTagServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
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

    private final ObjectMapper objectMapper;


    @Lazy
//    @Autowired
//    두 개 붙으면 Lazy가 이김
    public MainController(VodviewVodsByTagServiceImpl vodviewVodsByMoodService, VodGetRecoServiceImpl vodGetRecoService,
                          ObjectMapper objectMapper) {
        this.vodviewVodsByMoodService = vodviewVodsByMoodService;
        this.vodGetRecoService = vodGetRecoService;

        this.objectMapper = objectMapper;
    }

    @LogExecutionTime
    @PostMapping("")
//    public ResponseEntity<Mono<MainResponseDto>> getAllRecoFromModel(@RequestBody UserDto userDto) {
//    public ResponseEntity<MainResponseDto> getAllRecoFromModel(@RequestBody UserDto userDto) {
//    public Mono<MainResponseDto> getAllRecoFromModel(@RequestBody UserDto userDto) {
    public MainResponseDto getAllRecoFromModel(@RequestBody UserDto userDto) {
//        if (vodGetRecoService.getAllContentIdsFromModel(userDto.getSubsr()) != null) {
//            return ResponseEntity.ok(vodGetRecoService.getAllContentIdsFromModel(userDto.getSubsr()));
            return vodGetRecoService.getAllContentIdsFromModel(userDto.getSubsr());
//        }
//        //에러코드 204
//            return ResponseEntity.noContent().build();

    }


    //태그(템플릿 단어)별 vod 조회
    @GetMapping("/{tags}")
    public ResponseEntity<List<BasicInfoOfVodDto>> sendEachTagVods (@PathVariable String tags){
        if (vodviewVodsByMoodService.sendEachTagVods(tags).isEmpty()) {
            //에러코드 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vodviewVodsByMoodService.sendEachTagVods(tags));
    }




}


