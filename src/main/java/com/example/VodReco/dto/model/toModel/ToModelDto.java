package com.example.VodReco.dto.model.toModel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
public class ToModelDto {
    private List<EveryDescriptionDto> description_data;
    private List<EveryMoodDto> mood_data;
    private List<EveryPersonalDto> personal_data;

    @Builder
    public ToModelDto(List<EveryDescriptionDto> description_data, List<EveryMoodDto> mood_data, List<EveryPersonalDto> personal_data) {
        this.description_data = description_data;
        this.mood_data = mood_data;
        this.personal_data = personal_data;
    }
}
