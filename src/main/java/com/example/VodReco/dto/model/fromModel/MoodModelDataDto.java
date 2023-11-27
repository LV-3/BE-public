package com.example.VodReco.dto.model.fromModel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class MoodModelDataDto {
    private JSONArray moodData;

    @Builder
    public MoodModelDataDto(JSONArray moodData) {
        this.moodData = moodData;
    }
}
