package com.example.VodReco.dto.Recommendation.model.fromModel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FromModelDto {
    private List<String> description_data;
    private List<String> mood_data;
    private List<String> personal_data;

}
