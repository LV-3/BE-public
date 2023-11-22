package com.example.VodReco.service.genre.viewVodsByGenre;

import
        com.example.VodReco.domain.Vod;
import com.example.VodReco.mongoRepository.VodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VodViewVodsByGenreServiceImpl implements VodViewVodsByGenreService{

    private final VodRepository vodRepository;

    //장르별 VodDto 리스트
    @Override
    public List<Vod> viewVodsByGenre(String genre) {
        return vodRepository.findAllByGenre(genre);
    }
}
