package com.example.VodReco.dto.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
@Getter
@Component
@Setter
@RequiredArgsConstructor
public class ToMoodModelDto {
    private String modelName;
    private List<EveryMood> responseData;

    @Builder
    public ToMoodModelDto(String modelName, List<EveryMood> responseData) {
        this.modelName = modelName;
        this.responseData = responseData;
    }
}
