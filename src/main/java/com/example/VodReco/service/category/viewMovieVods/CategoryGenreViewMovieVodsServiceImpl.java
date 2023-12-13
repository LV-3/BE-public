package com.example.VodReco.service.category.viewMovieVods;

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
public class CategoryGenreViewMovieVodsServiceImpl implements CategoryGenreViewMovieVodsService{
    private final VodRepository vodRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;
    private final ContentIdToBasicInfoOfVodsWrapper contentIdToBasicInfoOfVodsWrapper;

    @Override
    public List<BasicInfoOfVodDto> getMovieVodsInfo(String genre2) {
        List<Vod> list = vodRepository.findByCategoryAndGenre("영화", genre2);
        List<String> contentIds = new ArrayList<>();
        List<BasicInfoOfVodDto> basicInfoOfVodDtoList = new ArrayList<>();
        for (Vod v : list) {
            contentIds.add(v.getContentId());
        }
        return contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(basicInfoOfVodDtoList, contentIds, vodtoVodDtoWrapper, vodRepository);
    }
}
