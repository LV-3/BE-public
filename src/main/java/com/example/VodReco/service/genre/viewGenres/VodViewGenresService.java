package com.example.VodReco.service.genre.viewGenres;

import com.example.VodReco.mongoRepository.VodMapping;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VodViewGenresService {
    List<VodMapping> viewGenreList();
}
