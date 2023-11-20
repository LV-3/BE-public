package com.example.VodReco.dto.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ToModelDto {
    private List<?> data;

    @Builder
    public ToModelDto(List<?> data) {
        this.data = data;
    }
}
