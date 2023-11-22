package com.example.VodReco.service.main;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.model.toModel.*;
import com.example.VodReco.mongoRepository.UserWatchRepository;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.service.VodtoVodDto;
import lombok.RequiredArgsConstructor;
import org.hamcrest.core.Every;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VodGetRecoServiceImpl {

    private final UserWatchRepository userWatchRepository;
    private final VodRepository vodRepository;

    public List<String> getRecoFromModel(String subsr) {
        List<?> toModelList = new ArrayList<>();
        List<EveryDescription> descriptionResponseList = new ArrayList<>();
        List<EveryMood> moodResponseList = new ArrayList<>();
        for (Vod v : userWatchRepository.findBySubsr(subsr)) {
            VodDto vodDto = VodtoVodDto.vodtoVodDto(v);
            String contentId = vodDto.getContentId();
            EveryDescription everyDescription = EveryDescription.builder().content_id(contentId).description(vodDto.getDescription()).build();
            EveryMood everyMood = EveryMood.builder().content_id(contentId).mood(vodDto.getMood()).build();
            descriptionResponseList.add(everyDescription);
            moodResponseList.add(everyMood);
        }
        ToDescriptionModelDto toDescriptionModelDto = ToDescriptionModelDto.builder().modelName("description_data").responseData(descriptionResponseList).build();
        ToMoodModelDto toMoodModelDto = ToMoodModelDto.builder().modelName("mood_data").responseData(moodResponseList).build();

        //List안에 자료형 다른 객체 못 들어감, 수정 필요(231122)
//        toModelList.add(toDescriptionModelDto);
//        toModelList.add(toMoodModelDto);
//        ToModelDto toModelDto = ToModelDto.builder().build();
//    }

}
