package com.example.VodReco.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class UserWish {
    @Id
    @Column(nullable = false, unique = true)
    private String userEmail;
    @Column(nullable = false, unique = true)
    private String vcode;
    private Integer wish;

    public UserWish(){}

    //이건 받아오기만 하는 건데 builder가 필요할까 다시 고려하기
    @Builder
    public UserWish(String userEmail, String vcode, Integer wish) {
        this.userEmail = userEmail;
        this.vcode = vcode;
        this.wish = wish;
    }

}
