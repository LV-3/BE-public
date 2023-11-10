package com.example.VodReco.dto.model;

import jakarta.persistence.criteria.From;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class FromModelDto {
    private List<String> description_data;
    private List<String> genre_data;
    private List<String> personal_data;

    @Builder
    public FromModelDto(List<String> description_data, List<String> genre_data, List<String> personal_data) {
        this.description_data = description_data;
        this.genre_data = genre_data;
        this.personal_data = personal_data;
    }
}
