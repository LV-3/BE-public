package com.example.VodReco.controller;

import com.example.VodReco.domain.Movie;
import com.example.VodReco.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;

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

    //상세페이지
    @GetMapping(value = "/movies/{mcode}") //title? mcode?
    public String detail(Model model, @PathVariable("mcode") String mcode){//변하는 id값을 얻을 때는 @PathVariable 써야함

        Movie movie = this.movieService.getMovie(mcode);
        model.addAttribute("movie", movie);
        return "movies/movieDetail";

    }
}
