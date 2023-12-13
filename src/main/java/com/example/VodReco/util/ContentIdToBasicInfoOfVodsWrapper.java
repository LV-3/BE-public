package com.example.VodReco.util;

import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.BasicInfoOfVodDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContentIdToBasicInfoOfVodsWrapper {

//    contentId로 Vod의 기본정보(title, content_id, posterurl) 리스트를 만드는 메서드.
//    genre, mood, gpt_genres, gpt_subjects, 메인페이지에서 사용됨.

    public List<BasicInfoOfVodDto> getBasicInfoOfVodDtos(List<BasicInfoOfVodDto> list, List<String> contentIds, VodtoVodDtoWrapper vodtoVodDtoWrapper, VodRepository vodRepository) {
        return getBasicInfo(list, contentIds, vodtoVodDtoWrapper, vodRepository);
    }

    private List<BasicInfoOfVodDto> getBasicInfo(List<BasicInfoOfVodDto> list, List<String> contentIds, VodtoVodDtoWrapper vodtoVodDtoWrapper, VodRepository vodRepository) {
        for (String c : contentIds) {
            VodDto vodDto = vodtoVodDtoWrapper.toVodDto((vodRepository.findByContentId(c)));
            BasicInfoOfVodDto basicInfoOfVodDto = BasicInfoOfVodDto.builder()
                    .title(vodDto.getTitle()).contentId(vodDto.getContentId())
                    .posterurl(vodDto.getPosterurl()).build();
            list.add(basicInfoOfVodDto);
        }
        return list;
    }
}
