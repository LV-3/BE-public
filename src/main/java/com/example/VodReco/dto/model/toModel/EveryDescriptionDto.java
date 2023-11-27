package com.example.VodReco.dto.model.toModel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
//@NoArgsConstructor: Builder와 충돌 에러(231104)
public class EveryDescriptionDto {
//    private String content_id; // 예의주시하기
//    @JsonProperty("content_id")
    private String content_id;
    private String description;

    @Builder
    public EveryDescriptionDto(String content_id, String description) {
        this.content_id = content_id;
        this.description = description;
    }
}
