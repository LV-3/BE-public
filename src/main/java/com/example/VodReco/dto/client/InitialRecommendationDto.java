package com.example.VodReco.dto.client;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
public class MainResponseDto {
    private List<ToClient1stDto> description_data;
    private List<ToClient1stDto> genre_data;
    private List<ToClient1stDto> personal_data;

    @Builder
    public MainResponseDto(List<ToClient1stDto> description_data, List<ToClient1stDto> genre_data, List<ToClient1stDto> personal_data) {
        this.description_data = description_data;
        this.genre_data = genre_data;
        this.personal_data = personal_data;
    }
}
