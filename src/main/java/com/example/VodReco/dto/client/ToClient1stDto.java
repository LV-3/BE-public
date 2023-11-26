package com.example.VodReco.dto.client;

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
    private List<String> mood;
    private List<String> gpt_genres;
    private List<String> gpt_subjects;

    @Builder
    public ToClient1stDto(String contentId, String posterurl, String title, List<String> mood, List<String> gpt_subjects, List<String> gpt_genres) {
        this.contentId = contentId;
        this.posterurl = posterurl;
        this.title = title;
        this.mood = mood;
        this.gpt_genres = gpt_genres;
        this.gpt_subjects = gpt_subjects;
    }
}
