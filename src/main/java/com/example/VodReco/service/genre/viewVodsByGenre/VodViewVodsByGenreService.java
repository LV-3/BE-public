package com.example.VodReco.service.genre.viewVodsByGenre;

import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public interface VodViewVodsByGenreService {

    List<BasicInfoOfVodDto> viewVodsByGenre(String genre) throws UnsupportedEncodingException;
}
