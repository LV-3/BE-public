package com.example.VodReco.util.Vod;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.util.StringToListWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public//public -> dafault로 변경(Entity를 직접 인풋으로 받는 거라 범위 줄임) -> default면 같은 패키지 내부 아닌가? 불가(231123)
class VodtoVodDtoWrapper {
    private final StringToListWrapper stringToListWrapper;

    public VodDto toVodDto(Vod vod){
        return VodtoVodDto(vod);
    }
    private VodDto VodtoVodDto(Vod vod) {
        return VodDto.builder()
                .contentId(vod.getContentId())
                .title(vod.getTitle())
                .category(vod.getCategory())
                .genre(vod.getGenre())
                .disp_rtm(vod.getDisp_rtm())

                .description(vod.getDescription())
                .actors(vod.getActors())
                .posterurl(vod.getPosterurl())
                .grade(vod.getGrade())
                .country(vod.getCountry())

                .release_year(vod.getRelease_year())
                .director(vod.getDirector())
                .mood(stringToListWrapper.stringToList(vod.getMood()))
                .gpt_genres(stringToListWrapper.stringToList(vod.getGpt_genres()))
                .gpt_subjects(stringToListWrapper.stringToList(vod.getGpt_subjects()))

                .titleDescription(vod.getTitleDescription())
                .isSeries(vod.getIsSeries())
                .seriesId(vod.getSeriesId())

                .build();
    }
}
