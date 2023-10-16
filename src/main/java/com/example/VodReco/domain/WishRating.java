package com.example.VodReco.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@RequiredArgsConstructor
public class WishRating {
    @Id
    @Column(nullable = false)
    private String userEmail;
    private String vcode;
    private Integer wish;
    private Integer rating;
}
