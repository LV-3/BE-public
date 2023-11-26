package com.example.VodReco.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "personal_watch")
@Getter
public class PersonalWatch {
    private String subsr;
    @Field(name = "content_id")
    private String contentId;
    @Field(name = "ct_cl")
    private String category;
    @Field(name = "genre_of_ct_cl")
    private String genre;
    @Field(name = "template_A")
    private String mood;
    @Field(name = "template_B")
    private String gpt_genres;
    @Field(name = "template_C")
    private String gpt_subjects;
    @Field(name = "liked")
    private Integer liked;

    @Builder
    public PersonalWatch(String subsr, String contentId, String category, String genre, String mood, String gpt_genres, String gpt_subjects, Integer liked) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.category = category;
        this.genre = genre;
        this.mood = mood;
        this.gpt_genres = gpt_genres;
        this.gpt_subjects = gpt_subjects;
        this.liked = liked;
    }
}
