package com.example.VodReco.service.category.viewTVCategories;

import com.example.VodReco.domain.CategoryGenre;
import com.example.VodReco.mongoRepository.CategoryGenreRepository;
import com.example.VodReco.util.StringToListWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryGenreViewTVServiceImpl implements CategoryGenreViewTVService{
    private final CategoryGenreRepository categoryGenreRepository;
    private final StringToListWrapper stringToListWrapper;

    @Override
    public List<String> viewTVCategoriesList(){
        CategoryGenre tv = categoryGenreRepository.findByMaster("TV");
        return stringToListWrapper.stringToList(tv.getSlave());
    }
}
