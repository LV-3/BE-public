package com.example.VodReco.dto.model.toModel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

//ForDeepFM 엔티티 조회해서 EveryPersonalDto 인스턴스 내부 필드에 값 주입할 때 사용하는 DTO
@Getter
@RequiredArgsConstructor
public class ForDeepFMDto {
    private String subsr;
    private String contentId;
    private String category;
    private String genre;
    private List<String> mood;
    private List<String> gpt_genres;
    private List<String> gpt_subjects;
    private Integer userPreference;
    private String timeGroup;

    @Builder
    public ForDeepFMDto(String subsr, String contentId, String category, String genre, List<String> mood, List<String> gpt_genres, List<String> gpt_subjects, Integer userPreference,
                        String timeGroup) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.category = category;
        this.genre = genre;
        this.mood = mood;
        this.gpt_genres = gpt_genres;
        this.gpt_subjects = gpt_subjects;
        this.userPreference = userPreference;
        this.timeGroup = timeGroup;

    }
}
