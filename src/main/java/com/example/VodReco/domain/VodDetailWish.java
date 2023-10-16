package com.example.VodReco.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

//프론트에서 들어오는 찜 데이터
//{wish:true}

@Getter
@Setter
public class VodDetailWish {
    @Column(nullable = false, unique = true)
    private String vcode;
    private Integer wish;

}
