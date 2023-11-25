package com.example.VodReco.controller;

import com.example.VodReco.dto.UserDto;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.client.MainResponseDto;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.dto.model.toModel.EveryDescription;
import com.example.VodReco.dto.model.toModel.EveryMood;
import com.example.VodReco.service.mainPage.viewVodsByMood.VodviewVodsByMoodServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/main")
public class MainController {

    private final List<EveryDescription> descriptionList = new ArrayList<>();
    private final List<EveryMood> genreList = new ArrayList<>();

    //테스트용(231112)
    private final List<String> personalList = new ArrayList<String>();

    private final VodviewVodsByMoodServiceImpl vodviewVodsByMoodService;


//새로고침 눌리면 사용하던 모든 전역변수 List clearAll 필수(231116)

    //2. 새로고침 클릭 이후 대기하던 데이터를 형식 맞춰 모델에 보내는 메서드 3개(231104)

    //3. 새로고침 클릭 이후 모델로부터 데이터 받아서 -> 형식 맞추고 -> 프론트에 보내는 메서드(231104)

//    @GetMapping("")
//    public void showInitialRecommendation() {
//        String message = "jjae kafka 2nd test";
//        producerService.sendMessage(message);
//    }

//    @GetMapping("/jjae-test")
//    public String testforkafka(){
//        return consumerService.getMessageFromModel();
//    }


    @PostMapping("")
    public void getAllRecoFromModel (@RequestBody UserDto userDto){
        MainResponseDto mainResponseDto = new MainResponseDto();

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



//    @GetMapping("/rereco")
//    public MainResponseDto getFromModel(){
//
//        //Topic에 데이터 전송
//        // personal모델 추후 작성 필요(231112)

//
//        //테스트를 위해 일시적 주석처리함
////        descriptionList.clear(); //다음 턴을 위해 리스트 비우기(231112)
////        genreList.clear();
//
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

