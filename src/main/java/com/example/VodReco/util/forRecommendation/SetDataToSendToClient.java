package com.example.VodReco.util.forRecommendation;

import com.example.VodReco.domain.UserWatch;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.client.ToClient1stDto;
import com.example.VodReco.dto.model.fromModel.DescriptionModelDataDto;
import com.example.VodReco.dto.model.fromModel.MoodModelDataDto;
import com.example.VodReco.dto.model.fromModel.PersonalModelDataDto;
import com.example.VodReco.mongoRepository.UserWatchRepository;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.service.mainPage.getReco.VodGetRecoServiceImpl;
import com.example.VodReco.util.CheckNotTranslatedTemplatedWords;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import com.example.VodReco.util.series.ValidateDuplicateSeriesIdWrapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SetDataToSendToClient {
    private final UserWatchRepository userWatchRepository;
    private final ValidateDuplicateSeriesIdWrapper validateDuplicateSeriesIdWrapper;

    private final DescriptionModelDataDto descriptionModelDataDto;
    private final MoodModelDataDto moodModelDataDto;
    private final PersonalModelDataDto personalModelDataDto;

    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;
    private final VodRepository vodRepository;
    private final CheckNotTranslatedTemplatedWords checkNotTranslatedTemplatedWords;

    //    TODO : access modifier private으로 바꿀 수 있으면 바꾸기
//    21개 개수 맞추는 메서드
    public List<String> getsubList(List<String> list, String subsr) {
        if (list.size() >= 21) {
//            List<String> first21s = list.stream()
//                    .limit(21)
//                    .toList();
//            return first21s;
            return list.subList(0, 21);
        } else {
        Set<String> set = new HashSet<>(list); //이론상 이 상태에는 중복 없음. 이후 처리 위해 set으로 미리 변환한 것
            while (set.size() < 21) {
//                전체 중복 방지 위해 set 사용
                set.addAll(this.getSortedByUserPreference(subsr));
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

    public void parse(String recResult, String subsr) {
        if (recResult != null) {
            JSONObject jsonObject = new JSONObject(recResult.trim());
            JSONArray descriptionData = jsonObject.getJSONArray("description_data");
            System.out.println("descriptionData 확인 = " + descriptionData.toString());
            JSONArray moodData = jsonObject.getJSONArray("mood_data");
            System.out.println("moodData 확인 = " + moodData.toString());
            JSONArray personalData = jsonObject.getJSONArray("personal_data");
            System.out.println("personalData 확인 = " + personalData.toString());

            descriptionModelDataDto.setDescriptonData(descriptionData);
            moodModelDataDto.setMoodData(moodData);
            personalModelDataDto.setPersonalData(personalData);
        }
//        } else {
//            descriptionModelDataDto.setDescriptonData(this.getsubList(new ArrayList<>(), subsr));
//            descriptionModelDataDto.setDescriptonData(this.getsubList(new ArrayList<>(), subsr));
//            descriptionModelDataDto.setDescriptonData(this.getsubList(new ArrayList<>(), subsr));
//        }
    }

    public ToClient1stDto buildToClient1stDto(String contentId){
        VodDto vodDto = vodtoVodDtoWrapper.toVodDto(vodRepository.findByContentId(contentId));
        if (vodDto.getUniqueTemplates() != null) {
            //uniqueTemplates의 요소 하나씩 get해 번역 검사 돌린 후 tags 리스트에 add하기.
            //tags리스트의 요소 개수는 최대 3개까지 && uniqueTemplates의 요소 개수 이하의 i에서 번역검사 + add 반복.
            List<String> tags = new ArrayList<>();
            int i = 0;
            while (tags.size() <= 3 && i < vodDto.getUniqueTemplates().size()) {
                if (checkNotTranslatedTemplatedWords.checkIfNotTranslated(vodDto.getUniqueTemplates().get(i))) {
                    tags.add(vodDto.getUniqueTemplates().get(i));
                }
                i++;
            }
            return ToClient1stDto.builder().contentId(contentId).posterurl(vodDto.getPosterurl()).title(vodDto.getTitle())
                    .tags(tags)
                    .build();
            //uniqueTemplates가 null인 경우 빈 리스트 리턴
        } else {
            return ToClient1stDto.builder().contentId(contentId).posterurl(vodDto.getPosterurl()).title(vodDto.getTitle())
                    .tags(new ArrayList<>())
                    .build();
        }
    }

}
