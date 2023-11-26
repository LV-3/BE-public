package com.example.VodReco.service.mainPage.getReco;

import com.example.VodReco.domain.PersonalWatch;
import com.example.VodReco.domain.UserWatch;
import com.example.VodReco.dto.model.toModel.*;
import com.example.VodReco.mongoRepository.PersonalWatchRepository;
import com.example.VodReco.mongoRepository.UserWatchRepository;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.PersonalWatch.ToPersonalWatchDtoWrapper;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VodGetRecoServiceImpl implements VodGetRecoService{

    private final UserWatchRepository userWatchRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;
    private final PersonalWatchRepository personalWatchRepository;
    private final ToPersonalWatchDtoWrapper toPersonalWatchDtoWrapper;
    private final VodRepository vodRepository;

    @Override
    public ToModelDto setDataFromModel(String subsr) {
        List<EveryDescriptionDto> descriptionResponseList = new ArrayList<>();
        List<EveryMoodDto> moodResponseList = new ArrayList<>();
        List<EveryPersonalDto> personalResponseList = new ArrayList<>();
        //확인 요망(231124)
        //userWatch에서? vods에서?
        for (UserWatch v : userWatchRepository.findBySubsr(subsr)) {
            String contentId = v.getContentId();
            EveryDescriptionDto everyDescriptionDto = EveryDescriptionDto.builder().content_id(contentId).description(vodtoVodDtoWrapper.toVodDto(vodRepository.findByContentId(contentId)).getDescription()).build();
            EveryMoodDto everyMoodDto = EveryMoodDto.builder().content_id(contentId).mood(vodtoVodDtoWrapper.toVodDto(vodRepository.findByContentId(contentId)).getMood()).build();
            descriptionResponseList.add(everyDescriptionDto);
            moodResponseList.add(everyMoodDto);
        }
        for(PersonalWatch p : personalWatchRepository.findBySubsr(subsr)){
            PersonalWatchDto personalWatchDto = toPersonalWatchDtoWrapper.toPersonalWatchDto(p);
            EveryPersonalDto everyPersonalDto = EveryPersonalDto.builder().subsr(subsr).contentId(personalWatchDto.getContentId()).category(personalWatchDto.getCategory())
                    .genre(personalWatchDto.getGenre()).mood(personalWatchDto.getMood()).gpt_genres(personalWatchDto.getGpt_genres()).gpt_subjects(personalWatchDto.getGpt_subjects())
                    .liked(personalWatchDto.getLiked())
                    .build();
            personalResponseList.add(everyPersonalDto);
        }
        return ToModelDto.builder()
                .description_data(descriptionResponseList)
                .mood_data(moodResponseList)
                .personal_data(personalResponseList)
                .build();

    }
}
