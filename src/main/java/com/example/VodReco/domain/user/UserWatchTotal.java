package com.example.VodReco.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "user_watch_total")
@Getter
@RequiredArgsConstructor
public class UserWatchTotal {
    private String subsr;
    @Field(name = "content_id")
    private String contentId;
    private Integer user_preference;

    private String title;
    @Field(name = "ImgUrl")
    private String posterurl;

    @Builder
    public UserWatchTotal(String subsr, String contentId, Integer user_preference, String title, String posterurl) {
        this.contentId = contentId;
        this.subsr = subsr;
        this.user_preference = user_preference;
        this.title = title;
        this.posterurl = posterurl;
    }
}