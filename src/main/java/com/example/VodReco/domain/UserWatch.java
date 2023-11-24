package com.example.VodReco.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

//삭제 필요(231123)

@Document
@Getter
@RequiredArgsConstructor

//전면 수정 필요
//태이블에는 subsr, content_id밖에 없고 mood와 description은 vod테이블에서 조회해서 사용해야 함(231116)
public class UserWatch {
    private String subsr;
    private String content_id;
    private Integer user_runningtime;
    private Integer runningtime;

    @Builder
    public UserWatch(String subsr, String content_id, Integer user_runningtime, Integer runningtime) {
        this.content_id = content_id;
        this.subsr = subsr;
        this.user_runningtime = user_runningtime;
        this.runningtime = runningtime;
    }
}
