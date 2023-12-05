package com.example.VodReco.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//필드 총 5개

@Document(collection = "user_watch")
@Getter
@RequiredArgsConstructor

public class UserWatch {
    private String subsr;
    @Field(name = "content_id")
    private String contentId;

    @Field(name = "user_preference")
    private Integer userPreference;

    private String title;
    @Field(name = "ImgUrl")
    private String posterurl;
    @Field(name = "series_id")
    private String seriesId;

    @Builder
    public UserWatch(String subsr, String contentId, Integer userPreference, String title, String posterurl, String seriesId) {

        this.contentId = contentId;
        this.subsr = subsr;
        this.userPreference = userPreference;
        this.title = title;
        this.posterurl = posterurl;
        this.seriesId = seriesId;
    }
}
