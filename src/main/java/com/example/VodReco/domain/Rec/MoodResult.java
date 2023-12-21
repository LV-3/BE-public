package com.example.VodReco.domain.Rec;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Document(collection = "mood_result")
public class MoodResult {
    @Field
    private String subsr;
    @Field(name = "recommend_list")
    @JsonProperty("content_id")
    private String contentId;
    @Field(name = "title_info")
    private String genre_words;

    @Builder
    public MoodResult(String subsr, String contentId, String genre_words) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.genre_words = genre_words;
    }
}
