package com.example.VodReco.dto.model.toModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class EveryPersonalDto {
    private String subsr;
    @JsonProperty("content_id")
    private String contentId;
    @JsonProperty("ct_cl")
    private String category;
    @JsonProperty("genre_of_ct_cl")
    private String genre;
    @JsonProperty("template_A")
    private List<String> mood;
    @JsonProperty("template_B")
    private List<String> gpt_genres;
    @JsonProperty("template_C")
    private List<String> gpt_subjects;
    private Integer liked;

    @Builder
    public EveryPersonalDto(String subsr, String contentId, String category, String genre,
                            List<String> mood, List<String> gpt_genres,
                            List<String> gpt_subjects, Integer liked) {
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
