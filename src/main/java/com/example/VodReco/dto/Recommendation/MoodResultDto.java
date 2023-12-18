package com.example.VodReco.dto.Recommendation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MoodResultDto {
    private String subsr;
    @JsonProperty("content_id")
    private List<String> contentId;

    @Builder
    public MoodResultDto(String subsr, List<String> contentId) {
        this.subsr = subsr;
        this.contentId = contentId;
    }
}
