package com.example.VodReco.service.vodDetailPage.update.updateMyRating;

import com.example.VodReco.dto.rating.UpdateMyRatingRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface UserRatingUpdateMyRatingService {

    void saveRating(String contentId, UpdateMyRatingRequestDto updateMyRatingRequestDto);

}
