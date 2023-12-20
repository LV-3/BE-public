package com.example.VodReco.service.vodDetailPage.view.viewVodsByTag;

import com.example.VodReco.aspects.LogExecutionTime;
import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.BasicInfoOfVodDto;
import com.example.VodReco.dto.vodDetail.TagsResponseDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.ContentIdToBasicInfoOfVodsWrapper;
import com.example.VodReco.util.RandomContentIdsExtractor;
import com.example.VodReco.util.StringToListWrapper;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VodviewVodsByTagServiceImpl implements VodviewVodsByTagService {

    private final VodRepository vodRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;
    private final ContentIdToBasicInfoOfVodsWrapper contentIdToBasicInfoOfVodsWrapper;
    private final StringToListWrapper stringToListWrapper;
    private final RandomContentIdsExtractor randomContentIdsExtractor;


    @LogExecutionTime
    @Override
    public TagsResponseDto sendEachTagVods(String contentId) {
        List<String> tags = stringToListWrapper.stringToList(vodRepository.findByContentId(contentId).getUniqueTemplates());
        if (tags == null) {
            return TagsResponseDto.builder().tags(new ArrayList<>()).tag1(new ArrayList<>()).tag2(new ArrayList<>()).tag3(new ArrayList<>()).build();
        } else {
            List<Vod> byUniqueTemplatesContaining = vodRepository.findByUniqueTemplatesContaining(tags.get(0));
            if (tags.size() == 1) {
                List<String> contentIds = new ArrayList<>();
                List<BasicInfoOfVodDto> list = new ArrayList<>();
                for (Vod v : byUniqueTemplatesContaining) {
                    contentIds.add(v.getContentId());
                }
                //contentId 리스트에서 자기 자신 제외
                contentIds.remove(contentId);
                //5개 랜덤 추출
                List<String> extractedContentIds = randomContentIdsExtractor.extractFiveRandContentIds(contentIds);

                List<BasicInfoOfVodDto> basicInfoOfVodDtos = contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(list, extractedContentIds, vodtoVodDtoWrapper, vodRepository);
                return TagsResponseDto.builder().tags(tags).tag1(basicInfoOfVodDtos).build();
            } else {
                List<Vod> byUniqueTemplatesContaining2 = vodRepository.findByUniqueTemplatesContaining(tags.get(1));
                if (tags.size() == 2) {
                    List<String> contentIds1 = new ArrayList<>();
                    List<String> contentIds2 = new ArrayList<>();
                    List<BasicInfoOfVodDto> tag1s = new ArrayList<>();
                    List<BasicInfoOfVodDto> tag2s = new ArrayList<>();
                    for (Vod v : byUniqueTemplatesContaining) {
                        contentIds1.add(v.getContentId());
                    }
                    for (Vod v : byUniqueTemplatesContaining2) {
                        contentIds2.add(v.getContentId());
                    }
                    contentIds1.remove(contentId);
                    contentIds2.remove(contentId);
                    //5개 랜덤 추출
                    List<String> extractedContentIds1 = randomContentIdsExtractor.extractFiveRandContentIds(contentIds1);
                    List<String> extractedContentIds2 = randomContentIdsExtractor.extractFiveRandContentIds(contentIds2);
                    List<BasicInfoOfVodDto> basicInfoOfTag1VodDtos = contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(tag1s, extractedContentIds1, vodtoVodDtoWrapper, vodRepository);
                    List<BasicInfoOfVodDto> basicInfoOfTag2VodDtos = contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(tag2s, extractedContentIds2, vodtoVodDtoWrapper, vodRepository);
                    return TagsResponseDto.builder().tags(tags).tag1(basicInfoOfTag1VodDtos).tag2(basicInfoOfTag2VodDtos).build();
                } else {
                    List<Vod> byUniqueTemplatesContaining3 = vodRepository.findByUniqueTemplatesContaining(tags.get(2));
                    List<String> contentIds1 = new ArrayList<>();
                    List<String> contentIds2 = new ArrayList<>();
                    List<String> contentIds3 = new ArrayList<>();
                    List<BasicInfoOfVodDto> tag1s = new ArrayList<>();
                    List<BasicInfoOfVodDto> tag2s = new ArrayList<>();
                    List<BasicInfoOfVodDto> tag3s = new ArrayList<>();
                    for (Vod v : byUniqueTemplatesContaining) {
                        contentIds1.add(v.getContentId());
                    }
                    for (Vod v : byUniqueTemplatesContaining2) {
                        contentIds2.add(v.getContentId());
                    }
                    for (Vod v : byUniqueTemplatesContaining3) {
                        contentIds3.add(v.getContentId());
                    }
                    contentIds1.remove(contentId);
                    contentIds2.remove(contentId);
                    contentIds3.remove(contentId);
                    //5개 랜덤 추출
                    List<String> extractedContentIds1 = randomContentIdsExtractor.extractFiveRandContentIds(contentIds1);
                    List<String> extractedContentIds2 = randomContentIdsExtractor.extractFiveRandContentIds(contentIds2);
                    List<String> extractedContentIds3 = randomContentIdsExtractor.extractFiveRandContentIds(contentIds3);
                    List<BasicInfoOfVodDto> basicInfoOfTag1VodDtos = contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(tag1s, extractedContentIds1, vodtoVodDtoWrapper, vodRepository);
                    List<BasicInfoOfVodDto> basicInfoOfTag2VodDtos = contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(tag2s, extractedContentIds2, vodtoVodDtoWrapper, vodRepository);
                    List<BasicInfoOfVodDto> basicInfoOfTag3VodDtos = contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(tag3s, extractedContentIds3, vodtoVodDtoWrapper, vodRepository);
                    return TagsResponseDto.builder().tags(tags).tag1(basicInfoOfTag1VodDtos).tag2(basicInfoOfTag2VodDtos).tag3(basicInfoOfTag3VodDtos).build();
                }
            }
        }
    }
}
