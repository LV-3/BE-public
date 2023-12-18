package com.example.VodReco.util.forRecommendation;

import com.example.VodReco.domain.Rec.MoodResult;
import com.example.VodReco.dto.Recommendation.MoodResultDto;
import com.example.VodReco.util.StringToListWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoodResultToMoodResultDtoWrapper {
    private final StringToListWrapper stringToListWrapper;

    public MoodResultDto toMoodResultDto(MoodResult moodResult) {
        return this.moodResultToMoodResultDto(moodResult);
    }

    private MoodResultDto moodResultToMoodResultDto(MoodResult moodResult) {
        return MoodResultDto.builder()
                .subsr(moodResult.getSubsr())
                .contentId(stringToListWrapper.stringToList(moodResult.getContentId()))
                .build();
    }
}
