package com.example.VodReco.service.mainPage.getReco;

import com.example.VodReco.domain.ForDeepFM;
import com.example.VodReco.domain.UserWatch;
import com.example.VodReco.dto.model.toModel.*;
import com.example.VodReco.mongoRepository.ForDeepFMRepository;
import com.example.VodReco.mongoRepository.UserWatchRepository;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.ForDeepFM.ToForDeepFMDtoWrapper;
import com.example.VodReco.util.StringToListWrapper;
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
    private final ForDeepFMRepository forDeepFMRepository;
    private final ToForDeepFMDtoWrapper toForDeepFMDtoWrapper;
    private final VodRepository vodRepository;
    private final StringToListWrapper stringToListWrapper;

    @Override
    public ToModelDto setDataForModel(String subsr) {
        List<EveryDescriptionDto> descriptionResponseList = new ArrayList<>();
        List<EveryMoodDto> moodResponseList = new ArrayList<>();
        List<EveryPersonalDto> personalResponseList = new ArrayList<>();

        //mood는 null들어올 경우 NPE(231127)
        for (UserWatch v : userWatchRepository.findBySubsr(subsr)) {
            String contentId = v.getContentId();
            EveryDescriptionDto everyDescriptionDto = EveryDescriptionDto.builder().content_id(contentId).description(vodtoVodDtoWrapper.toVodDto(vodRepository.findByContentId(contentId)).getDescription()).build();
            EveryMoodDto everyMoodDto = EveryMoodDto.builder().content_id(contentId).mood(stringToListWrapper.stringToList(vodRepository.findByContentId(contentId).getMood())).build();
            System.out.println("콘솔에서 자료형 확인 = " + stringToListWrapper.stringToList(vodRepository.findByContentId(contentId).getMood()));
            System.out.println("vod의 mood 필드 자료형 확인 = " + vodRepository.findByContentId(contentId).getMood());
            descriptionResponseList.add(everyDescriptionDto);
            moodResponseList.add(everyMoodDto);
        }
        for(ForDeepFM f : forDeepFMRepository.findBySubsr(subsr)){
            ForDeepFMDto forDeepFMDto = toForDeepFMDtoWrapper.toForDeepFMDto(f);
            EveryPersonalDto everyPersonalDto = EveryPersonalDto.builder().subsr(subsr).content_id(forDeepFMDto.getContentId()).ct_cl(forDeepFMDto.getCategory())
                    .genre_of_ct_cl(forDeepFMDto.getGenre()).template_A(forDeepFMDto.getMood()).template_B(forDeepFMDto.getGpt_genres()).template_C(forDeepFMDto.getGpt_subjects())
                    .liked(forDeepFMDto.getLiked())
                    .build();
            System.out.println("template_B 자료형 확인 = " + forDeepFMDto.getGpt_genres());
            System.out.println("everypersonalDto의 template_A 자료형 확인 = " + everyPersonalDto.getTemplate_A());
            personalResponseList.add(everyPersonalDto);
        }
        return ToModelDto.builder()
                .description_data(descriptionResponseList)
                .mood_data(moodResponseList)
                .personal_data(personalResponseList)
                .build();

    }
}
