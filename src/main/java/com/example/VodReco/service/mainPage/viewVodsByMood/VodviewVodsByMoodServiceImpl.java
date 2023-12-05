package com.example.VodReco.service.mainPage.viewVodsByMood;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.ContentIdToBasicInfoOfVodsWrapper;
import com.example.VodReco.util.ValidateDuplicateSeriesIdWrapper;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VodviewVodsByMoodServiceImpl{

    private final VodRepository vodRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;
    private final ValidateDuplicateSeriesIdWrapper validateDuplicateSeriesIdWrapper;
    private final ContentIdToBasicInfoOfVodsWrapper contentIdToBasicInfoOfVodsWrapper;

    public List<BasicInfoOfVodDto> sendEachMoodVods(String mood) {
        List<BasicInfoOfVodDto> list = new ArrayList<>();
        List<String> contentIds = validateDuplicateSeriesIdWrapper.validateDuplicateSeriesId(vodRepository.findByMoodContaining(mood).stream().map(Vod::getContentId).toList());
        return contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(list, contentIds, vodtoVodDtoWrapper, vodRepository);
    }
}
