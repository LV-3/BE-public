package com.example.VodReco.service.vodDetailPage.rating.viewEveryRating;

import com.example.VodReco.dto.rating.ViewEveryRatingResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRatingViewEveryRatingService {
    List<ViewEveryRatingResponseDto> findEveryUserRating(String contentId);
}
