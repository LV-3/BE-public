package com.example.VodReco.service.mainPage.viewPopularVods;

import com.example.VodReco.domain.PopularVod;
import com.example.VodReco.mongoRepository.PopularVodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewPopularVodsServiceImpl implements ViewPopularVodsService{
    private final PopularVodRepository popularVodRepository;

    public List<PopularVod> getTop10PopularVods() {
        // 시간대에 따른 VOD 가져오기
        LocalDateTime now = LocalDateTime.now();
        String timeGroup = getTimeGroup(now);

        // 시간대에 맞는 상위 10개 VOD 조회
        return popularVodRepository.findTop10ByTimeGroupOrderByCountDesc(timeGroup);
    }

    private String getTimeGroup(LocalDateTime now) {
        int hour = now.getHour();
        if (hour >= 18 || hour < 6) {
            return "night";
        } else if (hour >= 6 && hour < 12) {
            return "am";
        } else if (hour >= 12 && hour < 18) {
            return "pm";
        } else {
            return "dawn";
        }
    }
//    public List<ViewPopularVodsDto> getPopularVodsByTimeGroup(String timeGroup) {
//        int hour = LocalDateTime.now().getHour();
//
//        // dawn 시간대에만 count 값이 3 이상인 것 출력
//        if (hour >= 0 && hour < 6) {
//            return popularVodRepository.findByTimeGroupAndCountGreaterThanEqual("dawn", 3)
//                    .stream()
//                    .map(this::convertToDto)
//                    .collect(Collectors.toList());
//        }
//
//        if (hour >= 18) {
//            timeGroup = "night";
//        } else if (hour >= 6 && hour < 12) {
//            timeGroup = "am";
//        } else if (hour >= 12 && hour < 18) {
//            timeGroup = "pm";
//        } else {
//            timeGroup = "dawn";
//        }
//
//        return popularVodRepository.findByTimeGroupAndCountGreaterThanEqual(timeGroup, 5)
//                .stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    private ViewPopularVodsDto convertToDto(PopularVod vod) {
//        return ViewPopularVodsDto.builder()
//                .contentId(vod.getContentId())
//                .timeGroup(vod.getTimeGroup())
//                .count(vod.getCount())
//                .title(vod.getTitle())
//                .posterurl(vod.getPosterurl())
//                .build();
//    }


}
