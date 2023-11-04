package com.example.VodReco.dto.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class EveryGenre {
    private String content_id;
    private List<String> genre;

    @Builder
    public EveryGenre(String content_id, List<String> genre) {
        this.content_id = content_id;
        this.genre = genre;
    }
}
