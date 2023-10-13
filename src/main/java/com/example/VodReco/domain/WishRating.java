package com.example.VodReco.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class WishRating {
    @Id
    @Column(nullable = false)
    private String userEmail;
    private String title;
    private Integer Wish;
    private Integer Rating;
}
