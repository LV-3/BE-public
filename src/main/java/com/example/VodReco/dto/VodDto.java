package com.example.VodReco.dto;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.util.ListToStringWrapper;
import com.example.VodReco.util.StringToListWrapper;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;


//VodDto 사용 목적: findBy로 조회할 때 Vod자체를 다루지 않기 위함

@Getter
@ToString
public class VodDto {
    @Id
    private String contentId;
    private String title;
    private String category;
    private String genre;
    private String disp_rtm;

    private String description;
    //변경 가능성 있음(231123)
    private List<String> actors; //한 개의 String으로 받아서 "," 기준으로 split해서 Array로 만들고 -> List로 convert
    private String posterurl;
    private String grade;
    private String country;

    private String release_year;
    //변경 가능성 있음(231123)
    private List<String> director;// 한 개의 String으로 받아서 "," 기준으로 split해서 Array로 만들고 -> List로 convert
    private List<String> mood;// 한 개의 String으로 받아서 "," 기준으로 split해서 Array로 만들고 -> List로 convert
    private List<String> gpt_genres;
    private List<String> gpt_subjects;

    public VodDto(){
    }

    @Builder
    public VodDto(String contentId, String title, String category, String genre, String disp_rtm,
                  String description, List<String> actors, String posterurl, String grade, String country,
                  String release_year, List<String> director, List<String> mood, List<String> gpt_genres, List<String> gpt_subjects) {

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
    }


//    public Vod toVodEntity(VodDto vodDto) {
//        if (vodDto == null) {
//            return null;
//        }
//        return Vod.builder()
//                .title(vodDto.getTitle())
//                .contentId(vodDto.getContentId())
//                .category(vodDto.getCategory())
//                .genre(vodDto.getGenre())
//                .disp_rtm(vodDto.getDisp_rtm())
//
//                .description(vodDto.getDescription())
//                .actors(listToStringWrapper.listToString(vodDto.getActors()))
//                .posterurl(vodDto.getPosterurl())
//                .grade(vodDto.getGrade())
//                .country(vodDto.getCountry())
//
//                .release_year(vodDto.getRelease_year())
//                .director(ListToStringWrapper.listToString(vodDto.getDirector()))
//                .mood(ListToStringWrapper.listToString(vodDto.getMood()))
//                .gpt_genres(ListToStringWrapper.listToString(vodDto.getGpt_genres()))
//                .gpt_subjects(ListToStringWrapper.listToString(vodDto.getGpt_subjects()))
//
//                .build();
    }

}


