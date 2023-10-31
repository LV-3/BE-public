package com.example.VodReco.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Wish {
    @Id
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String vcode;
    private Integer wish;

    @Builder
    public Wish(String email, String vcode, Integer wish) {
        this.email = email;
        this.vcode = vcode;
        this.wish = wish;
    }

    public Wish() {

    }
}
