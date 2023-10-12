package com.example.loginDemo.controller;

import com.example.loginDemo.domain.Movie;
import com.example.loginDemo.repository.MovieRepository;
import com.example.loginDemo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/all") // 전부 보러가기
    public String showMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movies/movieList";
    }

    @GetMapping("/movies/posters") // 포스터
    public String displayMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movies/poster";
    }

//    @PostMapping("/movies/posters")
//    public Integer
}
