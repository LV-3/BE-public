package com.example.VodReco.service.mainPage.viewPopularVods;

import com.example.VodReco.domain.PopularVod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ViewPopularVodsService {
    List<PopularVod> getTop10PopularVods();
}
