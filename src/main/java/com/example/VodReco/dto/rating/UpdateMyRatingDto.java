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

    public UpdateMyRatingDto(){}


    @Builder
    public UpdateMyRatingDto(String subsr, String contentId, Integer rating, String review, String rating_date) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.rating = rating;
        this.review = review;
        this.rating_date = rating_date;
    }

    public UserRating toUserRatingEntity(UpdateMyRatingDto updateMyRatingDto) {
        return UserRating.builder()
                .subsr(subsr)
                .contentId(contentId)
                .rating(rating)
                .review(review)
                .rating_date(rating_date)
                .build();
    }


}

