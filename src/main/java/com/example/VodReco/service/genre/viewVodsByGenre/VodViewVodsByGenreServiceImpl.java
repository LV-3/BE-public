package com.example.VodReco.service.genre.viewVodsByGenre;


import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
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
public class VodViewVodsByGenreServiceImpl implements VodViewVodsByGenreService{

    private final VodRepository vodRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;
    private final ValidateDuplicateSeriesIdWrapper validateDuplicateSeriesIdWrapper;
    private final ContentIdToBasicInfoOfVodsWrapper contentIdToBasicInfoOfVodsWrapper;

    //장르별 Dto 리스트
    @Override
    public List<BasicInfoOfVodDto> viewVodsByGenre(String genre){
        List<BasicInfoOfVodDto> list = new ArrayList<>();
        List<String> contentIds = validateDuplicateSeriesIdWrapper.validateDuplicateSeriesId(vodRepository.findAllByGenre(genre).stream().map(Vod::getContentId).toList());
        return contentIdToBasicInfoOfVodsWrapper.getBasicInfoOfVodDtos(list, contentIds, vodtoVodDtoWrapper, vodRepository);
    }
//
////        String decodedGenre = URLDecoder.decode(genre, "utf-8");
////        System.out.println(decodedGenre); // "한글 장르" 출력됨
////        System.out.println("디코딩 전 = " + genre);

}
