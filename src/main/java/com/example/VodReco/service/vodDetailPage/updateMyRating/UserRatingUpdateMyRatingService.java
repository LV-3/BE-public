package com.example.VodReco.service.vodDetailPage.updateMyRating;

import com.example.VodReco.dto.rating.UpdateMyRatingDto;
import org.springframework.stereotype.Service;

@Service
public interface UserRatingUpdateMyRatingService {
    void saveRating(UpdateMyRatingDto updateMyRatingDto);
}
