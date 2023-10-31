package com.example.VodReco.dto;

//프론트에서 들어오는 평점 데이터 매핑
//{"vcode":"20200620", "rating":"1~5"}
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequestDto {
    //    @Column(nullable = false, unique = true)
//    private String vcode;
    private Integer rating;
}
