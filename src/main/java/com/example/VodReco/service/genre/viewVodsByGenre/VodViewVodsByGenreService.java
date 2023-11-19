package com.example.VodReco.service.genre.viewVodsByGenre;

import com.example.VodReco.domain.Vod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VodViewVodsByGenreService {

    List<Vod> viewVodsByGenre(String genre);
}
