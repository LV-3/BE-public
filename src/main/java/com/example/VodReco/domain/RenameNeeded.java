package com.example.VodReco.domain;

import com.example.VodReco.dto.genre.ViewVodsByGenreResponseDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


//장르별 VOD 목록 조회 기능의 ResponseData용 테이블. rename 필요(231123)
@Document
@Getter
public class RenameNeeded {
    @Field(name = "content_id")
    private String contentId;
    private String posterurl;
    private String title;

    @Builder // 쓸 일 없을 것으로 예상(231123)
    public RenameNeeded(String content_id, String posterurl, String title) {
        this.contentId = content_id;
        this.posterurl = posterurl;
        this.title = title;
    }

    public ViewVodsByGenreResponseDto domainToDto(RenameNeeded renameNeeded) {
        return ViewVodsByGenreResponseDto.builder().content_id(renameNeeded.getContentId()).posterurl(renameNeeded.getPosterurl()).title(renameNeeded.getTitle()).build();
    }



}
