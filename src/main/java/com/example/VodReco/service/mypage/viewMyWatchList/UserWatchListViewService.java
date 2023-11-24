package com.example.VodReco.service.mypage.viewMyWatchList;

import com.example.VodReco.dto.mypage.ViewMyWatchListDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserWatchListViewService {
    Optional<List<ViewMyWatchListDto>> findMyWatchList(String subsr);

}
