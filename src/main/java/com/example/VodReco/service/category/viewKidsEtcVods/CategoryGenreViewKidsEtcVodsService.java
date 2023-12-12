package com.example.VodReco.service.category.viewKidsEtcVods;

import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryGenreViewKidsEtcVodsService {
    List<BasicInfoOfVodDto> getKidsEtcVodsInfo(String genre3);
}
