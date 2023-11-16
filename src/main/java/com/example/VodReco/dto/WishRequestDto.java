package com.example.VodReco.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//프론트에서 들어오는 찜 데이터 매핑
//{"model":"description", "content_id":"20200620", "wish":1}

@Getter
@Setter
@RequiredArgsConstructor

public class WishRequestDto {
    private String subsr;
    @JsonProperty("content_id")
    private String contentId;
    private Integer wish;

}
