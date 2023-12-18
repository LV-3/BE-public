package com.example.VodReco.service.mainPage.getReco;

import com.example.VodReco.domain.PersonalResult;
import com.example.VodReco.domain.Rec.DescriptionResult;
import com.example.VodReco.domain.Rec.MoodResult;
import com.example.VodReco.dto.BasicInfoOfVodDto;
import com.example.VodReco.dto.Recommendation.client.MainResponseDto;
import com.example.VodReco.mongoRepository.DescriptionResultRepository;
import com.example.VodReco.mongoRepository.MoodResultRepository;
import com.example.VodReco.mongoRepository.PersonalResultRepository;
import com.example.VodReco.util.StringToListWrapper;
import com.example.VodReco.util.forRecommendation.SetDataToSendToClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VodGetRecoServiceImpl implements VodGetRecoService {
    private final SetDataToSendToClient setDataToSendToClient;
    private final DescriptionResultRepository descriptionResultRepository;
    private final MoodResultRepository moodResultRepository;
    private final PersonalResultRepository personalResultRepository;
    private final StringToListWrapper stringToListWrapper;



    @Override
    public MainResponseDto getAllContentIdsFromModel(String subsr) {
        DescriptionResult allDescBySubsr = descriptionResultRepository.findBySubsr(subsr);
        MoodResult allMoodBySubsr = moodResultRepository.findBySubsr(subsr);
        PersonalResult allPersBySubsr = personalResultRepository.findBySubsr(subsr);

//        List<String> descIds = setDataToSendToClient.filterNullPosterurl(stringToListWrapper.stringToList(allDescBySubsr.getContentId()));
//        List<String> moodIds = setDataToSendToClient.filterNullPosterurl(stringToListWrapper.stringToList(allMoodBySubsr.getContentId()));
//        List<String> persIds = setDataToSendToClient.filterNullPosterurl(stringToListWrapper.stringToList(allPersBySubsr.getContentId()));

        return MainResponseDto.builder()
                .description_data(stringToListWrapper.stringToList(allDescBySubsr.getContentId()).stream().map(setDataToSendToClient :: buildToClient1stDto).toList())
                .genre_data(stringToListWrapper.stringToList(allMoodBySubsr.getContentId()).stream().map(setDataToSendToClient :: buildToClient1stDto).toList())
                .personal_data(stringToListWrapper.stringToList(allPersBySubsr.getContentId()).stream().map(setDataToSendToClient :: buildToClient1stDto).toList())
                .personal_words(personalResultRepository.findBySubsr(subsr).getPersonal_words())
                .build();


    }


}






