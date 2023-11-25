package com.example.VodReco.service.mainPage.viewVodsByMood;

import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VodviewVodsMyMoodService {

    List<BasicInfoOfVodDto> sendEachMoodVods(String mood);
}
