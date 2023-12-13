package com.example.VodReco.service.mainPage.getReco;

import com.example.VodReco.dto.client.MainResponseDto;
import com.example.VodReco.dto.model.fromModel.DescriptionModelDataDto;
import com.example.VodReco.dto.model.fromModel.MoodModelDataDto;
import com.example.VodReco.dto.model.fromModel.PersonalModelDataDto;
import com.example.VodReco.dto.model.fromModel.receivedContentIds.ReceivedDescriptionContentIds;
import com.example.VodReco.dto.model.fromModel.receivedContentIds.ReceivedMoodContentIds;
import com.example.VodReco.dto.model.fromModel.receivedContentIds.ReceivedPersonalContentIds;
import com.example.VodReco.dto.model.toModel.*;
import com.example.VodReco.util.forRecommendation.SetDataToSendToClient;
import com.example.VodReco.util.forRecommendation.SetDataToSendToModel;
import com.example.VodReco.util.series.ValidateDuplicateSeriesIdWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class VodGetRecoServiceImpl implements VodGetRecoService {

    private final DescriptionModelDataDto descriptionModelDataDto;
    private final MoodModelDataDto moodModelDataDto;
    private final PersonalModelDataDto personalModelDataDto;

    //새로고침 눌리면 사용하던 모든 전역변수 List clearAll 필수(231126)
    private final ReceivedDescriptionContentIds receivedDescriptionContentIds;
    private final ReceivedMoodContentIds receivedMoodContentIds;
    private final ReceivedPersonalContentIds receivedPersonalContentIds;

    private final ValidateDuplicateSeriesIdWrapper validateDuplicateSeriesIdWrapper;

    private final SetDataToSendToModel setDataToSendToModel;
    private final SetDataToSendToClient setDataToSendToClient;

    private final ObjectMapper objectMapper;

    private final ToModelJsonDto toModelJsonDto;




//    //테스트용
//    List<String> descriptionContentIds21 = new ArrayList<>();
//    List<String> moodContentIds21 = new ArrayList<>();
//    List<String> personalContentIds21 = new ArrayList<>();
//
//    public List<String> getDescriptionContentIds21() {
//        descriptionContentIds21.add("7531");
//        descriptionContentIds21.add("7534");
//        descriptionContentIds21.add("7535");
//        descriptionContentIds21.add("7538");
//        descriptionContentIds21.add("7545");
//        descriptionContentIds21.add("7547");
//        descriptionContentIds21.add("7549");
//        descriptionContentIds21.add("7551");
//        descriptionContentIds21.add("7553");
//        descriptionContentIds21.add("7555");
//        descriptionContentIds21.add("7557");
//        descriptionContentIds21.add("7558");
//        descriptionContentIds21.add("7559");
//        descriptionContentIds21.add("7560");
//        descriptionContentIds21.add("7545");
//        descriptionContentIds21.add("7561");
//        descriptionContentIds21.add("7562");
//        descriptionContentIds21.add("7563");
//        descriptionContentIds21.add("7565");
//        descriptionContentIds21.add("7569");
//        descriptionContentIds21.add("7579");
//        return this.descriptionContentIds21;
//    }
//
//    public List<String> getMoodContentIds21() {
//        moodContentIds21.add("7580");
//        moodContentIds21.add("7581");
//        moodContentIds21.add("7582");
//        moodContentIds21.add("7583");
//        moodContentIds21.add("7584");
//        moodContentIds21.add("7586");
//        moodContentIds21.add("7587");
//        moodContentIds21.add("7588");
//        moodContentIds21.add("7589");
//        moodContentIds21.add("7590");
//        moodContentIds21.add("7591");
//        moodContentIds21.add("7592");
//        moodContentIds21.add("7593");
//        moodContentIds21.add("7594");
//        moodContentIds21.add("7595");
//        moodContentIds21.add("7596");
//        moodContentIds21.add("7601");
//        moodContentIds21.add("7604");
//        moodContentIds21.add("7605");
//        moodContentIds21.add("7606");
//        moodContentIds21.add("7611");
//        return this.moodContentIds21;
//    }
//
//    public List<String> getPersonalContentIds21() {
//        personalContentIds21.add("7613");
//        personalContentIds21.add("7614");
//        personalContentIds21.add("7615");
//        personalContentIds21.add("7616");
//        personalContentIds21.add("7617");
//        personalContentIds21.add("7619");
//        personalContentIds21.add("7621");
//        personalContentIds21.add("7622");
//        personalContentIds21.add("7624");
//        personalContentIds21.add("7625");
//        personalContentIds21.add("7627");
//        personalContentIds21.add("7632");
//        personalContentIds21.add("7633");
//        personalContentIds21.add("7634");
//        personalContentIds21.add("7637");
//        personalContentIds21.add("7638");
//        personalContentIds21.add("7639");
//        personalContentIds21.add("7640");
//        personalContentIds21.add("7641");
//        personalContentIds21.add("7642");
//        personalContentIds21.add("7643");

//        personalContentIds21.add("317");
//        personalContentIds21.add("318");
//        personalContentIds21.add("319");
//        personalContentIds21.add("320");
//        personalContentIds21.add("321");

//        return this.personalContentIds21;
//    }


    @Override
    public Mono<MainResponseDto> getAllContentIdsFromModel(String subsr) {
//    public MainResponseDto getAllContentIdsFromModel(String subsr) {

        ToModel2ndDto toModel2ndDto = setDataToSendToModel.setDataForModel(subsr);

        try {
            String json = objectMapper.writeValueAsString(toModel2ndDto.getDataForModel());
            toModelJsonDto.setJsonDto(json);

            //테스트용(231212)
//            String jsonMood = objectMapper.writeValueAsString(toModel2ndDto.getDataForModel());
            String jsonMood = objectMapper.writeValueAsString(toModel2ndDto.getDataForModel().getMood_data());
            String jsonDescription = objectMapper.writeValueAsString(toModel2ndDto.getDataForModel().getDescription_data());
            String jsonPersonal = objectMapper.writeValueAsString(toModel2ndDto.getDataForModel().getPersonal_data());
            System.out.println("무드 데이터 = 'mood_data':" + jsonMood);
            System.out.println("줄거리 데이터 = 'description_data':" + jsonDescription);
            System.out.println("퍼스널 데이터 = 'personal_data':" + jsonPersonal);

        WebClient webClient = WebClient.builder()
//                .baseUrl("http://1.220.201.108:8000")
//                .baseUrl("http://1.223.55.43:8000")
                .baseUrl("http://lv3-loadbalancer-f-725358857.ap-northeast-2.elb.amazonaws.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();


        return webClient.post()
                .uri("/prcs_models")
//                .body(Mono.just(toModelDto), ToModelDto.class)

                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(toModelJsonDto.getJsonDto()))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(result -> {
                            // 비동기 작업 완료 후 처리할 로직

                    //테스트용
//                    System.out.println(result);

                            setDataToSendToClient.parse(result, subsr);


                            //JSONArray로 받아온 content_id들 리스트로 변환
                            List<String> descriptionList = new ArrayList<>();
                            List<String> moodList = new ArrayList<>();
                            List<String> personalList = new ArrayList<>();
                            for (int i = 0; i < descriptionModelDataDto.getDescriptonData().length(); i++) {
                                descriptionList.add((String) descriptionModelDataDto.getDescriptonData().get(i));
                            }
                            for (int j = 0; j < moodModelDataDto.getMoodData().length(); j++) {
                                moodList.add((String) moodModelDataDto.getMoodData().get(j));
                            }
//                            0번째 index는 별도 처리(231211)
                            for (int k = 1; k < personalModelDataDto.getPersonalData().length(); k++) {
                                personalList.add((String) personalModelDataDto.getPersonalData().get(k));
                            }


                            //시리즈 처리
                            List<String> validatedDescriptionContentIdList = validateDuplicateSeriesIdWrapper.validateDuplicateSeriesId(descriptionList);
                            List<String> validatedMoodContentIdList = validateDuplicateSeriesIdWrapper.validateDuplicateSeriesId(moodList);
                            List<String> validatedPersonalContentIdList = validateDuplicateSeriesIdWrapper.validateDuplicateSeriesId(personalList);

                            //시리즈 처리 통과한 리스트
                            receivedDescriptionContentIds.setReceivedDescriptionDataList(validatedDescriptionContentIdList);
                            receivedMoodContentIds.setReceivedMoodDataList(validatedMoodContentIdList);
                            receivedPersonalContentIds.setReceivedPersonalDataList(validatedPersonalContentIdList);

//                            System.out.println("비동기 블럭 내 subsr 사용 가능? = " + subsr);

                            //시리즈 처리 통과한 리스트 getsubList처리해서 클라이언트에 리턴하는 리스트의 요소 개수 21개 맞추기
                            List<String> descriptionContentIds21 = setDataToSendToClient.getsubList(receivedDescriptionContentIds.getReceivedDescriptionDataList(), subsr);
                            List<String> moodContentIds21 = setDataToSendToClient.getsubList(receivedMoodContentIds.getReceivedMoodDataList(), subsr);
                            List<String> personalContentIds21 = setDataToSendToClient.getsubList(receivedPersonalContentIds.getReceivedPersonalDataList(), subsr);

//                    System.out.println("시리즈 처리 통과한 description 리스트 = " + descriptionContentIds21);

                            // 결과 반환
                            return Mono.just(MainResponseDto.builder()
                                    .description_data(descriptionContentIds21.stream().map(setDataToSendToClient::buildToClient1stDto).toList())
                                    .genre_data(moodContentIds21.stream().map(setDataToSendToClient::buildToClient1stDto).toList())
                                    .personal_data(personalContentIds21.stream().map(setDataToSendToClient::buildToClient1stDto).toList())
                                    //231211 추가
                                    .personal_words((String) personalModelDataDto.getPersonalData().get(0))
                                    .build());
                        })

//                            .description_data(vodReloadService.reloadRec(receivedDescriptionContentIds.getReceivedDescriptionDataList()))
//                            .genre_data(vodReloadService.reloadRec(receivedMoodContentIds.getReceivedMoodDataList()))
//                            .personal_data(vodReloadService.reloadRec(receivedPersonalContentIds.getReceivedPersonalDataList()))
//        return MainResponseDto.builder()
//                .description_data(this.getsubList(this.getDescriptionContentIds21(), subsr).stream().map(vodReloadService::buildToClient1stDto).toList())
//                .genre_data(this.getsubList(this.getMoodContentIds21(), subsr).stream().map(vodReloadService::buildToClient1stDto).toList())
//                        .personal_data(this.getsubList(this.getPersonalContentIds21(), subsr).stream().map(vodReloadService::buildToClient1stDto).toList())
//

                .doOnError(error -> {
//                    System.out.println("데이터 = " + toModelJsonDto.getJsonDto());
//                    System.out.println("무드 데이터 = " + jsonMood);
                    System.err.println("에러 발생: " + error);
                    System.err.println("에러 발생: " + error);

                });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}


