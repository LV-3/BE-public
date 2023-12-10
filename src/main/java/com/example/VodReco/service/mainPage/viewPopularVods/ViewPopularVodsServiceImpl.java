package com.example.VodReco.service.mainPage.viewPopularVods;

import com.example.VodReco.domain.PopularVod;
import com.example.VodReco.dto.popular.ViewPopularVodsDto;
import com.example.VodReco.mongoRepository.PopularVodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewPopularVodsServiceImpl {
    private final PopularVodRepository popularVodRepository;

    @Autowired
    public ViewPopularVodsServiceImpl(PopularVodRepository popularVodRepository) {
        this.popularVodRepository = popularVodRepository;
    }
    public List<ViewPopularVodsDto> getPopularVodsByTimeGroup(String timeGroup) {
        int hour = LocalDateTime.now().getHour();

        // dawn 시간대에만 count 값이 3 이상인 것 출력
        if (hour >= 0 && hour < 6) {
            return popularVodRepository.findByTimeGroupAndCountGreaterThanEqual("dawn", 3)
                    .stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }

        if (hour >= 18) {
            timeGroup = "night";
        } else if (hour >= 6 && hour < 12) {
            timeGroup = "am";
        } else if (hour >= 12 && hour < 18) {
            timeGroup = "pm";
        } else {
            timeGroup = "dawn";
        }

        return popularVodRepository.findByTimeGroupAndCountGreaterThanEqual(timeGroup, 5)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ViewPopularVodsDto convertToDto(PopularVod vod) {
        return ViewPopularVodsDto.builder()
                .contentId(vod.getContentId())
                .timeGroup(vod.getTimeGroup())
                .count(vod.getCount())
                .title(vod.getTitle())
                .posterurl(vod.getPosterurl())
                .build();
    }
}
