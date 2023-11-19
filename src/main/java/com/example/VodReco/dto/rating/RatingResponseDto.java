package com.example.VodReco.dto.rating;

import com.example.VodReco.domain.UserRating;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RatingResponseDto {
    @Id
    @Column(nullable = false, unique = true)
    private String subsr;
    @Column(nullable = false, unique = true)
    private String contentId;
    private Integer rating;
    private String comment;

    public RatingResponseDto(){}


    @Builder
    public RatingResponseDto(String subsr, String contentId, Integer rating, String comment) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.rating = rating;
        this.comment = comment;
    }

    public UserRating toRatingEntity(RatingResponseDto ratingResponseDto) {
        return UserRating.builder()
                .subsr(subsr)
                .contentId(contentId)
                .rating(rating)
                .comment(comment)
                .build();
    }

}

