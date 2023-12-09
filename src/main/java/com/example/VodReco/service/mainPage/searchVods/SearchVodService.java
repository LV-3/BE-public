package com.example.VodReco.service.mainPage.searchVods;

import com.example.VodReco.dto.VodDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchVodService {
    List<VodDto> searchVods(String searchTerm);
//    List<VodDto> searchByTitle(String title);
//    List<VodDto> searchByActor(String actorName);
//    List<VodDto> searchByTitleAndActor(String title, String actorName);
}
