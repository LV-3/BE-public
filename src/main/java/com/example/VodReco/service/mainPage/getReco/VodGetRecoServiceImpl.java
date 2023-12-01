package com.example.VodReco.service.mainPage.getReco;

import com.example.VodReco.domain.ForDeepFM;
import com.example.VodReco.domain.UserWatch;
import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.client.MainResponseDto;
import com.example.VodReco.dto.model.fromModel.DescriptionModelDataDto;
import com.example.VodReco.dto.model.fromModel.MoodModelDataDto;
import com.example.VodReco.dto.model.fromModel.PersonalModelDataDto;
import com.example.VodReco.dto.model.fromModel.receivedContentIds.ReceivedDescriptionContentIds;
import com.example.VodReco.dto.model.fromModel.receivedContentIds.ReceivedMoodContentIds;
import com.example.VodReco.dto.model.fromModel.receivedContentIds.ReceivedPersonalContentIds;
import com.example.VodReco.dto.model.toModel.*;
import com.example.VodReco.mongoRepository.ForDeepFMRepository;
import com.example.VodReco.mongoRepository.UserWatchRepository;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.ForDeepFM.ToForDeepFMDtoWrapper;
import com.example.VodReco.util.StringToListWrapper;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VodGetRecoServiceImpl implements VodGetRecoService{

    private final UserWatchRepository userWatchRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;
    private final ForDeepFMRepository forDeepFMRepository;
    private final ToForDeepFMDtoWrapper toForDeepFMDtoWrapper;
    private final VodRepository vodRepository;
    private final StringToListWrapper stringToListWrapper;

    private final DescriptionModelDataDto descriptionModelDataDto;
    private final MoodModelDataDto moodModelDataDto;
    private final PersonalModelDataDto personalModelDataDto;

    //새로고침 눌리면 사용하던 모든 전역변수 List clearAll 필수(231126)
//    private final List<String> descriptionDataList;
//    private final List<String> moodDataList;
//    private final List<String> personalDataList;
    private final ReceivedDescriptionContentIds receivedDescriptionContentIds;
    private final ReceivedMoodContentIds receivedMoodContentIds;
    private final ReceivedPersonalContentIds receivedPersonalContentIds;

    private final VodReloadServiceImpl vodReloadService;

    @Override
    public Mono<MainResponseDto> getAllContentIdsFromModel(String subsr) {
        //직전에 모든 추천결과 21개를 보지 않은 상태로 /main에 재진입했을 때 처음부터 다시 받아오기 위해 clear
        if (!receivedDescriptionContentIds.getReceivedDescriptionDataList().isEmpty() || !receivedMoodContentIds.getReceivedMoodDataList().isEmpty() || !receivedPersonalContentIds.getReceivedPersonalDataList().isEmpty()) {
            receivedDescriptionContentIds.getReceivedDescriptionDataList().clear();
            receivedMoodContentIds.getReceivedMoodDataList().clear();
            receivedPersonalContentIds.getReceivedPersonalDataList().clear();
        }

        ToModelDto toModelDto = setDataForModel(subsr);

        WebClient webClient = WebClient.builder()
                .baseUrl("http://1.220.201.108:8000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.post()
                .uri("/prcs_models")
                .body(Mono.just(toModelDto), ToModelDto.class)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(result -> {
                    // 비동기 작업 완료 후 처리할 로직
                    parse(result);

                    List<String> descriptionList = new ArrayList<>();
                    List<String> moodList = new ArrayList<>();
                    List<String> personalList = new ArrayList<>();
                    for (int i = 0; i < 21; i++) {
                        descriptionList.add((String) descriptionModelDataDto.getDescriptonData().get(i));
                        moodList.add((String) moodModelDataDto.getMoodData().get(i));
                        personalList.add((String) personalModelDataDto.getPersonalData().get(i));
                    }
                        receivedDescriptionContentIds.setReceivedDescriptionDataList(descriptionList);
                        receivedMoodContentIds.setReceivedMoodDataList(moodList);
                        receivedPersonalContentIds.setReceivedPersonalDataList(personalList);

                    // 결과 반환
                    return Mono.just(MainResponseDto.builder()
                            .description_data(vodReloadService.reloadRec(receivedDescriptionContentIds.getReceivedDescriptionDataList()))
                            .genre_data(vodReloadService.reloadRec(receivedMoodContentIds.getReceivedMoodDataList()))
                            .personal_data(vodReloadService.reloadRec(receivedPersonalContentIds.getReceivedPersonalDataList()))
                            .build());
                })
                .doOnError(error -> {
                    System.out.println(toModelDto);
                    System.err.println("에러 발생: " + error);
                });
    }


    public ToModelDto setDataForModel(String subsr) {
        List<EveryDescriptionDto> descriptionResponseList = new ArrayList<>();
        List<EveryMoodDto> moodResponseList = new ArrayList<>();
        List<EveryPersonalDto> personalResponseList = new ArrayList<>();

        //mood는 null들어올 경우 NPE(231127)
        for (UserWatch v : userWatchRepository.findBySubsr(subsr)) {
            String contentId = v.getContentId();
            Vod byContentId = vodRepository.findByContentId(contentId);

            EveryDescriptionDto everyDescriptionDto;
            if (byContentId.getIsSeries() == 1) {
                everyDescriptionDto = EveryDescriptionDto.builder().content_id(contentId).description(vodtoVodDtoWrapper.toVodDto(byContentId).getTitleDescription()).build();
            } else {
                everyDescriptionDto = EveryDescriptionDto.builder().content_id(contentId).description(vodtoVodDtoWrapper.toVodDto(byContentId).getDescription()).build();
            }
            descriptionResponseList.add(everyDescriptionDto);

            EveryMoodDto everyMoodDto = EveryMoodDto.builder().content_id(contentId).mood(stringToListWrapper.stringToList(byContentId.getMood())).build();
            moodResponseList.add(everyMoodDto);
        }
        for(ForDeepFM f : forDeepFMRepository.findBySubsr(subsr)){
            ForDeepFMDto forDeepFMDto = toForDeepFMDtoWrapper.toForDeepFMDto(f);
            //liked가 1인 데이터만 personalResponseList에 담기
            if (forDeepFMDto.getLiked() == 1) {
            EveryPersonalDto everyPersonalDto = EveryPersonalDto.builder().subsr(subsr).content_id(forDeepFMDto.getContentId()).ct_cl(forDeepFMDto.getCategory())
                    .genre_of_ct_cl(forDeepFMDto.getGenre()).template_A(forDeepFMDto.getMood()).template_B(forDeepFMDto.getGpt_genres()).template_C(forDeepFMDto.getGpt_subjects())
                    .liked(forDeepFMDto.getLiked())
                    .build();
            personalResponseList.add(everyPersonalDto);
            }
        }
        return ToModelDto.builder()
                .description_data(descriptionResponseList)
                .mood_data(moodResponseList)
                .personal_data(personalResponseList)
                .build();

    }

    public void parse(String recoResult) {
        JSONObject jsonObject = new JSONObject(recoResult);
        JSONArray descriptionData = jsonObject.getJSONArray("description_data");
        System.out.println("descriptionData 확인 = " + descriptionData.toString());
        JSONArray moodData = jsonObject.getJSONArray("mood_data");
        JSONArray personalData = jsonObject.getJSONArray("personal_data");
//        DescriptionModelDataDto descriptionModelDataDto = DescriptionModelDataDto.builder().descriptonData(descriptionData).build();
//        MoodModelDataDto moodDataDto = MoodModelDataDto.builder().moodData(moodData).build();
//        PersonalModelDataDto personalDataDto = PersonalModelDataDto.builder().personalData(personalData).build();
        descriptionModelDataDto.setDescriptonData(descriptionData);
        moodModelDataDto.setMoodData(moodData);
        personalModelDataDto.setPersonalData(personalData);
    }

}
