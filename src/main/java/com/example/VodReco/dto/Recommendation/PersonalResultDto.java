package com.example.VodReco.dto.Recommendation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PersonalResultDto {
    private String subsr;
    @JsonProperty("content_id")
    private List<String> contentId;
    private String personal_words;

    @Builder
    public PersonalResultDto(String subsr, List<String> contentId, String personal_words) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.personal_words = personal_words;
    }
}
