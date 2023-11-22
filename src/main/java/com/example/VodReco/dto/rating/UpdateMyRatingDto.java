package com.example.VodReco.dto.rating;

import com.example.VodReco.domain.UserRating;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateMyRatingDto {
    @Id
    @Column(nullable = false, unique = true)
    private String subsr;
    @Column(nullable = false, unique = true)
    private String contentId;
    private Integer rating;
    private String review;
    private String rating_date;

    //조회 속도 개선을 위해 추가(231122)
    private String title;
    private String posterurl;

    public UpdateMyRatingDto(){}


    @Builder
    public UpdateMyRatingDto(String subsr, String contentId, Integer rating, String review, String rating_date, String title, String posterurl) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.rating = rating;
        this.review = review;
        this.rating_date = rating_date;
        this.title = title;
        this.posterurl = posterurl;
    }

    public UserRating toUserRatingEntity(UpdateMyRatingDto updateMyRatingDto) {
        return UserRating.builder()
                .subsr(subsr)
                .contentId(contentId)
                .rating(rating)
                .review(review)
                .rating_date(rating_date)
                .title(title)
                .posterurl(posterurl)
                .build();
    }


}

