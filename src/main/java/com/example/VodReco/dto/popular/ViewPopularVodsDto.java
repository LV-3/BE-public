package com.example.VodReco.dto.popular;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ViewPopularVodsDto {
    private String contentId;
    private String title;
    private String posterurl;

    @Builder
    public ViewPopularVodsDto(String contentId, String title, String posterurl) {
        this.contentId = contentId;
        this.title = title;
        this.posterurl = posterurl;
    }
}
