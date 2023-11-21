package com.example.VodReco.service.vodDetailPage.viewDetailInfo;

import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.vodDetail.VodDetailResponseDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.service.VodtoVodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VodViewDetailInfoServiceImpl implements VodViewDetailInfoService {
    private final VodRepository vodRepository;


    // content_id로 Vod 조회
    @Override
    public VodDetailResponseDto getVodByContentId(String contentId) {
        VodDto vodDto = VodtoVodDto.vodtoVodDto(vodRepository.findByContentId(contentId));
        return VodDetailResponseDto.builder().title(vodDto.getTitle()).contentId(vodDto.getContentId()).genre(vodDto.getGenre()).country(vodDto.getCountry()).actor(vodDto.getActor()).director(vodDto.getDirector()).posterurl(vodDto.getPosterurl()).description(vodDto.getDescription()).build();


    }
}
