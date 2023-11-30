package com.example.VodReco.dto.model.fromModel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
@Setter
public class PersonalModelDataDto {
    private JSONArray personalData;

    @Builder
    public PersonalModelDataDto(JSONArray personalData) {
        this.personalData = personalData;
    }
}
