package com.example.VodReco.service.mainPage.viewVodsByTag;

import com.example.VodReco.dto.genre.BasicInfoOfVodDto;

import java.util.List;

public interface VodviewVodsByTagService {

    List<BasicInfoOfVodDto> sendEachTagVods(String mood);
}
