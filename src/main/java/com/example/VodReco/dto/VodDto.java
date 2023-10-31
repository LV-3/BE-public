package com.example.VodReco.dto;

import com.example.VodReco.domain.Vod;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;


@Getter
public class VodDto {
    @Id
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private String vcode;
    private String country;
    private String genre;
    private String director;
    private String posterurl;

    public VodDto(){
    }

    @Builder
    public VodDto(String title, String vcode, String country, String genre, String director, String posterurl) {
        this.title = title;
        this.vcode = vcode;
        this.country = country;
        this.genre = genre;
        this.director = director;
        this.posterurl = posterurl;
    }

    public Vod toVodEntity(VodDto vodDto) {
        return Vod.builder()
                .title(vodDto.getTitle())
                .vcode(vodDto.getVcode())
                .country(vodDto.getCountry())
                .genre(vodDto.getGenre())
                .director(vodDto.getDirector())
                .posterurl(vodDto.getPosterurl())
                .build();
    }
}


