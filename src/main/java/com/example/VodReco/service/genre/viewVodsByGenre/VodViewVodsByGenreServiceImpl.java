package com.example.VodReco.service.genre.viewVodsByGenre;


import com.example.VodReco.domain.ForGenreView;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.mongoRepository.ForGenreViewRepository;
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
    private final ForGenreViewRepository forGenreViewRepository;

    //장르별 Dto 리스트
    @Override
    public List<BasicInfoOfVodDto> viewVodsByGenre(String genre) throws UnsupportedEncodingException {
        List<BasicInfoOfVodDto> list = new ArrayList<>();
        //?? 아래 두 줄 다 "한글 장르" 출력됨. 애초에 디코딩 필요없이 그대로 들어옴
//        String decodedGenre = URLDecoder.decode(genre, "utf-8");
//        System.out.println(decodedGenre); // "한글 장르" 출력됨
//        System.out.println("디코딩 전 = " + genre);

        List<ForGenreView> allByGenre = forGenreViewRepository.findAllByGenre(genre);
        for (ForGenreView a : allByGenre) {
            BasicInfoOfVodDto basicInfoOfVodDto = a.forGenreViewToDto(a);
            list.add(basicInfoOfVodDto);
        }
        return list;
    }
}
