package com.example.VodReco.dto.client;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class ToClient3rdDto {
    private ToClient2ndDto description_data;
    private ToClient2ndDto genre_data;
    private ToClient2ndDto personal_data;

    @Builder
    public ToClient3rdDto(ToClient2ndDto description_data, ToClient2ndDto genre_data, ToClient2ndDto personal_data) {
        this.description_data = description_data;
        this.genre_data = genre_data;
        this.personal_data = personal_data;
    }
}
