package com.example.VodReco.service.mypage.viewMyRatingList;

import com.example.VodReco.dto.mypage.ViewMyRatingListDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserRatingListViewService {
    Optional<List<ViewMyRatingListDto>> findMyRatingList(String subsr);
}
