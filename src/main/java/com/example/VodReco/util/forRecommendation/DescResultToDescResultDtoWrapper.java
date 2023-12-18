package com.example.VodReco.util.forRecommendation;


import com.example.VodReco.domain.Rec.DescriptionResult;
import com.example.VodReco.dto.Recommendation.DescriptionResultDto;
import com.example.VodReco.util.StringToListWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DescResultToDescResultDtoWrapper {
    private final StringToListWrapper stringToListWrapper;

    public DescriptionResultDto toDescResultDto(DescriptionResult descriptionResult) {
        return this.descResultToDescResultDto(descriptionResult);
    }

    private DescriptionResultDto descResultToDescResultDto(DescriptionResult descriptionResult) {
        return DescriptionResultDto.builder()
                .subsr(descriptionResult.getSubsr())
                .contentId(stringToListWrapper.stringToList(descriptionResult.getContentId()))
                .build();
    }
}
