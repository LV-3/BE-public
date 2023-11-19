package com.example.VodReco.dto.wish;

import com.example.VodReco.dto.rating.ViewEveryRatingResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ViewMyWishResponseDto {
    private Integer wish;

    @Builder
    public ViewMyWishResponseDto(Integer wish) {
        this.wish = wish;
    }
}
