package com.example.VodReco.dto.vodDetail;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class VodDetailResponseDto {
    private String title;
    private String contentId;
    private String genre;
    private String country;
    private String director;
    private List<String> actor;
    private String posterurl;
    private String description;
//    private String releaseYear;

    @Builder
    public VodDetailResponseDto(String title, String contentId, String genre, String country, String director, List<String> actor, String posterurl, String description) {
        this.title = title;
        this.contentId = contentId;
        this.genre = genre;
        this.country = country;
        this.director = director;
        this.actor = actor;
        this.posterurl = posterurl;
        this.description = description;
    }

}
