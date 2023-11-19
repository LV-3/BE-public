package com.example.VodReco.dto.wish;

import com.example.VodReco.domain.UserWish;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateMyWishResponseDto {
    @Id
    @Column(nullable = false, unique = true)
    private String subsr;
    @Column(nullable = false, unique = true)
    private String contentId;
    private Integer wish;

    public UpdateMyWishResponseDto(){}

    @Builder
    public UpdateMyWishResponseDto(String subsr, String contentId, Integer wish) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.wish = wish;
    }

    public UserWish toWishEntity(UpdateMyWishResponseDto updateMyWishResponseDto) {
        return UserWish.builder()
                .subsr(subsr)
                .contentId(contentId)
                .wish(wish)
                .build();
    }
}

