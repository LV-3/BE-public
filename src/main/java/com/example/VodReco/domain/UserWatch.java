package com.example.VodReco.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//삭제 필요(231123)

@Document(collection = "user_watch")
@Getter
@RequiredArgsConstructor

//전면 수정 필요
//태이블에는 subsr, content_id밖에 없고 mood와 description은 vod테이블에서 조회해서 사용해야 함(231116)
public class UserWatch {
    private String subsr;
    @Field(name = "content_id")
    private String contentId;
    private Integer user_preference;

    private String title;
    private String posterurl;

    @Builder
    public UserWatch(String subsr, String contentId, Integer user_preference, String title, String posterurl) {
        this.contentId = contentId;
        this.subsr = subsr;
        this.user_preference = user_preference;
        this.title = title;
        this.posterurl = posterurl;
    }
}
