package com.example.VodReco.domain;

import com.example.VodReco.dto.RatingResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_rating")
@Getter
@NoArgsConstructor
public class UserRating {
    @Id
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String vcode;
    @Builder
    private UserRating(String email, String vcode, Integer rating) {
        this.email = email;
        this.vcode = vcode;
        this.rating = rating;
    }

    private Integer rating;

    //이건 당연히 private은 아님 외부에서 호출해다 쓰는 거니까
    public RatingResponseDto toRatingResponseDto(UserRating userRating) {
        return RatingResponseDto.builder()
                .email(email)
                .vcode(vcode)
                .rating(rating)//문제 발생: Rating 클래스 내부 필드 이름이 rating이라서 Rating rating의 인스턴스 이름과 겹침
                //UserRating으로 리팩토링해서 해결(231031)
                .build();
    }
}
