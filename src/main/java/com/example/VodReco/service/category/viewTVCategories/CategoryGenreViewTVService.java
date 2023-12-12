package com.example.VodReco.service.category.viewCategories;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public interface CategoryGenreViewTVService {
    //
    List<String> viewTVSlaveList() throws UnsupportedEncodingException;
}
