package com.example.VodReco.service.genre.viewGenres;

import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.repository.VodMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VodViewGenresServiceImpl implements VodViewGenresService{
    private final VodRepository vodRepository;

    //모든 장르 리스트
    @Override
    public List<VodMapping> viewGenreList() {
        return vodRepository.findAllBy();
    }

}