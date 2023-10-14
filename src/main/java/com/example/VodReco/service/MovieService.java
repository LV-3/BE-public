package com.example.VodReco.service;

import com.example.VodReco.domain.Movie;
import com.example.VodReco.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }


    // mcode로 movie 조회
    public Movie getMovie(String mcode) {
        return movieRepository.findByMcode(mcode);
    }

}
