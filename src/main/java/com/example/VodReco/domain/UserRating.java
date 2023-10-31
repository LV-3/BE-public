package com.example.VodReco.domain;

import com.example.VodReco.dto.RatingResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Rating {
    @Id
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String vcode;
    private Integer rating;

    @Builder
    private Rating(String email, String vcode, Integer rating) {
        this.email = email;
        this.vcode = vcode;
        this.rating = rating;
    }

    private RatingResponseDto toRatingResponseDto(Rating rating) {
        return RatingResponseDto.builder()
                .email(email)
                .vcode(vcode)
                .rating(rating)//문제 발생: Rating 클래스 내부 필드 이름이 rating이라서 Rating rating의 인스턴스 이름과 겹침
                //대대적으로 UserWish, UserRating으로 리팩토링 고려(231031)
                .build();
    }
}
