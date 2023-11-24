package com.example.VodReco.dto.mypage;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ViewMyWatchListDto {
    private String subsr;
    private String content_id;
    private String title;
    private Integer user_preference;
    private String posterurl;

    @Builder
    public ViewMyWatchListDto(String subsr, String contentId, String title, Integer user_preference, String posterurl) {
        this.subsr = subsr;
        this.content_id = contentId;
        this.title = title;
        this.user_preference = user_preference;
        this.posterurl = posterurl;
    }

}

