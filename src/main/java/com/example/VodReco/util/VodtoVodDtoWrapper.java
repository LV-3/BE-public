package com.example.VodReco.util;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public//public -> dafault로 변경(Entity를 직접 인풋으로 받는 거라 범위 줄임) -> default면 같은 패키지 내부 아닌가? 불가(231123)
class VodtoVodDtoWrapper {
    private final StringToListWrapper stringToListWrapper;

    //static으로 만들어서 VodtoVodDto의 인스턴스 vodtoVodDto 만들지 않고
    //그냥 VodtoVodDto.vodtoVodDto 쓸 수 있게 변경
    //오로지 이 메서드만 쓸 수 있으면 되는 클래스라 인스턴스 필요없음
    public VodDto toVodDto(Vod vod) {
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

                .build();
    }
}
