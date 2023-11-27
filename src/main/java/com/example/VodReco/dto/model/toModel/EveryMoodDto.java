package com.example.VodReco.dto.model.toModel;

import com.fasterxml.jackson.annotation.JsonProperty;
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
//    private List<String> mood;
    private String mood;

    @Builder

    public EveryMoodDto(String content_id, String mood) {
        this.content_id = content_id;
        this.mood = mood;
    }
}
