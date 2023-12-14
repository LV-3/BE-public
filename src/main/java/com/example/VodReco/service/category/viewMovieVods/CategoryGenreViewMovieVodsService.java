package com.example.VodReco.service.category.viewMovieVods;

import com.example.VodReco.dto.BasicInfoOfVodDto;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public interface CategoryGenreViewMovieVodsService {
    List<BasicInfoOfVodDto> getMovieVodsInfo(String genre2) throws UnsupportedEncodingException;
}
