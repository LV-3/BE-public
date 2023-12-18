package com.example.VodReco.dto.Recommendation.model.toModel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
public class EveryMoodDto {
//    @JsonProperty("content_id")
    private String content_id;
    private List<String> mood;
    @Builder
    public EveryMoodDto(String content_id, List<String> mood) {
        this.content_id = content_id;
        this.mood = mood;
    }
}
