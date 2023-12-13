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


    @Override
    public Mono<MainResponseDto> getAllContentIdsFromModel(String subsr) {
//    public MainResponseDto getAllContentIdsFromModel(String subsr) {

        ToModel2ndDto toModel2ndDto = setDataToSendToModel.setDataForModel(subsr);

        try {
            String json = objectMapper.writeValueAsString(toModel2ndDto.getDataForModel());
            toModelJsonDto.setJsonDto(json);

            //테스트용(231212)
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


