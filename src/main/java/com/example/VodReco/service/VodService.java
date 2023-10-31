package com.example.VodReco.service;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;

import java.util.List;

public interface VodService {

    //    VodDto VodtoVodDto(Vod vod);
    List<VodDto> getAllVods();

    List<String> getAllPosterUrls();

    VodDto getVod(String vcode);
}
