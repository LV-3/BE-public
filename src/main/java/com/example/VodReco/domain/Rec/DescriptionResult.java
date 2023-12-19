package com.example.VodReco.domain.Rec;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "description_result")
@Getter
public class DescriptionResult {
    @Field
    private String subsr;
    @Field(name = "recommend_list")
    @JsonProperty("content_id")
    private String contentId;

    @Builder
    public DescriptionResult(String subsr, String contentId) {
        this.subsr = subsr;
        this.contentId = contentId;
    }
}
