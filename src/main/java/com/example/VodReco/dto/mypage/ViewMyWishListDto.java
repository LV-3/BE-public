package com.example.VodReco.dto.mypage;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ViewMyWishListDto {
    private String subsr;
    private String content_id;
    private String title;
    private Integer wish;
    private String posterurl;

    @Builder
    public ViewMyWishListDto(String subsr,String contentId, String title, Integer wish, String posterurl) {
        this.subsr = subsr;
        this.content_id = contentId;
        this.title = title;
        this.wish = wish;
        this.posterurl = posterurl;

    }

}
