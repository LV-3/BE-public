package com.example.VodReco.dto.Recommendation.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ToClient1stDto {
    @JsonProperty("content_id")
    private String contentId;
    private String posterurl;
    private String title;
//    private List<String> tags;

    @Builder
    public ToClient1stDto(String contentId, String posterurl, String title) {
        this.contentId = contentId;
        this.posterurl = posterurl;
        this.title = title;
//        this.tags = tags;
    }
}
