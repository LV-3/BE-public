package com.example.VodReco.dto;

import com.example.VodReco.domain.UserWish;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WishResponseDto {
    @Id
    @Column(nullable = false, unique = true)
    private String subsr;
    @Column(nullable = false, unique = true)
    private String contentId;
    private Integer wish;

    public WishResponseDto(){}

    //이건 받아오기만 하는 건데 builder가 필요할까 다시 고려하기
    @Builder
    public WishResponseDto(String subsr, String contentId, Integer wish) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.wish = wish;
    }


    public UserWish toWishEntity(WishResponseDto wishResponseDto) {
        return UserWish.builder()
                .subsr(subsr)
                .contentId(contentId)
                .wish(wish)
                .build();
    }
}

