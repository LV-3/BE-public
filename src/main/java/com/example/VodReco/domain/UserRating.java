package com.example.VodReco.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class UserRating {
    @Id
    @Column(nullable = false, unique = true)
    private String userEmail;
    @Column(nullable = false, unique = true)
    private String vcode;
    private Integer rating;

    public UserRating(){}


    @Builder
    public UserRating(String userEmail, String vcode, Integer wish) {
        this.userEmail = userEmail;
        this.vcode = vcode;
        this.rating = wish;
    }

}

