package com.example.VodReco.dto.Recommendation.model.fromModel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
@Component
@Setter
public class DescriptionModelDataDto {
    JSONArray descriptonData;

    @Builder
    public DescriptionModelDataDto(JSONArray descriptonData) {
        this.descriptonData = descriptonData;
    }
}
