package com.example.VodReco.util.ForDeepFM;

import com.example.VodReco.domain.ForDeepFM;
import com.example.VodReco.dto.model.toModel.ForDeepFMDto;
import com.example.VodReco.util.StringToListWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToForDeepFMDtoWrapper {
    private final StringToListWrapper stringToListWrapper;

    public ForDeepFMDto toForDeepFMDto(ForDeepFM forDeepFM) {
        return forDeepFMDtotoForDeepFMDto(forDeepFM);
    }

    private ForDeepFMDto forDeepFMDtotoForDeepFMDto(ForDeepFM forDeepFM) {
        return ForDeepFMDto.builder()

                .subsr(forDeepFM.getSubsr())
                .contentId(forDeepFM.getContentId())
                .category(forDeepFM.getCategory())
                .genre(forDeepFM.getGenre())

                .mood(stringToListWrapper.stringToList(forDeepFM.getMood()))
                .gpt_genres(stringToListWrapper.stringToList(forDeepFM.getGpt_genres()))
                .gpt_subjects(stringToListWrapper.stringToList(forDeepFM.getGpt_subjects()))
                .userPreference(forDeepFM.getUserPreference())

                .build();

    }
}