package com.example.VodReco.util.PersonalWatch;

import com.example.VodReco.domain.PersonalWatch;
import com.example.VodReco.dto.model.toModel.PersonalWatchDto;
import com.example.VodReco.util.StringToListWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToPersonalWatchDtoWrapper {
    private final StringToListWrapper stringToListWrapper;

    public PersonalWatchDto toPersonalWatchDto(PersonalWatch personalWatch) {
        return PersonalWatchtoPersonalWatchDto(personalWatch);
    }

    private PersonalWatchDto PersonalWatchtoPersonalWatchDto(PersonalWatch personalWatch) {
        return PersonalWatchDto.builder()

                .subsr(personalWatch.getSubsr())
                .contentId(personalWatch.getContentId())
                .category(personalWatch.getCategory())
                .genre(personalWatch.getGenre())

                .mood(stringToListWrapper.stringToList(personalWatch.getMood()))
                .gpt_genres(stringToListWrapper.stringToList(personalWatch.getGpt_genres()))
                .gpt_subjects(stringToListWrapper.stringToList(personalWatch.getGpt_subjects()))
                .liked(personalWatch.getLiked())

                .build();

    }
}