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

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class VodGetRecoServiceImpl implements VodGetRecoService {

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

    private final VodReloadServiceImpl vodReloadService;



    //테스트용
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

//        personalContentIds21.add("317");
//        personalContentIds21.add("318");
//        personalContentIds21.add("319");
//        personalContentIds21.add("320");
//        personalContentIds21.add("321");

        return this.personalContentIds21;
    }



    @Override
    public Mono<MainResponseDto> getAllContentIdsFromModel(String subsr) {
//    public MainResponseDto getAllContentIdsFromModel(String subsr) {
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
                            return Mono.just(MainResponseDto.builder()
                                    .description_data(descriptionContentIds21.stream().map(vodReloadService::buildToClient1stDto).toList())
                                    .genre_data(moodContentIds21.stream().map(vodReloadService::buildToClient1stDto).toList())
                                    .personal_data(personalContentIds21.stream().map(vodReloadService::buildToClient1stDto).toList())
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
                    System.out.println(toModelDto);
                    System.err.println("에러 발생: " + error);
                });
    }

    //    TODO : access modifier private으로 바꿀 수 있으면 바꾸기
//    21개 개수 맞추는 메서드
    public List<String> getsubList(List<String> list, String subsr) {
        Set<String> set = new HashSet<>(list); //이론상 이 상태에는 중복 없음. 이후 처리 위해 set으로 미리 변환한 것
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
        List<String> list = new ArrayList<>();
        List<UserWatch> sortedUserWatchs = userWatchRepository.findBySubsrOrderByUserPreferenceDesc(subsr);
        for (UserWatch s : sortedUserWatchs) {
            if (s.getContentId() == null) {
                list.add(validateDuplicateSeriesIdWrapper.convertToMinContentId(s.getSeriesId()));
            } else {
                list.add(s.getContentId());
            }
        }

        return list;
//               FIXME : userWatch에는 content_id가 없는 데이터가 많음. UserWatch대신 UserWatchTotal에서 조회한 뒤
//                ContentIdToSeriesIdWrapper → ValidateDuplicateSeriesId 내부의 convertToMinContentId에 집어넣고
//                리턴받은 content_id를 List에 add
//                FIXME : 2번째 방안 - userWatch for문 돌려서 if content_id null이면 getSeriesId해서 convertToMinContentId에 집어넣고 리턴받은 content_id를 List에 add.
//                 2번 방안으로 구현. UserWatchTotal은 너무 방대함. 조회 속도 위함

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
        for (ForDeepFM f : forDeepFMRepository.findBySubsr(subsr)) {
            ForDeepFMDto forDeepFMDto = toForDeepFMDtoWrapper.toForDeepFMDto(f);
            //liked가 1인 데이터만 personalResponseList에 담기
//            if (forDeepFMDto.getLiked() == 1) {
                EveryPersonalDto everyPersonalDto = EveryPersonalDto.builder().subsr(Integer.parseInt(subsr)).content_id(Integer.parseInt(forDeepFMDto.getContentId())).ct_cl(forDeepFMDto.getCategory())
                        .genre_of_ct_cl(forDeepFMDto.getGenre()).template_A_TopGroup(forDeepFMDto.getMood()).template_B_TopGroup(forDeepFMDto.getGpt_genres()).template_C_TopGroup(forDeepFMDto.getGpt_subjects())
                        .userPreference(forDeepFMDto.getUserPreference())
                        .build();
                personalResponseList.add(everyPersonalDto);
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
        descriptionModelDataDto.setDescriptonData(descriptionData);
        moodModelDataDto.setMoodData(moodData);
        personalModelDataDto.setPersonalData(personalData);
    }

}


