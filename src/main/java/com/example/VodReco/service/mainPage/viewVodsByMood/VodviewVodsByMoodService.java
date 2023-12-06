package com.example.VodReco.service.mainPage.viewVodsByMood;

import com.example.VodReco.dto.genre.BasicInfoOfVodDto;

import java.util.List;

public interface VodviewVodsByMoodService {
    List<BasicInfoOfVodDto> sendEachMoodVods(String mood);
}
