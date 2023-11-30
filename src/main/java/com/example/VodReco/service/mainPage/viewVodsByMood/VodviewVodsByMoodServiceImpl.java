package com.example.VodReco.service.mainPage.viewVodsByMood;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VodviewVodsByMoodServiceImpl{

    private final VodRepository vodRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;

    public List<BasicInfoOfVodDto> sendEachMoodVods(String mood) {
        List<Vod> byMoodContaining = vodRepository.findByMoodContaining(mood);
        List<BasicInfoOfVodDto> list = new ArrayList<>();
        for (Vod v : byMoodContaining) {
            VodDto vodDto = vodtoVodDtoWrapper.toVodDto(v);
            BasicInfoOfVodDto basicInfoOfVodDto = BasicInfoOfVodDto.builder()
                    .contentId(vodDto.getContentId())
                    .posterurl(vodDto.getPosterurl())
                    .title(vodDto.getTitle())
                    .build();
            list.add(basicInfoOfVodDto);
        }
        return list;
    }

}
