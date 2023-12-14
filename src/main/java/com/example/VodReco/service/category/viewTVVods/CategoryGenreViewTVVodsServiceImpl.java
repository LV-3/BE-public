package com.example.VodReco.service.category.viewTVVods;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.BasicInfoOfVodDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.ContentIdToBasicInfoOfVodsWrapper;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryGenreViewTVVodsServiceImpl implements CategoryGenreViewTVVodsService{
    private final VodRepository vodRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;
    private final ContentIdToBasicInfoOfVodsWrapper contentIdToBasicInfoOfVodsWrapper;

    @Override
    public List<BasicInfoOfVodDto> getTVVodsInfo(String genre1){
        //변수명은 genre1이지만 TV 시사/교양, TV애니메이션, .. 등이 들어옴(231212)
//        String decodedGenre = URLDecoder.decode(genre1, "ascii");
        String replacedGenre = genre1.replaceAll(":", "/");
        List<Vod> list = vodRepository.findByCategory(replacedGenre);
        List<String> contentIds = new ArrayList<>();
        List<BasicInfoOfVodDto> basicInfoOfVodDtoList = new ArrayList<>();
        for (Vod v : list) {
            contentIds.add(v.getContentId());
        }
        return contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(basicInfoOfVodDtoList, contentIds, vodtoVodDtoWrapper, vodRepository);
    }
}
