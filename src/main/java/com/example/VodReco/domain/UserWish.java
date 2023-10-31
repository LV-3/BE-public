package com.example.VodReco.domain;

import com.example.VodReco.dto.WishResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_wish")
public class UserWish {
    @Id
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String vcode;
    private Integer wish;

    @Builder
    public UserWish(String email, String vcode, Integer wish) {
        this.email = email;
        this.vcode = vcode;
        this.wish = wish;
    }

    public UserWish() {

    }

    public WishResponseDto toWishResponseDto(UserWish userWish) {
        return WishResponseDto.builder()
                .email(email)
                .vcode(vcode)
                .wish(wish)
                .build();
    }
}
