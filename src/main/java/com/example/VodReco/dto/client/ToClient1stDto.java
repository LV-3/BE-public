package com.example.VodReco.dto.client;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ToClient1stDto {
    private String content_id;
    private String posterurl;
    private List<String> mood;

    @Builder
    public ToClient1stDto(String contentId, String posterurl, List<String> mood) {
        this.content_id = contentId;
        this.posterurl = posterurl;
        this.mood = mood;
    }
}
