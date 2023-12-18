package com.example.VodReco.controller;

import com.example.VodReco.aspects.LogExecutionTime;
import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.WeatherRecDto;
import com.example.VodReco.dto.Recommendation.client.MainResponseDto;
import com.example.VodReco.dto.BasicInfoOfVodDto;
import com.example.VodReco.service.mainPage.getReco.VodGetRecoServiceImpl;
import com.example.VodReco.service.viewWeatherRec.WeatherRecViewServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//develop branch commit test

@RestController
//@RequiredArgsConstructor
@Slf4j
@RequestMapping("/main")
public class MainController {
    private final VodGetRecoServiceImpl vodGetRecoService;
    private final WeatherRecViewServiceImpl weatherRecViewService;


    @Lazy

    //    @Autowired
//    두 개 붙으면 Lazy가 이김
    public MainController(VodGetRecoServiceImpl vodGetRecoService,
                          WeatherRecViewServiceImpl weatherRecViewService) {
        this.vodGetRecoService = vodGetRecoService;
        this.weatherRecViewService = weatherRecViewService;
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



    //날씨 기반 추천
    @GetMapping("/weather")
    public WeatherRecDto sendWeatherRecs() {
        return weatherRecViewService.sendWeatherRecommendations();
    }
}


