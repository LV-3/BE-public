package com.example.loginDemo.service;

import com.example.loginDemo.domain.Movie;
import com.example.loginDemo.repository.MovieRepository;
import lombok.Setter;
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

}
