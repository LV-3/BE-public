package com.example.VodReco.service.genre.viewGenres;

import com.example.VodReco.domain.Genres;
import com.example.VodReco.mongoRepository.GenreRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenresViewGenresServiceImplTest {
    GenreRepository genreRepository;

    @Test
    void viewGenreList() {
//        given
        List<Genres> allGenres = genreRepository.findAllGenres();
//        when
//        then
        assertEquals(allGenres.size(), 2);
    }
}