//package com.example.VodReco.service.genre.viewGenres;
//
//import com.example.VodReco.domain.Genre;
//import com.example.VodReco.mongoRepository.GenreRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//@RequiredArgsConstructor
//public class GenreViewGenresServiceImpl implements GenreViewGenresService {
//    private final GenreRepository genreRepository;
//    //모든 장르 리스트
//    @Override
//    public List<String> viewGenreList() {
//        List<String> list = new ArrayList<>();
//        for (Genre g : genreRepository.findByGenre(genre)) {
//            String genre = g.getGenre();
//            list.add(genre);
//        }
//        return list;
//    }
//
//}
