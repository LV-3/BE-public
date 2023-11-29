package com.example.VodReco.domain;

import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


//장르별 VOD 목록 조회 기능의 ResponseData용 테이블. rename 필요(231123)
@Document(collection = "for_genre_view")
@Getter
public class ForGenreView {
    @Field(name = "content_id")
    private String contentId;
    private String genre;
    private String posterurl;
    private String title;

//    @Builder // 쓸 일 없을 것으로 예상(231123)
//    public ForGenreView(String contentId, String genre, String posterurl, String title) {
//        this.contentId = contentId;
//        this.genre = genre;
//        this.posterurl = posterurl;
//        this.title = title;
//    }

    //추후 util패키지의 별도 클래스로 작성 뒤 스프링 빈에 등록해서 사용(231124)
    //genre 제외하고 Dto로 만들기
    public BasicInfoOfVodDto forGenreViewToDto(ForGenreView forGenreView) {
        return BasicInfoOfVodDto.builder().contentId(forGenreView.getContentId()).posterurl(forGenreView.getPosterurl()).title(forGenreView.getTitle()).build();
    }



}
