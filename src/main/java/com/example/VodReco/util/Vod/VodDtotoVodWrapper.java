package com.example.VodReco.util.Vod;


import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.util.ListToStringWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



//근데 Vod테이블 자체가 업데이트되지 않는데, dto에서 vod로 바꿀 일이 있을까? (231123)
@Component
@RequiredArgsConstructor
public class VodDtotoVodWrapper {
    private final ListToStringWrapper listToStringWrapper;

    public Vod toVod(VodDto vodDto){
        return VodDtotoVod(vodDto);
    }

    //접근 제어자 private로 좁히기 위해 다른 method로 감싸기(231126)
    private Vod VodDtotoVod(VodDto vodDto) {
        return Vod.builder()
                .title(vodDto.getTitle())
                .contentId(vodDto.getContentId())
                .category(vodDto.getCategory())
                .genre(vodDto.getGenre())
                .disp_rtm(vodDto.getDisp_rtm())

                .description(vodDto.getDescription())
                .actors(vodDto.getActors())
                .posterurl(vodDto.getPosterurl())
                .grade(vodDto.getGrade())
                .country(vodDto.getCountry())

                .release_year(vodDto.getRelease_year())
                .director(vodDto.getDirector())
                .mood(listToStringWrapper.listToString(vodDto.getMood()))
                .gpt_genres(listToStringWrapper.listToString(vodDto.getGpt_genres()))
                .gpt_subjects(listToStringWrapper.listToString(vodDto.getGpt_subjects()))

                .titleDescription(vodDto.getTitleDescription())
                .isSeries(vodDto.getIsSeries())

                .build();
    }
}
