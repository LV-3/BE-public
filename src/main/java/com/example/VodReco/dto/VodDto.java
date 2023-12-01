package com.example.VodReco.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;


//VodDto 사용 목적: findBy로 조회할 때 Vod자체를 다루지 않기 위함

@Getter
@ToString
@NoArgsConstructor
public class VodDto {
    private String contentId;
    private String title;
    private String category;
    private String genre;
    private String disp_rtm;

    private String description;
    private String actors; //한 개의 String으로 받아서 ", " 기준으로 split해서 Array로 만들고 -> List로 convert
    private String posterurl;
    private String grade;
    private String country;

    private String release_year;
    private String director;// 한 개의 String으로 받아서 ", " 기준으로 split해서 Array로 만들고 -> List로 convert
    private List<String> mood;// 한 개의 String으로 받아서 ", " 기준으로 split해서 Array로 만들고 -> List로 convert
    private List<String> gpt_genres;
    private List<String> gpt_subjects;

    private String titleDescription;
    private Integer isSeries;

    @Builder
    public VodDto(String contentId, String title, String category, String genre, String disp_rtm,
                  String description, String actors, String posterurl, String grade, String country,
                  String release_year, String director, List<String> mood, List<String> gpt_genres, List<String> gpt_subjects,
                  String titleDescription, Integer isSeries) {

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
        this.mood = mood;
        this.gpt_genres = gpt_genres;
        this.gpt_subjects = gpt_subjects;

        this.titleDescription = titleDescription;
        this.isSeries = isSeries;
    }

}


