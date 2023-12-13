package com.example.VodReco.service.category.viewKidsEtcVods;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.BasicInfoOfVodDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.ContentIdToBasicInfoOfVodsWrapper;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryGenreViewKidsEtcVodsServiceImpl implements CategoryGenreViewKidsEtcVodsService {
    private final VodRepository vodRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;
    private final ContentIdToBasicInfoOfVodsWrapper contentIdToBasicInfoOfVodsWrapper;

    @Override
    public List<BasicInfoOfVodDto> getKidsEtcVodsInfo(String genre3) {
        //변수명은 genre3이지만 실제로는 카테고리인 키즈, 미분류,  .. 등이 들어옴(231212)
        if (genre3 == "기타") {
            List<Vod> list = vodRepository.findByCategory("미분류");
            List<String> contentIds = new ArrayList<>();
            List<BasicInfoOfVodDto> basicInfoOfVodDtoList = new ArrayList<>();
            for (Vod v : list) {
                contentIds.add(v.getContentId());
            }
            return contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(basicInfoOfVodDtoList, contentIds, vodtoVodDtoWrapper, vodRepository);
        } else {
            List<Vod> list = vodRepository.findByCategory(genre3);
            List<String> contentIds = new ArrayList<>();
            List<BasicInfoOfVodDto> basicInfoOfVodDtoList = new ArrayList<>();
            for (Vod v : list) {
                contentIds.add(v.getContentId());
            }
            return contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(basicInfoOfVodDtoList, contentIds, vodtoVodDtoWrapper, vodRepository);
        }
    }
}
