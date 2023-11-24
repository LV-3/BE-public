package com.example.VodReco.service.genre.viewGenres;

import com.example.VodReco.domain.Genre;
import com.example.VodReco.mongoRepository.GenreRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenreViewGenresServiceImplTest {
    GenreRepository genreRepository;

    @Test
    void viewGenreList() {
//        given
        List<Genre> allGenres = genreRepository.findAllGenres();;
//        when
//        then
        assertEquals(allGenres.size(), 2);
    }
}