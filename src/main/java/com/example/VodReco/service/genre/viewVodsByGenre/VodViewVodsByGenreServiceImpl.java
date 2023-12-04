package com.example.VodReco.service.genre.viewVodsByGenre;


import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VodViewVodsByGenreServiceImpl implements VodViewVodsByGenreService{

    private final VodRepository vodRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;

    //장르별 Dto 리스트
    @Override
    public List<BasicInfoOfVodDto> viewVodsByGenre(String genre) throws UnsupportedEncodingException {
        List<BasicInfoOfVodDto> list = new ArrayList<>();
        //?? 아래 두 줄 다 "한글 장르" 출력됨. 애초에 디코딩 필요없이 그대로 들어옴
//        String decodedGenre = URLDecoder.decode(genre, "utf-8");
//        System.out.println(decodedGenre); // "한글 장르" 출력됨
//        System.out.println("디코딩 전 = " + genre);

        List<Vod> allByGenre = vodRepository.findAllByGenre(genre);
        for (Vod v : allByGenre) {
            VodDto vodDto = vodtoVodDtoWrapper.toVodDto(v);
            BasicInfoOfVodDto basicInfoOfVodDto = BasicInfoOfVodDto.builder()
                    .title(vodDto.getTitle()).contentId(vodDto.getContentId())
                    .posterurl(vodDto.getPosterurl()).build();
            list.add(basicInfoOfVodDto);
        }
        return list;
    }
}
