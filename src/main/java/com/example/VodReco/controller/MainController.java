package com.example.VodReco.controller;

import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.client.MainResponseDto;
import com.example.VodReco.dto.client.ToClient1stDto;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.dto.model.fromModel.DescriptionModelDataDto;
import com.example.VodReco.dto.model.fromModel.MoodModelDataDto;
import com.example.VodReco.dto.model.fromModel.PersonalModelDataDto;
import com.example.VodReco.dto.model.toModel.ToModelDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.service.mainPage.getReco.VodGetRecoServiceImpl;
import com.example.VodReco.service.mainPage.viewVodsByMood.VodviewVodsByMoodServiceImpl;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/main")
public class MainController {
    private final VodviewVodsByMoodServiceImpl vodviewVodsByMoodService;
    private final VodGetRecoServiceImpl vodGetRecoService;

    private final DescriptionModelDataDto descriptionModelDataDto;
    private final MoodModelDataDto moodModelDataDto;
    private final PersonalModelDataDto personalModelDataDto;

    //테스트 완료 후 Service로 대부분의 로직 밀어넣으면서 삭제하기(231126)
    private final VodRepository vodRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;

    //새로고침 눌리면 사용하던 모든 전역변수 List clearAll 필수(231126)

    private final List<String> descriptionDataList;
    private final List<String> moodDataList;
    private final List<String> personalDataList;


    @PostMapping("")
    public MainResponseDto getAllRecoFromModel(@RequestBody UserDto userDto) {
        long nTime= System.nanoTime();
        ToModelDto toModelDto = vodGetRecoService.setDataForModel(userDto.getSubsr());
        WebClient webClient = WebClient.builder()
                .baseUrl("http://1.220.201.108:8000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<String> response = webClient.post()
                .uri("/items")
//                .uri("/prcs_models")
                .body(Mono.just(toModelDto), ToModelDto.class)
                .retrieve()
                .bodyToMono(String.class);

        response.subscribe(
                result -> {
                    System.out.println("비동기 응답: " + result);
                    parse(result);
                },
                error -> {
                    System.out.println(toModelDto);
                    System.err.println("에러 발생: " + error);
                },
                () -> System.out.println("완료됨")
        );

        //FastAPI 통합 테스트용
        //descriptionModelDataDto, moodModelDataDto, personalModelDataDto는 스프링 빈에 등록했기 때문에 { } 밖에서도 사용 가능할 것으로 예상됨

        for (int i = 0; i < 21; i++) {
            descriptionDataList.add((String) descriptionModelDataDto.getDescriptonData().get(i));
            moodDataList.add((String) moodModelDataDto.getMoodData().get(i));
            personalDataList.add((String) personalModelDataDto.getPersonalData().get(i));
        }


        //FastAPI 배제한 API 테스트를 위해 descriptionDataList, mood...List, personal..List 에 직접 데이터 세팅하는 메서드 호출

//        forTest();

        //다만 { } 안에서만 적용되는 것으로 보임, descriptionDataList/mood../personal..List 내부 데이터를 계속 쓰려면 스프링 빈에 등록해야 하는데
        // 인스턴스가 싱글톤으로 생성돼서 다른 사용자들과 공유될 수 있단 이슈 발생(231126)

        System.out.println("줄거리 content_id 리스트 = " + descriptionDataList);
        System.out.println("무드 content_id 리스트 = " + moodDataList);
        System.out.println("퍼스널 content_id 리스트 = " + personalDataList);


        long nTime2 = System.nanoTime();
        System.out.println("성능 테스트용 = " + (nTime2 - nTime) + "ns");
        return MainResponseDto.builder().description_data(reloadDescriptionModel())
                .genre_data(reloadMoodModel())
                .personal_data(reloadPersonalModel())
                .build();

    }

    //통합테스트 시 주석처리
//    public void forTest() {
//        descriptionDataList.add("22222");
//        descriptionDataList.add("33333");
//        descriptionDataList.add("44444");
//        moodDataList.add("22222");
//        moodDataList.add("55555");
//        moodDataList.add("66666");
//        personalDataList.add("55555");
//        personalDataList.add("44444");
//        personalDataList.add("66666");
//    }

    public void parse(String recoResult) {
        JSONObject jsonObject = new JSONObject(recoResult);
        JSONArray descriptionData = jsonObject.getJSONArray("description_data");
        System.out.println("descriptionData 확인 = " + descriptionData.toString());
        JSONArray moodData = jsonObject.getJSONArray("mood_data");
        JSONArray personalData = jsonObject.getJSONArray("personal_data");
        DescriptionModelDataDto descriptionModelDataDto = DescriptionModelDataDto.builder().descriptonData(descriptionData).build();
        MoodModelDataDto moodDataDto = MoodModelDataDto.builder().moodData(moodData).build();
        PersonalModelDataDto personalDataDto = PersonalModelDataDto.builder().personalData(personalData).build();
    }


    //최초 접속이 아닌 새로고침 시에는 subsr 필요 없음. 수정 요망(231126)
    @PostMapping("/reload1")
    public List<ToClient1stDto> reloadDescriptionModel() {
//    public List<ToClient1stDto> reloadDescriptionModel() {
        //이 descriptionDataList가 안 들어와서 IndexOutOfBoundsException 터짐(231126)
        System.out.println("줄거리 인덱스 확인 = " + descriptionDataList);
        return getToClient1stDtos(descriptionDataList);
    }

    @PostMapping("/reload2")
    public List<ToClient1stDto> reloadMoodModel() {
        System.out.println("무드 인덱스 확인 = " + moodDataList);
        return getToClient1stDtos(moodDataList);
    }

    @PostMapping("/reload3")
    public List<ToClient1stDto> reloadPersonalModel() {
        System.out.println("퍼스널 인덱스 확인 = " + personalDataList);
        return getToClient1stDtos(personalDataList);
    }

    private List<ToClient1stDto> getToClient1stDtos(List<String> DataList) {
        List<ToClient1stDto> list = new ArrayList<>();
        List<String> firstSeven = DataList.stream()
                .limit(7) // 7로 수정하기(231126)
                .toList();
        DataList.subList(0, 7).clear();
        for (int j = 0; j < 7; j++) {
            String contentId = firstSeven.get(j);
            list.add(buildToClient1stDto(contentId));
        }
        return list;
    }

    //별도 테이블 만들면 이 메서드 수정(231126)
    public ToClient1stDto buildToClient1stDto(String contentId) {
        VodDto vodDto = vodtoVodDtoWrapper.toVodDto(vodRepository.findByContentId(contentId));

        return ToClient1stDto.builder().contentId(contentId).posterurl(vodDto.getPosterurl()).title(vodDto.getTitle())
                .mood(vodDto.getMood()).gpt_genres(vodDto.getGpt_genres()).gpt_subjects(vodDto.getGpt_subjects())
                .build();
    }

    @GetMapping("/{mood}")
    public List<BasicInfoOfVodDto> sendEachMoodVods(@PathVariable String mood) {
        return vodviewVodsByMoodService.sendEachMoodVods(mood);
    }

}