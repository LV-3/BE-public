package com.example.VodReco.dto.mypage;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class ViewMyRatingListDto {
    private String subsr;
    private String contentId;
    private String title;
    private String posterurl;
    private Integer rating;
    private String rating_date;
    private String review;

    @Builder
    public ViewMyRatingListDto(String subsr,String contentId, String title,String posterurl, Integer rating, String rating_date, String review) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.title =title;
        this.posterurl = posterurl;
        this.rating = rating;
        this.rating_date = rating_date;
        this.review = review;
    }
}
