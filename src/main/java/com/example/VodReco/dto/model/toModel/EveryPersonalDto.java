package com.example.VodReco.dto.model.toModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

//필드 총 8개(231127)
@Getter
@RequiredArgsConstructor
@ToString
public class EveryPersonalDto {
    private Integer subsr;
//    @JsonProperty("content_id")
//    private String contentId;
    private Integer content_id;
//    @JsonProperty("ct_cl")
//    private String category;
    private String ct_cl;
//    @JsonProperty("genre_of_ct_cl")
//    private String genre;
    private String genre_of_ct_cl;
//    @JsonProperty("template_A")
//    private List<String> mood;

    private List<String> template_A_TopGroup;
//    private String template_A;
    //    @JsonProperty("template_B")
    private List<String> template_B_TopGroup;
//    private String template_B;
//    @JsonProperty("template_C")
    private List<String> template_C_TopGroup;
//    private String template_C;
    private Integer liked;

    @Builder
    public EveryPersonalDto(Integer subsr, Integer content_id, String ct_cl, String genre_of_ct_cl, List<String> template_A_TopGroup, List<String> template_B_TopGroup, List<String> template_C_TopGroup,
//                            String contentId, String category, String genre,
//                            List<String> mood, List<String> gpt_genres,
//                            List<String> gpt_subjects,
                            Integer liked) {
        this.subsr = subsr;
//        this.contentId = contentId;
        this.content_id = content_id;
        this.genre_of_ct_cl = genre_of_ct_cl;
        this.ct_cl = ct_cl;
        this.template_A_TopGroup = template_A_TopGroup;
        this.template_B_TopGroup = template_B_TopGroup;
        this.template_C_TopGroup = template_C_TopGroup;
//        this.category = category;
//        this.genre = genre;
//        this.mood = mood;
//        this.gpt_genres = gpt_genres;
//        this.gpt_subjects = gpt_subjects;
        this.liked = liked;

    }

}
