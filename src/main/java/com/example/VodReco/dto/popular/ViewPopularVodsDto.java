package com.example.VodReco.dto.popular;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ViewPopularVodsDto {
    @JsonProperty("content_id")
    private String contentId;
    private String title;
    private String posterurl;
    private String timeGroup;

    @Builder
    public ViewPopularVodsDto(String contentId, String title, String posterurl, String timeGroup) {
        this.contentId = contentId;
        this.title = title;
        this.posterurl = posterurl;
        this.timeGroup = timeGroup;
    }
}
