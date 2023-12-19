package com.example.VodReco.util.forRecommendation;

import com.example.VodReco.domain.Rec.PersonalResult;
import com.example.VodReco.dto.Recommendation.PersonalResultDto;
import com.example.VodReco.util.StringToListWrapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PersResultToPersResultDto {
    private final StringToListWrapper stringToListWrapper;

    public PersonalResultDto toPersResultDto(PersonalResult personalResult) {
        return this.personalResultToPersonalResultDto(personalResult);
    }

    private PersonalResultDto personalResultToPersonalResultDto(PersonalResult personalResult) {
        return PersonalResultDto.builder()
                .subsr(personalResult.getSubsr())
                .contentId(stringToListWrapper.stringToList(personalResult.getContentId()))
                .personal_words(personalResult.getPersonal_words())
                .build();
    }
}
