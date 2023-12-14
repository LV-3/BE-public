package com.example.VodReco.service.category.viewKidsEtcVods;

import com.example.VodReco.dto.BasicInfoOfVodDto;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public interface CategoryGenreViewKidsEtcVodsService {
    List<BasicInfoOfVodDto> getKidsEtcVodsInfo(String genre3) throws UnsupportedEncodingException;
}
