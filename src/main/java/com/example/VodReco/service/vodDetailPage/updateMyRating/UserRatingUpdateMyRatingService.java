package com.example.VodReco.service.vodDetailPage.updateMyRating;

import com.example.VodReco.dto.rating.UpdateMyRatingDto;
import com.example.VodReco.dto.rating.UpdateMyRatingRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface UserRatingUpdateMyRatingService {

    void saveRating(String contentId, UpdateMyRatingRequestDto updateMyRatingRequestDto);


    void deleteRating(String contentId, UpdateMyRatingRequestDto updateMyRatingRequestDto);
}
