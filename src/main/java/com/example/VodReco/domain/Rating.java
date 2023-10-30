package com.example.VodReco.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Rating {
    @Id
    @Column(nullable = false, unique = true)
    private String userEmail;
    @Column(nullable = false, unique = true)
    private String vcode;
    private Integer rating;

    public Rating(){}


    @Builder
    public Rating(String userEmail, String vcode, Integer rating) {
        this.userEmail = userEmail;
        this.vcode = vcode;
        this.rating = rating;
    }

}

