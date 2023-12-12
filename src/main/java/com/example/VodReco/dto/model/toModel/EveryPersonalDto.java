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
    private Integer content_id;
    private String ct_cl;
    private String genre_of_ct_cl;
    private List<String> template_A_TopGroup;
    private List<String> template_B_TopGroup;
    private List<String> template_C_TopGroup;
//    @JsonProperty("user_preference")
//    private Integer userPreference;
    private Integer user_preference;
    @JsonProperty("TimeGroup")
    private String TimeGroup;

    @Builder
    public EveryPersonalDto(Integer subsr, Integer content_id, String ct_cl, String genre_of_ct_cl, List<String> template_A_TopGroup, List<String> template_B_TopGroup, List<String> template_C_TopGroup,
                            Integer user_preference, String TimeGroup) {
        this.subsr = subsr;
        this.content_id = content_id;
        this.genre_of_ct_cl = genre_of_ct_cl;
        this.ct_cl = ct_cl;
        this.template_A_TopGroup = template_A_TopGroup;
        this.template_B_TopGroup = template_B_TopGroup;
        this.template_C_TopGroup = template_C_TopGroup;
        this.user_preference = user_preference;
        this.TimeGroup = TimeGroup;

    }

}
