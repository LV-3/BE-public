package com.example.VodReco.dto.popular;

import lombok.Builder;

public class ViewPopularVodsDto {
    private String contentId;
    private String timeGroup;
    private Integer count;
    private String title;
    private String posterurl;

    @Builder
    public ViewPopularVodsDto(String contentId, String timeGroup, Integer count, String title, String posterurl) {
        this.contentId = contentId;
        this.timeGroup = timeGroup;
        this.count = count;
        this.title = title;
        this.posterurl = posterurl;
    }
}
