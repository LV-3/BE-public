package com.example.VodReco.service.category.viewTVVods;

import com.example.VodReco.dto.BasicInfoOfVodDto;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public interface CategoryGenreViewTVVodsService {
    List<BasicInfoOfVodDto> getTVVodsInfo(String genre1) throws UnsupportedEncodingException;
}
