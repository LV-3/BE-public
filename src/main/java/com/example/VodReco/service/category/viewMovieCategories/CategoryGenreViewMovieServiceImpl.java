package com.example.VodReco.service.category.viewMovieCategories;

import com.example.VodReco.domain.CategoryGenre;
import com.example.VodReco.mongoRepository.CategoryGenreRepository;
import com.example.VodReco.util.StringToListWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryGenreViewMovieServiceImpl implements CategoryGenreViewMovieService {
    private final CategoryGenreRepository categoryGenreRepository;
    private final StringToListWrapper stringToListWrapper;

    @Override
    public List<String> viewMovieGenresList(){
        CategoryGenre movieGenre = categoryGenreRepository.findByMaster("영화");
        return stringToListWrapper.stringToList(movieGenre.getSlave());
    }
}
