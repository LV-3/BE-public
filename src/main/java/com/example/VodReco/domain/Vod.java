package com.example.VodReco.domain;

import com.example.VodReco.dto.VodDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;
import java.util.List;
@Document(collection = "vods") // (collection = "vods") 꼭 써야 DB에 넣어둔 컬렉션과 매핑돼서 데이터 가져올 수 있음! (231109)
//@Table(name = "vods") //DB에 존재하는 테이블과 매핑해서 데이터 가져오는 어노테이션
//DB의 컬럼명과 정확히 같게 설정해야 함
@Getter
//@NoArgsConstructor 기본 생성자 직접 작성
public class Vod {
    @Id
//    @Column(nullable = false)
    private String title;

    //vcode -> contentId 리팩토링 필요(231101)
    //스프링 내에서는 contentId로 쓰다가 모델로 넘기는 ResponseDto 클래스 만들 때 필드 content_id로 선언하고
    //builder패턴으로 .content_id(contentId) 집어넣고
    //@ResponseBody 써서 json으로 넘겨주면 될 것으로 예상(231101)

    //스프링 단에서는 contentId, db에서는 content_id로 다루기 위해 name = 추가함(231103)
//    @Column(name = "content_id", nullable = false, unique = true)
    @Field(name = "content_id") // 이거 붙여줘야 mongoDB의 content_id와 스프링부트 내의 contentId가 연결됨 (231109)
    private String contentId;
    //    @Column
//    @Convert(converter = StringAttributeConverter.class)
    private String genre; // 한 개의 String으로 받아서 "," 기준으로 split해서 Array로 만들고 -> List로 convert
    private String country;
    private String director;
    private String actor; //한 개의 String으로 받아서 "," 기준으로 split해서 Array로 만들고 -> List로 split
    private String posterurl;
    private String description;

    public Vod(){
    }

    //DB로부터 꺼내온 actor(String)을 List<String>으로 변환하는 메서드 생성(231102)
    public List<String> actorToList(String actor) {
        String[] splitedActor = actor.split(",");
        return Arrays.stream(splitedActor).toList();
    }

    //DB로부터 꺼내온 genre(String)를 List<String>으로 변환하는 메서드 생성(231102)
    public List<String> genreToList(String genre) {
        String[] splitedGenre = genre.split(",");
        return Arrays.stream(splitedGenre).toList();
    }

    @Builder
    public Vod(String title, String contentId, String actor, String country, String genre, String director, String posterurl, String description) {
        this.title = title;
        this.contentId = contentId;
        this.genre = genre;
        this.country = country;
        this.director = director;
        this.actor = actor;
        this.posterurl = posterurl;
        this.description = description;

    }

    public VodDto toVodDto(Vod vod) {
        if (vod == null) {
            return null;
        }
        return VodDto.builder()
                .title(vod.getTitle())
                .contentId(vod.getContentId())
                .genre(vod.genreToList(vod.getGenre()))
                .country(vod.getCountry())
                .director(vod.getDirector())
                .actor(vod.actorToList(getActor()))
                .posterurl(vod.getPosterurl())
                .description(vod.getDescription())
                .build();
    }
}
