package com.example.VodReco.dto;

import com.example.VodReco.domain.UserWish;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WishResponseDto {
    @Id
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String vcode;
    private Integer wish;

    public WishResponseDto(){}

    //이건 받아오기만 하는 건데 builder가 필요할까 다시 고려하기
    @Builder
    public WishResponseDto(String email, String vcode, Integer wish) {
        this.email = email;
        this.vcode = vcode;
        this.wish = wish;
    }


    public UserWish toWishEntity(WishResponseDto wishResponseDto) {
        return UserWish.builder()
                .email(email)
                .vcode(vcode)
                .wish(wish)
                .build();
    }
}

