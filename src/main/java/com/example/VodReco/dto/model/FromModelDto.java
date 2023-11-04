package com.example.VodReco.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FromModelDto {
    private List<String> description_data;
    private List<String> genre_data;
    private List<String> personal_data;

}
