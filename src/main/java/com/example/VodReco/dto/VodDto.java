package com.example.VodReco.dto;

import com.example.VodReco.domain.Vod;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@Getter
@ToString
public class VodDto {
    @Id
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private String contentId;
    private String genre;
    private List<String> mood;
    private String country;
    private String director;
    private List<String> actor;
    private String posterurl;
    private String description;

    public VodDto(){
    }

    @Builder
    public VodDto(String title, String contentId, List<String> actor, String country, String genre, List<String> mood, String director, String posterurl, String description) {
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

    //프로젝트 내에서 사용하던 VodDto를 Vod엔티티(=DB와 직접 연결되는 것)으로 변환할 때
    //actor를 List<String>에서 다시 String 하나로 join하는 메서드 생성(231102)

    //actor는 구분자에 띄어쓰기 끼기
    public String joinListActor(List<String> actor) {
        return String.join(", ", actor);
    }

    //프로젝트 내에서 사용하던 VodDto를 Vod엔티티(=DB와 직접 연결되는 것)으로 변환할 때
    //genre를 List<String>에서 다시 String 하나로 join하는 메서드 생성(231102)
    public String joinListMood(List<String> mood) {
        return String.join(",", mood);
    }


    public Vod toVodEntity(VodDto vodDto) {
        if (vodDto == null) {
            return null;
        }
        return Vod.builder()
                .title(vodDto.getTitle())
                .contentId(vodDto.getContentId())
                .genre(vodDto.getGenre())
                .mood(joinListMood(vodDto.getMood()))
                .country(vodDto.getCountry())
                .director(vodDto.getDirector())
                .actor(joinListActor(vodDto.getActor()))
                .posterurl(vodDto.getPosterurl())
                .description(vodDto.getDescription())
                .build();
    }
}


