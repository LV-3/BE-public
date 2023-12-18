package com.example.VodReco.dto.vodDetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

//총 12개 필드(mood, gpt_genres, gpt_subjects 3개 제외)
@Getter
@RequiredArgsConstructor
public class VodDetailResponseDto {
    private String title;
    @JsonProperty("content_id")
    private String contentId;
    private String genre;
    private String country;
    private String director;
    private String actors;
    private String posterurl;
    private String description;

    private String disp_rtm;
    private String release_year;
    private String grade;
    private String category;

    private List<String> tags;
    @Builder
    public VodDetailResponseDto(String title, String contentId, String genre, String country, String director,
                                String actors, String posterurl, String description,
                                String disp_rtm, String release_year, String grade, String category, List<String> tags) {
        this.title = title;
        this.contentId = contentId;
        this.genre = genre;
        this.country = country;
        this.director = director;

        this.actors = actors;
        this.posterurl = posterurl;
        this.description = description;
        this.disp_rtm = disp_rtm;
        this.release_year = release_year;

        this.grade = grade;
        this.category = category;

        this.tags = tags;
    }

}
