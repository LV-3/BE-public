package com.example.VodReco.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Wish {
    @Id
    @Column(nullable = false, unique = true)
    private String userEmail;
    @Column(nullable = false, unique = true)
    private String vcode;
    private Integer wish;

    public Wish(){}

    //이건 받아오기만 하는 건데 builder가 필요할까 다시 고려하기
    @Builder
    public Wish(String userEmail, String vcode, Integer wish) {
        this.userEmail = userEmail;
        this.vcode = vcode;
        this.wish = wish;
    }

}
