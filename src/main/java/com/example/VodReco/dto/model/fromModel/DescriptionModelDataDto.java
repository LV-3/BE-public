package com.example.VodReco.dto.model.fromModel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
@Component
public class DescriptionModelDataDto {
    JSONArray descriptonData;

    @Builder
    public DescriptionModelDataDto(JSONArray descriptonData) {
        this.descriptonData = descriptonData;
    }
}
