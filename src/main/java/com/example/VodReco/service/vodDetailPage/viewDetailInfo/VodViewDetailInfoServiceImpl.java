package com.example.VodReco.service.vodDetailPage.viewDetailInfo;

import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.vodDetail.VodDetailResponseDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VodViewDetailInfoServiceImpl implements VodViewDetailInfoService {
    private final VodRepository vodRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;



    // content_id로 Vod 조회
    @Override
    public VodDetailResponseDto getVodByContentId(String contentId) {
        //findByContentId 조회 불가능한 에러: @Id 제거하니 해결(231124)
        VodDto vodDto = vodtoVodDtoWrapper.toVodDto(vodRepository.findByContentId(contentId));
        return VodDetailResponseDto.builder()

                .title(vodDto.getTitle())
                .contentId(vodDto.getContentId())
                .category(vodDto.getCategory())
                .genre(vodDto.getGenre())
                .country(vodDto.getCountry())

                .actors(vodDto.getActors())
                .director(vodDto.getDirector())
                .posterurl(vodDto.getPosterurl())
                .description(vodDto.getDescription())
                .release_year(vodDto.getRelease_year())

                .disp_rtm(vodDto.getDisp_rtm())
                .grade(vodDto.getGrade())

                .build();


    }
}
