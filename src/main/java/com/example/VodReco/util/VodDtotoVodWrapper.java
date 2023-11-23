package com.example.VodReco.util;


import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



//근데 Vod테이블 자체가 업데이트되지 않는데, dto에서 vod로 바꿀 일이 있을까? (231123)
@Component
@RequiredArgsConstructor
public class VodDtotoVodWrapper {
    private final ListToStringWrapper listToStringWrapper;

    public Vod toVod(VodDto vodDto) {
        return Vod.builder()
                .title(vodDto.getTitle())
                .contentId(vodDto.getContentId())
                .category(vodDto.getCategory())
                .genre(vodDto.getGenre())
                .disp_rtm(vodDto.getDisp_rtm())

                .description(vodDto.getDescription())
                .actors(listToStringWrapper.listToString(vodDto.getActors()))
                .posterurl(vodDto.getPosterurl())
                .grade(vodDto.getGrade())
                .country(vodDto.getCountry())

                .release_year(vodDto.getRelease_year())
                .director(listToStringWrapper.listToString(vodDto.getDirector()))
                .mood(listToStringWrapper.listToString(vodDto.getMood()))
                .gpt_genres(listToStringWrapper.listToString(vodDto.getGpt_genres()))
                .gpt_subjects(listToStringWrapper.listToString(vodDto.getGpt_subjects()))

                .build();
    }
}
