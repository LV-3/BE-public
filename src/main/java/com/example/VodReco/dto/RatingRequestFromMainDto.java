package com.example.VodReco.dto;

//프론트에서 들어오는 평점 데이터 매핑
//{"model":"description", "content_id":"20200620", "rating":"1~5"}
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequestFromMainDto {
    private String model;
    @JsonProperty("content_id")
    private String contentId;
    private Integer rating;
    private String comment;
}
