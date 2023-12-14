package com.example.VodReco.service.mainPage.viewVodsByTag;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.BasicInfoOfVodDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.ContentIdToBasicInfoOfVodsWrapper;
import com.example.VodReco.util.series.ValidateDuplicateSeriesIdWrapper;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VodviewVodsByTagServiceImpl implements VodviewVodsByTagService {

    private final VodRepository vodRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;
    private final ValidateDuplicateSeriesIdWrapper validateDuplicateSeriesIdWrapper;
    private final ContentIdToBasicInfoOfVodsWrapper contentIdToBasicInfoOfVodsWrapper;

    @Override
    public List<BasicInfoOfVodDto> sendEachTagVods(String tags) {
//    public List<BasicInfoOfVodDto> sendEachTagVods(String tag) {
        List<BasicInfoOfVodDto> list = new ArrayList<>();
        List<String> contentIds = validateDuplicateSeriesIdWrapper.validateDuplicateSeriesId(vodRepository.findByUniqueTemplatesContaining(tags).stream().map(Vod::getContentId).toList());
//        List<String> contentIds = validateDuplicateSeriesIdWrapper.validateDuplicateSeriesId(vodRepository.findByUniqueTemplatesContaining(tag).stream().map(Vod::getContentId).toList());
        return contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(list, contentIds, vodtoVodDtoWrapper, vodRepository);
    }
}
