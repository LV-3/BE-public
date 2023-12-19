package com.example.VodReco.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


//필드 13개(231218)



@Document(collection = "vods")
//@CompoundIndex(def = "{'title': 'text', 'actors': 'text'}")
@Getter
@NoArgsConstructor
public class Vod {

    @Field(name = "unique_id")
    private String contentId;

    @Field
    private String title;

    @Field(name = "ct_cl")
    private String category;

    @Field(name = "genre_of_ct_cl")
    private String genre;

    @Field
    private String disp_rtm;

    @Field(name = "SMRY")
    private String description;

    @Field(name = "ACTR_DISP")
    private String actors; //한 개의 String으로 받아서 ", " 기준으로 split해서 Array로 만들고 -> List로 convert

    @Field(name = "ImgUrl")
    private String posterurl;

    @Field
    private String grade;

    @Field
    private String country;

    @Field
    private String release_year;

    @Field
    private String director;// 한 개의 String으로 받아서 ", " 기준으로 split해서 Array로 만들고 -> List로 convert

    @Field(name = "translated_front_view_template")
    private String uniqueTemplates;





    @Builder
    public Vod(String contentId, String title, String category, String genre, String disp_rtm,
               String description, String actors, String posterurl, String grade, String country,
               String release_year, String director, String uniqueTemplates) {

        this.contentId = contentId;
        this.title = title;
        this.category = category;
        this.genre = genre;
        this.disp_rtm = disp_rtm;

        this.description = description;
        this.actors = actors;
        this.posterurl = posterurl;
        this.grade = grade;
        this.country = country;

        this.release_year = release_year;
        this.director = director;
        this.uniqueTemplates = uniqueTemplates;

    }

}