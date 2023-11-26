package com.example.VodReco.util.PersonalWatch;

import com.example.VodReco.domain.PersonalWatch;
import com.example.VodReco.dto.model.toModel.PersonalWatchDto;
import com.example.VodReco.util.ListToStringWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ToPersonalWatchWrapper {
    private final ListToStringWrapper listToStringWrapper;

    public PersonalWatch toPersonalWatch(PersonalWatchDto personalWatchDto) {
        return PersonalWatchDtotoPersonalWatch(personalWatchDto);
    }

    private PersonalWatch PersonalWatchDtotoPersonalWatch(PersonalWatchDto personalWatchDto) {
        return PersonalWatch.builder()

                .subsr(personalWatchDto.getSubsr())
                .contentId(personalWatchDto.getContentId())
                .category(personalWatchDto.getCategory())
                .genre(personalWatchDto.getGenre())

                .mood(listToStringWrapper.listToString(personalWatchDto.getMood()))
                .gpt_genres(listToStringWrapper.listToString(personalWatchDto.getGpt_genres()))
                .gpt_subjects(listToStringWrapper.listToString(personalWatchDto.getGpt_subjects()))
                .liked(personalWatchDto.getLiked())

                .build();

    }
}
