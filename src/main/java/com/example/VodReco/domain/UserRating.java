package com.example.VodReco.domain;

import com.example.VodReco.dto.rating.RatingResponseDto;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false) // unique = true) wish, rating은 email 겹쳐도 됨. 사용자가 여러 개의 vod에 대한 평가 내림(231104)
    private String subsr;
    private String contentId;
    private Integer rating;
    @Column(nullable = true) // comment는 null 가능!
    private String comment;

    @Builder
    public UserRating(String subsr, String contentId, Integer rating, String comment) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.rating = rating;
        this.comment = comment;
    }
    //이건 당연히 private은 아님 외부에서 호출해다 쓰는 거니까
    public RatingResponseDto toRatingResponseDto(UserRating userRating) {
        return RatingResponseDto.builder()
                .subsr(subsr)
                .contentId(contentId)
                .rating(rating)//문제 발생: Rating 클래스 내부 필드 이름이 rating이라서 Rating rating의 인스턴스 이름과 겹침
                //UserRating으로 리팩토링해서 해결(231031)
                .comment(comment)
                .build();
    }
}
