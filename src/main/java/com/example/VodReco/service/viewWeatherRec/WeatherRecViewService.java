package com.example.VodReco.service.viewWeatherRec;

import com.example.VodReco.dto.WeatherRecDto;
import org.springframework.stereotype.Service;

@Service
public interface WeatherRecViewService {
    WeatherRecDto sendWeatherRecommendations();
}
