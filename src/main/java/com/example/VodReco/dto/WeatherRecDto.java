package com.example.VodReco.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Builder
public class WeatherRecDto {
    private String weather;
    private List<BasicInfoOfVodDto> vodsList;
    private String weatherImg;

    @Builder
    public WeatherRecDto(String weather, List<BasicInfoOfVodDto> vodsList, String weatherImg) {
        this.weather = weather;
        this.vodsList = vodsList;
        this.weatherImg = weatherImg;
    }

}