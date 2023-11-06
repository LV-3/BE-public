package com.example.VodReco.dto;

import com.example.VodReco.domain.UserRating;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RatingResponseDto {
    @Id
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String contentId;
    private Integer rating;

    public RatingResponseDto(){}


    @Builder
    public RatingResponseDto(String email, String contentId, Integer rating) {
        this.email = email;
        this.contentId = contentId;
        this.rating = rating;
    }

    public UserRating toRatingEntity(RatingResponseDto ratingResponseDto) {
        return UserRating.builder()
                .email(email)
                .contentId(contentId)
                .rating(rating)
                .build();
    }

}

