package com.example.VodReco.domain;

import com.example.VodReco.dto.VodDto;
import com.example.VodReco.util.StringToListConverter;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    @Field(name = "ct_cl")
    private String category;
    @Field(name = "genre_of_ct_cl")
    private String genre;
    @Field
    private String disp_rtm;


    @Field(name = "SMRY")
    private String description;
    @Field(name = "ACTR_DISP")
    private String actors; //한 개의 String으로 받아서 "," 기준으로 split해서 Array로 만들고 -> List로 convert
    @Field(name = "ImgUrl")
    private String posterurl;
    @Field
    private String grade;
    @Field
    private String country;


    @Field
    private String release_year;
    @Field
    private String director;// 한 개의 String으로 받아서 "," 기준으로 split해서 Array로 만들고 -> List로 convert
    @Field
    private String mood;// 한 개의 String으로 받아서 "," 기준으로 split해서 Array로 만들고 -> List로 convert
    @Field
    private String gpt_genres;
    @Field
    private String gpt_subjects;


    public Vod() {

    }

    @Builder
    public Vod(String contentId, String title, String category, String genre, String disp_rtm,
               String description, String actors, String posterurl, String grade, String country,
               String release_year, String director, String mood, String gpt_genres, String gpt_subjects) {

        this.contentId = contentId;
        this.title = title;
        this.category = category;
        this.genre = genre;
        this.disp_rtm = disp_rtm;

        this.description = description;
        this.actors = actors;
        this.posterurl = posterurl;
        this.grade= grade;
        this.country = country;

        this.release_year = release_year;
        this.director = director;
        this.mood = mood;
        this.gpt_genres = gpt_genres;
        this.gpt_subjects = gpt_subjects;
    }

    public VodDto toVodDto(Vod vod) {
        if (vod == null) {
            return null;
        }
        return VodDto.builder()
                .contentId(vod.getContentId())
                .title(vod.getTitle())
                .category(vod.getCategory())
                .genre(vod.getGenre())
                .disp_rtm(vod.getDisp_rtm())

                .description(vod.getDescription())
                .actors(StringToListConverter.stringToList(vod.getActors()))
                .posterurl(vod.getPosterurl())
                .grade(vod.getGrade())
                .country(vod.getCountry())

                .release_year(vod.getRelease_year())
                .director(StringToListConverter.stringToList(vod.getDirector()))
                .mood(StringToListConverter.stringToList(vod.getMood()))
                .gpt_genres(StringToListConverter.stringToList(vod.getGpt_genres()))
                .gpt_subjects(StringToListConverter.stringToList(vod.getGpt_subjects()))

                .build();
    }
}