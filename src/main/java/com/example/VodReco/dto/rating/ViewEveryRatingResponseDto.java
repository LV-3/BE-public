package com.example.VodReco.dto.rating;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ViewEveryRatingResponseDto {
    private String subsr;
    private Integer rating;
    private String rating_date;
    private String review;
    private String title;
    private String posterurl;

    @Builder
    public ViewEveryRatingResponseDto(String subsr, Integer rating, String rating_date, String review, String title, String posterurl) {
        this.subsr = subsr;
        this.rating = rating;
        this.rating_date = rating_date;
        this.review = review;
        this.title = title;
        this.posterurl = posterurl;
    }
}
