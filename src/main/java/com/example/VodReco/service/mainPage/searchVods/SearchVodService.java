package com.example.VodReco.service.mainPage.searchVods;

import com.example.VodReco.dto.VodDto;
import com.example.VodReco.dto.genre.BasicInfoOfVodDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchVodService {
    List<BasicInfoOfVodDto> searchVods(String searchTerm);
}
