package com.example.VodReco.dto.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//@NoArgsConstructor: Builder와 충돌 에러(231104)
public class EveryDescription {
    private String content_id; // 예의주시하기
    private String description;

    @Builder
    public EveryDescription(String content_id, String description) {
        this.content_id = content_id;
        this.description = description;
    }
}
