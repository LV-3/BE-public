package com.example.VodReco.service.genre.viewGenres;

import com.example.VodReco.repository.VodMapping;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VodViewGenresService {
    List<VodMapping> viewGenreList();
}
