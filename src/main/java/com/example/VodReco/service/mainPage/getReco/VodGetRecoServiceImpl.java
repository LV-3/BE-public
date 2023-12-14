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
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    private final ToClientListDto toClientListDto;



//    //테스트용
    List<String> descriptionContentIds21 = new ArrayList<>();
    List<String> moodContentIds21 = new ArrayList<>();
    List<String> personalContentIds21 = new ArrayList<>();

    public List<String> getDescriptionContentIds21() {
        descriptionContentIds21.add("7531");
        descriptionContentIds21.add("7534");
        descriptionContentIds21.add("7535");
        descriptionContentIds21.add("7538");
        descriptionContentIds21.add("7545");
        descriptionContentIds21.add("7547");
        descriptionContentIds21.add("7549");
        descriptionContentIds21.add("7551");
        descriptionContentIds21.add("7553");
        descriptionContentIds21.add("7555");
        descriptionContentIds21.add("7557");
        descriptionContentIds21.add("7558");
        descriptionContentIds21.add("7559");
        descriptionContentIds21.add("7560");
        descriptionContentIds21.add("7545");
        descriptionContentIds21.add("7561");
        descriptionContentIds21.add("7562");
        descriptionContentIds21.add("7563");
        descriptionContentIds21.add("7565");
        descriptionContentIds21.add("7569");
        descriptionContentIds21.add("7579");
        return this.descriptionContentIds21;
    }

    public List<String> getMoodContentIds21() {
        moodContentIds21.add("7580");
        moodContentIds21.add("7581");
        moodContentIds21.add("7582");
        moodContentIds21.add("7583");
        moodContentIds21.add("7584");
        moodContentIds21.add("7586");
        moodContentIds21.add("7587");
        moodContentIds21.add("7588");
        moodContentIds21.add("7589");
        moodContentIds21.add("7590");
        moodContentIds21.add("7591");
        moodContentIds21.add("7592");
        moodContentIds21.add("7593");
        moodContentIds21.add("7594");
        moodContentIds21.add("7595");
        moodContentIds21.add("7596");
        moodContentIds21.add("7601");
        moodContentIds21.add("7604");
        moodContentIds21.add("7605");
        moodContentIds21.add("7606");
        moodContentIds21.add("7611");
        return this.moodContentIds21;
    }

    public List<String> getPersonalContentIds21() {
        personalContentIds21.add("7613");
        personalContentIds21.add("7614");
        personalContentIds21.add("7615");
        personalContentIds21.add("7616");
        personalContentIds21.add("7617");
        personalContentIds21.add("7619");
        personalContentIds21.add("7621");
        personalContentIds21.add("7622");
        personalContentIds21.add("7624");
        personalContentIds21.add("7625");
        personalContentIds21.add("7627");
        personalContentIds21.add("7632");
        personalContentIds21.add("7633");
        personalContentIds21.add("7634");
        personalContentIds21.add("7637");
        personalContentIds21.add("7638");
        personalContentIds21.add("7639");
        personalContentIds21.add("7640");
        personalContentIds21.add("7641");
        personalContentIds21.add("7642");
        personalContentIds21.add("7643");

        personalContentIds21.add("317");
        personalContentIds21.add("318");
        personalContentIds21.add("319");
        personalContentIds21.add("320");
        personalContentIds21.add("321");

        return this.personalContentIds21;
}



    @Override
//    public Mono<MainResponseDto> getAllContentIdsFromModel(String subsr) {
    public MainResponseDto getAllContentIdsFromModel(String subsr) {
        ToModel2ndDto toModel2ndDto = setDataToSendToModel.setDataForModel(subsr);

//        try {
//            String json = objectMapper.writeValueAsString(toModel2ndDto.getDataForModel());
//            toModelJsonDto.setJsonDto(json);
//
//            WebClient webClient = WebClient.builder()
//                    .baseUrl("http://lv3-loadbalancer-f-725358857.ap-northeast-2.elb.amazonaws.com")
//                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                    .build();
//
//            return webClient.post()
//                    .uri("/prcs_models")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .body(BodyInserters.fromValue(toModelJsonDto.getJsonDto()))
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .flatMap(result -> {
//                        if (result != null) {
//                            setDataToSendToClient.parse(result, subsr);
//
//                            // 여기에 원하는 작업 수행
//                            //JSONArray로 받아온 content_id들 리스트로 변환
//                            List<String> descriptionList = new ArrayList<>();
//                            List<String> moodList = new ArrayList<>();
//                            List<String> personalList = new ArrayList<>();
//                            for (int i = 0; i < descriptionModelDataDto.getDescriptonData().length(); i++) {
//                                descriptionList.add((String) descriptionModelDataDto.getDescriptonData().get(i));
//                            }
//                            for (int j = 0; j < moodModelDataDto.getMoodData().length(); j++) {
//                                moodList.add((String) moodModelDataDto.getMoodData().get(j));
//                            }
//                            //                            0번째 index는 별도 처리(231211)
//                            for (int k = 1; k < personalModelDataDto.getPersonalData().length(); k++) {
//                                personalList.add((String) personalModelDataDto.getPersonalData().get(k));
//                            }
//
//                            //시리즈 처리
//                            List<String> validatedDescriptionContentIdList = validateDuplicateSeriesIdWrapper.validateDuplicateSeriesId(descriptionList);
//                            List<String> validatedMoodContentIdList = validateDuplicateSeriesIdWrapper.validateDuplicateSeriesId(moodList);
//                            List<String> validatedPersonalContentIdList = validateDuplicateSeriesIdWrapper.validateDuplicateSeriesId(personalList);
//
//                            //시리즈 처리 통과한 리스트
//                            receivedDescriptionContentIds.setReceivedDescriptionDataList(validatedDescriptionContentIdList);
//                            receivedMoodContentIds.setReceivedMoodDataList(validatedMoodContentIdList);
//                            receivedPersonalContentIds.setReceivedPersonalDataList(validatedPersonalContentIdList);
//                        }else{
//                            System.out.println("데이터 null 들어옴");
//                            receivedDescriptionContentIds.setReceivedDescriptionDataList(new ArrayList<>());
//                            receivedMoodContentIds.setReceivedMoodDataList(new ArrayList<>());
//                            receivedPersonalContentIds.setReceivedPersonalDataList(new ArrayList<>());
//                        }
////                            System.out.println("비동기 블럭 내 subsr 사용 가능? = " + subsr);
//
//                            //시리즈 처리 통과한 리스트 getsubList처리해서 클라이언트에 리턴하는 리스트의 요소 개수 21개 맞추기
//
////                    System.out.println("시리즈 처리 통과한 description 리스트 = " + descriptionContentIds21);
//                            List<String> descriptionContentIds21 = setDataToSendToClient.getsubList(receivedDescriptionContentIds.getReceivedDescriptionDataList(), subsr);
//                            List<String> moodContentIds21 = setDataToSendToClient.getsubList(receivedMoodContentIds.getReceivedMoodDataList(), subsr);
//                            List<String> personalContentIds21 = setDataToSendToClient.getsubList(receivedPersonalContentIds.getReceivedPersonalDataList(), subsr);
//
//
//                            toClientListDto.setDescriptionListToClient(descriptionContentIds21.stream().map(setDataToSendToClient::buildToClient1stDto).toList());
//                            toClientListDto.setMoodListToClient(moodContentIds21.stream().map(setDataToSendToClient::buildToClient1stDto).toList());
//                            toClientListDto.setPersonalListToClient(personalContentIds21.stream().map(setDataToSendToClient::buildToClient1stDto).toList());
//                            toClientListDto.setPersonal_words((String) personalModelDataDto.getPersonalData().get(0));
//                            return Mono.just(MainResponseDto.builder()
//                                    .description_data(toClientListDto.getDescriptionListToClient())
//                                    .genre_data(toClientListDto.getMoodListToClient())
//                                    .personal_data(toClientListDto.getPersonalListToClient())
//                                    .personal_words(toClientListDto.getPersonal_words())
//                                    .build());
//
//
//                    })
//                    .onErrorResume(WebClientResponseException.class, error -> {
//                        System.err.println("HTTP error status: " + error.getStatusCode());
////                         HTTP 에러 발생 시 기본 응답 반환
//                        return Mono.just(MainResponseDto.builder()
//                                .description_data(setDataToSendToClient.sendFakeData(subsr))
//                                .genre_data(setDataToSendToClient.sendFakeData(subsr))
//                                .personal_data(setDataToSendToClient.sendFakeData(subsr))
//                                .personal_words("유쾌한, 사랑스러운, 흥미로운")
//                                .build());
//                    });
                        return MainResponseDto.builder()
                                .description_data(setDataToSendToClient.getsubList(this.getDescriptionContentIds21(), subsr).stream().map(setDataToSendToClient::buildToClient1stDto).toList())
                                 .genre_data(setDataToSendToClient.getsubList(this.getMoodContentIds21(), subsr).stream().map(setDataToSendToClient::buildToClient1stDto).toList())
                                .personal_data(setDataToSendToClient.getsubList(this.getPersonalContentIds21(), subsr).stream().map(setDataToSendToClient::buildToClient1stDto).toList())
                                .build();
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//
//        }

    }


}






