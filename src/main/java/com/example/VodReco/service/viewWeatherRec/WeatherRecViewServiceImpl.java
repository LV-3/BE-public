package com.example.VodReco.service.viewWeatherRec;

import com.example.VodReco.domain.Rec.WeatherRec;
import com.example.VodReco.dto.WeatherRecDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.mongoRepository.WeatherRecRepository;
import com.example.VodReco.util.ContentIdToBasicInfoOfVodsWrapper;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherRecViewServiceImpl implements WeatherRecViewService{
    private final WeatherRecRepository weatherRecRepository;
    private final ContentIdToBasicInfoOfVodsWrapper contentIdToBasicInfoOfVodsWrapper;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;
    private final VodRepository vodRepository;

    @Override
    public WeatherRecDto sendWeatherRecommendations() {
        WeatherRec weatherRec = weatherRecRepository.findAll().get(0);
        List<String> contentIdList = weatherRec.getContentIdList();
        List list = new ArrayList();
        List basicInfoOfVodDtos = contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(list, contentIdList, vodtoVodDtoWrapper, vodRepository);
        return WeatherRecDto.builder()
                .weather(weatherRec.getWeather()).vodsList(basicInfoOfVodDtos).weatherImg(weatherRec.getWeatherImg())
                .build();
    }

}
