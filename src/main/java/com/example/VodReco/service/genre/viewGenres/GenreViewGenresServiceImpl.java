package com.example.VodReco.service.genre.viewGenres;

import com.example.VodReco.domain.Genres;
import com.example.VodReco.mongoRepository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GenreViewGenresServiceImpl implements GenreViewGenresService {
    private final GenreRepository genreRepository;
    //모든 장르 리스트
    @Override
    public List<String> viewGenreList() throws UnsupportedEncodingException {
        List<String> list = new ArrayList<>();
        for (Genres g : genreRepository.findAll()) {
            String genre = g.getGenre();
            list.add(genre);
            //서버에서 클라이언트에 한글 보낼 때는 필요없음(231128)
//            try {
//                String encodedGenre = URLEncoder.encode(genre, "utf-8");
//                list.add(encodedGenre);
//            } catch (UnsupportedEncodingException e) {
//                System.out.println("인코딩 불가: " + genre);
//            }
        }
        return list;
    }

}
