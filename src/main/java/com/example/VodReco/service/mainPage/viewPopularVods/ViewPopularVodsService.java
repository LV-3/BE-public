package com.example.VodReco.service.mainPage.viewPopularVods;

import com.example.VodReco.dto.popular.ViewPopularVodsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ViewPopularVodsService {
    List<ViewPopularVodsDto> getTop10PopularVods();
}
