package com.example.VodReco.service.genre.viewVodsByGenre;


import com.example.VodReco.domain.ForGenreView;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import com.example.VodReco.mongoRepository.ForGenreViewRepository;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.Vod.VodtoVodDtoWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VodViewVodsByGenreServiceImpl implements VodViewVodsByGenreService{
    private final VodRepository vodRepository;
    private final ForGenreViewRepository forGenreViewRepository;
    private final VodtoVodDtoWrapper vodtoVodDtoWrapper;

    //장르별 Dto 리스트
    @Override
    public List<BasicInfoOfVodDto> viewVodsByGenre(String genre) {
        List<BasicInfoOfVodDto> list = new ArrayList<>();
        List<ForGenreView> allByGenre = forGenreViewRepository.findAllByGenre(genre);
        for (ForGenreView a : allByGenre) {
            BasicInfoOfVodDto basicInfoOfVodDto = a.forGenreViewToDto(a);
            list.add(basicInfoOfVodDto);
        }
        return list;
    }
}
