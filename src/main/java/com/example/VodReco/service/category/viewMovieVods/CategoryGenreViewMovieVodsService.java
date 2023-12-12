package com.example.VodReco.service.category.viewMovieVods;

import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryGenreViewMovieVodsService {
    List<BasicInfoOfVodDto> getMovieVodsInfo(String genre2);
}
