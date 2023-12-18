package com.example.VodReco.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Document(collection = "personal_result")
public class PersonalResult {
    @Field
    private String subsr;
    @Field(name = "unique_id")
    @JsonProperty("content_id")
    private String contentId;
    @Field(name = "title_info")
    private String personal_words;

    @Builder
    public PersonalResult(String subsr, String contentId, String personal_words) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.personal_words = personal_words;
    }
}
