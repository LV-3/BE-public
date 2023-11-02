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
    private String vcode;
    private String country;

    private List<String> genre;

    private String director;
    private String posterurl;
    private String description;

    public VodDto(){
    }

    @Builder
    public VodDto(String title, String vcode, String country, List<String> genre, String director, String posterurl, String description) {
        this.title = title;
        this.vcode = vcode;
        this.country = country;
        this.genre = genre;
        this.director = director;
        this.posterurl = posterurl;
        this.description = description;
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
                .country(vodDto.getCountry())
                .genre(joinListGenre(vodDto.getGenre()))
                .director(vodDto.getDirector())
                .posterurl(vodDto.getPosterurl())
                .description(vodDto.getDescription())
                .build();
    }
}


