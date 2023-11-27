package com.example.VodReco.dto.rating;

//프론트에서 들어오는 평점 데이터 매핑
//{"model":"description", "content_id":"20200620", "rating":"1~5"}
import lombok.Getter;

@Getter
public class UpdateMyRatingRequestDto {

    private String subsr;
    private Integer rating;
    private String review;
    private String rating_date;
}
