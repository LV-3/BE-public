package com.example.VodReco.dto;

import lombok.Getter;
import lombok.Setter;

//프론트에서 들어오는 찜 데이터 매핑
//{"vcode":"20200620", "rating":1}

@Getter
@Setter
public class WishRequestDto {
    //    @Column(nullable = false, unique = true)
//    private String vcode;
    private Integer wish;

}
