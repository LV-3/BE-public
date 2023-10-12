package com.example.loginDemo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FavRating {
    @Id
    @Column(nullable = false)
    private String userEmail;
    private String title;
    private Integer Fav;
    private Integer Rating;
}
