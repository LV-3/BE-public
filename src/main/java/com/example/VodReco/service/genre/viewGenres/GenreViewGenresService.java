package com.example.VodReco.service.genre.viewGenres;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public interface GenreViewGenresService {
    List<String> viewGenreList() throws UnsupportedEncodingException;
}
