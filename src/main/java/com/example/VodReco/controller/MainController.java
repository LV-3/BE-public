package com.example.VodReco.controller;

import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.client.InitialRecommendationDto;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.service.mainPage.viewVodsByMood.VodviewVodsByMoodServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/main")
public class MainController {

    private final VodviewVodsByMoodServiceImpl vodviewVodsByMoodService;

//새로고침 눌리면 사용하던 모든 전역변수 List clearAll 필수(231116)

    @PostMapping("")
    public void getAllRecoFromModel (@RequestBody UserDto userDto){
        InitialRecommendationDto initialRecommendationDto = new InitialRecommendationDto();
        WebClient webClient = WebClient.builder()
                //포트 확인하고 수정
                .baseUrl("http://fastapi_server_url)")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        //FastAPI에 보낼 데이터 생성
//        String requestDataObject = new YourDataObject("value1", "value2");
        //post방식 요청으로 FastAPI에 데이터 전송
        Mono<String> response = webClient.post()
                //요청을 보낼 FastAPI의 endpoint
                .uri("/endpoint_of_FastAPI_modification_needed")
                //객체를 JSON으로 serialize해서 body에 넣어 보냄
                .body(Mono.just(requestDataObject), YourDataObject.class)
                .retrieve()
                .bodyToMono(String.class);

        //비동기 처리
        response.subscribe(
                result -> System.out.println("비동기 응답: " + result),
                error -> System.err.println("에러 발생: " + error),
                () -> System.out.println("완료")
        );

    }

    @PostMapping("/reload1")
    public void send1stModelReco(@RequestBody UserDto userDto) {
    }

    @PostMapping("/reload2")
    public void send2ndModelReco(@RequestBody UserDto userDto) {
    }
    @PostMapping("/reload3")
    public void send3rdModelReco(@RequestBody UserDto userDto) {
    }

    @GetMapping("/{mood}")
    public List<BasicInfoOfVodDto> sendEachMoodVods(@PathVariable String mood) {
        return vodviewVodsByMoodService.sendEachMoodVods(mood);
    }



//        //이하 consumer에서 가져온 데이터 처리해서 프론트로 보내는 코드(231112)
////        초기에 Topic에 데이터 없으면 NPE(231112) -> exception 처리 필요, setPollTimeout(1000)? 기본은 5000ms
//
//        FromModelDto fromModelDto = consumerService.getProcessedData();
//        System.out.println("컨트롤러 확인 = " + fromModelDto);
//
//
//        ToClient1stDto[] array1 = new ToClient1stDto[10];
//        List<String> descriptionData = fromModelDto.getDescription_data();
//        ToClient2ndDto descriptionDto = sendData(array1, descriptionData);
//
//        ToClient1stDto[] array2 = new ToClient1stDto[10];
//        List<String> genreData = fromModelDto.getGenre_data();
//        ToClient2ndDto genreDto = sendData(array2, genreData);
//
//        ToClient1stDto[] array3 = new ToClient1stDto[10];
//        List<String> personalData = fromModelDto.getPersonal_data();
//        ToClient2ndDto personalDto = sendData(array3, personalData);
//
//        return MainResponseDto.builder().description_data(descriptionDto)
//                .genre_data(genreDto).personal_data(personalDto).build();
//
//    }
//
//    private ToClient2ndDto sendData(ToClient1stDto[] array, List<String> data) {
//        for (int i = 0; i < 10; i++) {
//            ToClient1stDto toClient1stDto = ToClient1stDto.builder().contentId(data.get(i))
//                    .posterurl(vodServiceImpl.getVodByContentId(data.get(i)).getPosterurl()).build();
//            array[i] = toClient1stDto;
//        }
//        return ToClient2ndDto.builder().vod1(array[0]).vod2(array[1]).vod3(array[2])
//                .vod4(array[3]).vod5(array[4]).vod6(array[5]).vod7(array[6]).vod8(array[6]).vod9(array[8]).vod10(array[9]).build();
//    }
}

