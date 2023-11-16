package com.example.VodReco.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GenreRequestDto {
    private String genre;

    @Builder
    public GenreRequestDto(String genre) {
        this.genre = genre;
    }
}
