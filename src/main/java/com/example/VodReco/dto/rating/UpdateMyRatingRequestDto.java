package com.example.VodReco.dto.rating;

//프론트에서 들어오는 평점 데이터 매핑
//{"model":"description", "content_id":"20200620", "rating":"1~5"}
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMyRatingRequestDto {

    private String subsr;
    @JsonProperty("content_id")
    private String contentId;
    private Integer rating;
    private String review;
    private String rating_date;
}
