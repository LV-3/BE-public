package com.example.VodReco.service.mainPage.getReco;

import com.example.VodReco.domain.*;
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
import com.example.VodReco.mongoRepository.UserWatchTotalRepository;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.ContentIdToSeriesIdWrapper;
import com.example.VodReco.util.ForDeepFM.ToForDeepFMDtoWrapper;
import com.example.VodReco.util.StringToListWrapper;
import com.example.VodReco.util.ValidateDuplicateSeriesIdWrapper;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VodGetRecoServiceImpl implements VodGetRecoService{

    private final UserWatchTotalRepository userWatchTotalRepository;
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
    private final ReceivedDescriptionContentIds receivedDescriptionContentIds;
    private final ReceivedMoodContentIds receivedMoodContentIds;
    private final ReceivedPersonalContentIds receivedPersonalContentIds;

    private final ValidateDuplicateSeriesIdWrapper validateDuplicateSeriesIdWrapper;
    private final ContentIdToSeriesIdWrapper contentIdToSeriesIdWrapper;

    private final VodReloadServiceImpl vodReloadService;

    @Override
    public Mono<MainResponseDto> getAllContentIdsFromModel(String subsr) {
        //직전에 모든 추천결과 21개를 보지 않은 상태로 /main에 재진입했을 때 처음부터 다시 받아오기 위해 clear
//        TODO : 추후 삭제 (231206)
        if (!receivedDescriptionContentIds.getReceivedDescriptionDataList().isEmpty() || !receivedMoodContentIds.getReceivedMoodDataList().isEmpty() || !receivedPersonalContentIds.getReceivedPersonalDataList().isEmpty()) {
            receivedDescriptionContentIds.getReceivedDescriptionDataList().clear();
            receivedMoodContentIds.getReceivedMoodDataList().clear();
            receivedPersonalContentIds.getReceivedPersonalDataList().clear();
        }

        ToModelDto toModelDto = setDataForModel(subsr);

        WebClient webClient = WebClient.builder()
//                .baseUrl("http://1.220.201.108:8000")
                .baseUrl("lv3-loadbalancer-f-725358857.ap-northeast-2.elb.amazonaws.com")
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
                    for (int i = 0; i < descriptionModelDataDto.getDescriptonData().length(); i++) {
                        descriptionList.add((String) descriptionModelDataDto.getDescriptonData().get(i));
                    }
                    for (int j = 0; j < moodModelDataDto.getMoodData().length(); j++) {
                        moodList.add((String) moodModelDataDto.getMoodData().get(j));
                    }
                    for (int k = 0; k < personalModelDataDto.getPersonalData().length(); k++) {
                        personalList.add((String) personalModelDataDto.getPersonalData().get(k));
                    }

                    List<String> validatedDescriptionContentIdList = validateDuplicateSeriesIdWrapper.validateDuplicateSeriesId(descriptionList);
                    List<String> validatedMoodContentIdList = validateDuplicateSeriesIdWrapper.validateDuplicateSeriesId(descriptionList);
                    List<String> validatedPersonalContentIdList = validateDuplicateSeriesIdWrapper.validateDuplicateSeriesId(descriptionList);

                    receivedDescriptionContentIds.setReceivedDescriptionDataList(validatedDescriptionContentIdList);
                    receivedMoodContentIds.setReceivedMoodDataList(validatedMoodContentIdList);
                    receivedPersonalContentIds.setReceivedPersonalDataList(validatedPersonalContentIdList);

                    System.out.println("비동기 블럭 내 subsr 사용 가능? = " + subsr);

                    List<String> descriptionContentIds21 = this.getsubList(receivedDescriptionContentIds.getReceivedDescriptionDataList(), subsr);
                    List<String> moodContentIds21 = this.getsubList(receivedMoodContentIds.getReceivedMoodDataList(), subsr);
                    List<String> personalContentIds21 = this.getsubList(receivedPersonalContentIds.getReceivedPersonalDataList(), subsr);

//                    TODO : vodReloadService 삭제 시 내부 buildToClient1stDto 메서드 옮겨오기

                    // 결과 반환
//                    genre_data로 되어있는지? mood_data로 되어있는지? 프론트에 key값 물어보기
                    return Mono.just(MainResponseDto.builder()
                                            .description_data(descriptionContentIds21.stream().map(vodReloadService::buildToClient1stDto).toList())
                                            .genre_data(moodContentIds21.stream().map(vodReloadService::buildToClient1stDto).toList())
                                            .personal_data(personalContentIds21.stream().map(vodReloadService::buildToClient1stDto).toList())
//                            .description_data(vodReloadService.reloadRec(receivedDescriptionContentIds.getReceivedDescriptionDataList()))
//                            .genre_data(vodReloadService.reloadRec(receivedMoodContentIds.getReceivedMoodDataList()))
//                            .personal_data(vodReloadService.reloadRec(receivedPersonalContentIds.getReceivedPersonalDataList()))
                            .build());
                })

                .doOnError(error -> {
                    System.out.println(toModelDto);
                    System.err.println("에러 발생: " + error);
                });
    }

    //    TODO : access modifier private으로 바꿀 수 있으면 바꾸기
//    21개 개수 맞추는 메서드
    public List<String> getsubList(List<String> list, String subsr) {
        Set<String> set = new HashSet<>(list); //이론 상 이 상태에는 중복 없음. 이후 처리 위해 set으로 미리 변환한 것
        if (list.size() >= 21) {
            List<String> first21s = list.stream()
                    .limit(21)
                    .toList();
            return first21s;
        } else {
            while (set.size() < 21) {
//                전체 중복 방지 위해 set 사용
                set.add(this.getSortedByUserPreference(subsr).get(0));
                this.getSortedByUserPreference(subsr).remove(0);
            }
//            TODO : user_preference 데이터까지 전부 써도 21개가 안 되는 예외는 아직 처리 안 함
            List<String> first21s = set.stream().toList();
            return first21s;
        }
    }


    public List<String> getSortedByUserPreference(String subsr) {
        List<UserWatch> sortedUserWatchs = userWatchRepository.findBySubsrOrderByUserPreferenceDesc(subsr);
        return sortedUserWatchs.stream()
                .map(UserWatch::getContentId)
                .collect(Collectors.toList());
    }

    public ToModelDto setDataForModel(String subsr) {
        List<EveryDescriptionDto> descriptionResponseList = new ArrayList<>();
        List<EveryMoodDto> moodResponseList = new ArrayList<>();
        List<EveryPersonalDto> personalResponseList = new ArrayList<>();

        //mood는 null들어올 경우 NPE(231127)
        for (UserWatchTotal v : userWatchTotalRepository.findBySubsr(subsr)) {
            String contentId = v.getContentId();
            Vod byContentId = vodRepository.findByContentId(contentId);

            EveryDescriptionDto everyDescriptionDto;
            //시리즈물은 SMRY 대신 TITLE_SMRY 전송
            if (byContentId.getIsSeries() == 1) {
                everyDescriptionDto = EveryDescriptionDto.builder()
                        .content_id(contentId).description(vodtoVodDtoWrapper.toVodDto(byContentId).getTitleDescription())
                        .build();
            } else {
                everyDescriptionDto = EveryDescriptionDto.builder()
                        .content_id(contentId).description(vodtoVodDtoWrapper.toVodDto(byContentId).getDescription())
                        .build();
            }
            descriptionResponseList.add(everyDescriptionDto);

            EveryMoodDto everyMoodDto = EveryMoodDto.builder()
                    .content_id(contentId).mood(stringToListWrapper.stringToList(byContentId.getMood()))
                    .build();
            moodResponseList.add(everyMoodDto);
        }
        for(ForDeepFM f : forDeepFMRepository.findBySubsr(subsr)){
            ForDeepFMDto forDeepFMDto = toForDeepFMDtoWrapper.toForDeepFMDto(f);
            //liked가 1인 데이터만 personalResponseList에 담기
            if (forDeepFMDto.getLiked() == 1) {
            EveryPersonalDto everyPersonalDto = EveryPersonalDto.builder().subsr(subsr).content_id(forDeepFMDto.getContentId()).ct_cl(forDeepFMDto.getCategory())
                    .genre_of_ct_cl(forDeepFMDto.getGenre()).template_A_TopGroup(forDeepFMDto.getMood()).template_B_TopGroup(forDeepFMDto.getGpt_genres()).template_C_TopGroup(forDeepFMDto.getGpt_subjects())
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


