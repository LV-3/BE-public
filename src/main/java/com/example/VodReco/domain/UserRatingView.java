package com.example.VodReco.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor
public class UserRatingView {
    @Field(name = "unique_id")
    private String uniqueId;
    // wish, rating은 subsr 겹쳐도 됨. 사용자가 여러 개의 vod에 대한 평가 내림(231104)
    private String subsr;
    private String contentId;
    private Integer rating;
//    @Column(nullable = true) // review는 null 가능!
    private String review;
    private String rating_date;
    private String title;
    private String posterurl;

    @Builder
    public UserRatingView(String uniqueId, String subsr, String contentId, Integer rating, String review, String rating_date, String title, String posterurl) {
        this.uniqueId = uniqueId;
        this.subsr = subsr;
        this.contentId = contentId;
        this.rating = rating;
        this.review = review;
        this.rating_date = rating_date;
        this.title = title;
        this.posterurl = posterurl;
    }
}
