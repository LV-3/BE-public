package com.example.VodReco.util.ForDeepFM;

import com.example.VodReco.dto.Recommendation.model.toModel.ForDeepFMDto;
import com.example.VodReco.util.ListToStringWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ToForDeepFMWrapper {
    private final ListToStringWrapper listToStringWrapper;

    public ForDeepFM toForDeepFM(ForDeepFMDto forDeepFMDto) {
        return forDeepFMDtoToForDeepFM(forDeepFMDto);
    }

    private ForDeepFM forDeepFMDtoToForDeepFM(ForDeepFMDto forDeepFMDto) {
        return ForDeepFM.builder()

                .subsr(forDeepFMDto.getSubsr())
                .contentId(forDeepFMDto.getContentId())
                .category(forDeepFMDto.getCategory())
                .genre(forDeepFMDto.getGenre())

                .mood(listToStringWrapper.listToString(forDeepFMDto.getMood()))
                .gpt_genres(listToStringWrapper.listToString(forDeepFMDto.getGpt_genres()))
                .gpt_subjects(listToStringWrapper.listToString(forDeepFMDto.getGpt_subjects()))
                .userPreference(forDeepFMDto.getUserPreference())

                .build();

    }
}
