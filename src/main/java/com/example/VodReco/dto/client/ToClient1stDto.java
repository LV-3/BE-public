package com.example.VodReco.dto.client;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ToClient1stDto {
    private String contentId;
    private String posterurl;

    @Builder
    public ToClient1stDto(String contentId, String posterurl) {
        this.contentId = contentId;
        this.posterurl = posterurl;
    }
}
