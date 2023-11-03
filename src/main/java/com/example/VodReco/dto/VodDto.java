package com.example.VodReco.dto;

import com.example.VodReco.domain.Vod;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@Getter
@ToString //이거 없이도 VodDto 리턴값 멀쩡히 json으로 출력되는데??뭐임(231102)
public class VodDto {
    @Id
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private String vcode;

    private List<String> genre;
    private String country;
    private String director;
    private List<String> actor;
    private String posterurl;
    private String description;

    public VodDto(){
    }

    @Builder
    public VodDto(String title, String vcode, List<String> actor, String country, List<String> genre, String director, String posterurl, String description) {
        this.title = title;
        this.vcode = vcode;
        this.genre = genre;
        this.country = country;
        this.director = director;
        this.actor = actor;
        this.posterurl = posterurl;
        this.description = description;
    }

    //프로젝트 내에서 사용하던 VodDto를 Vod엔티티(=DB와 직접 연결되는 것)으로 변환할 때
    //actor를 List<String>에서 다시 String 하나로 join하는 메서드 생성(231102)
    public String joinListActor(List<String> actor) {
        return String.join(",", actor);
    }

    //프로젝트 내에서 사용하던 VodDto를 Vod엔티티(=DB와 직접 연결되는 것)으로 변환할 때
    //genre를 List<String>에서 다시 String 하나로 join하는 메서드 생성(231102)
    public String joinListGenre(List<String> genre) {
        return String.join(",", genre);
    }


    public Vod toVodEntity(VodDto vodDto) {
        if (vodDto == null) {
            return null;
        }
        return Vod.builder()
                .title(vodDto.getTitle())
                .vcode(vodDto.getVcode())
                .genre(joinListGenre(vodDto.getGenre()))
                .country(vodDto.getCountry())
                .director(vodDto.getDirector())
                .actor(joinListActor(vodDto.getActor()))
                .posterurl(vodDto.getPosterurl())
                .description(vodDto.getDescription())
                .build();
    }
}


