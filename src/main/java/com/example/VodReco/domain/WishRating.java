package com.example.VodReco.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class WishRating {
    @Id
    @Column(nullable = false)
    private String userEmail;
    private String vcode;
    private Integer wish;
    private Integer rating;
}
