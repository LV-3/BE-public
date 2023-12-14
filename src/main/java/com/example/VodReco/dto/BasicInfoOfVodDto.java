package com.example.VodReco.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BasicInfoOfVodDto {
    private String content_id;
    private String posterurl;
    private String title;

    @Builder
    public BasicInfoOfVodDto(String contentId, String posterurl, String title) {
        this.content_id = contentId;
        this.posterurl = posterurl;
        this.title = title;
    }
}
