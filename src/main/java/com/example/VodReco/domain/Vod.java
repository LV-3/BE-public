package com.example.VodReco.domain;

import com.example.VodReco.dto.VodDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;
import java.util.List;
@Document(collection = "vods") // (collection = "vods") 꼭 써야 DB에 넣어둔 컬렉션과 매핑돼서 데이터 가져올 수 있음! (231109)
@Getter
public class Vod {
//    주의: jpa의 @Id가 아님
    @Id
    @Field(name = "content_id") // 이거 붙여줘야 mongoDB의 content_id와 스프링부트 내의 contentId가 연결됨 (231109)
    private String contentId;
    @Field(name = "preprocessed")
    private String title;
//    @Convert(converter = StringAttributeConverter.class)
    @Field(name = "genre_of_ct_cl")
    private String genre;
    @Field(name = "ct_cl")
    private String category;
    private String mood;// 한 개의 String으로 받아서 "," 기준으로 split해서 Array로 만들고 -> List로 convert
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
    public List<String> moodToList(String mood) {
        String[] splitedMood = mood.split(",");
        return Arrays.stream(splitedMood).toList();
    }

    @Builder
    public Vod(String title, String contentId, String actor, String country, String genre, String mood, String director, String posterurl, String description) {
        this.title = title;
        this.contentId = contentId;
        this.genre = genre;
        this.mood = mood;
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
                .genre(vod.getGenre())
                .mood(vod.moodToList(vod.getMood()))
                .country(vod.getCountry())
                .director(vod.getDirector())
                .actor(vod.actorToList(getActor()))
                .posterurl(vod.getPosterurl())
                .description(vod.getDescription())
                .build();
    }
}