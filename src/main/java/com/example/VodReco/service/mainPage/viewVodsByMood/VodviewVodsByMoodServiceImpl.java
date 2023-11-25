package com.example.VodReco.service.mainPage.viewVodsByMood;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VodviewVodsByMoodServiceImpl implements VodviewVodsMyMoodService{

    private final VodRepository vodRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;

    @Override
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
