package com.example.VodReco.dto.model.toModel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class EveryMoodDto {
    private String content_id;
    private List<String> mood;

    @Builder
    public EveryMoodDto(String content_id, List<String> mood) {
        this.content_id = content_id;
        this.mood = mood;
    }
}
