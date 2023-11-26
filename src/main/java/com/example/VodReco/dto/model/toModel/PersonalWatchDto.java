package com.example.VodReco.dto.model.toModel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PersonalWatchDto {
    private String subsr;
    private String contentId;
    private String category;
    private String genre;
    private List<String> mood;
    private List<String> gpt_genres;
    private List<String> gpt_subjects;
    private Integer liked;

    @Builder
    public PersonalWatchDto(String subsr, String contentId, String category, String genre, List<String> mood, List<String> gpt_genres, List<String> gpt_subjects, Integer liked) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.category = category;
        this.genre = genre;
        this.mood = mood;
        this.gpt_genres = gpt_genres;
        this.gpt_subjects = gpt_subjects;
        this.liked = liked;

    }
}
