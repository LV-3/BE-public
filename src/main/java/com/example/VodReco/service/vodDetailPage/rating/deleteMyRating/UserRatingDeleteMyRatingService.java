package com.example.VodReco.service.vodDetailPage.rating.deleteMyRating;

import org.springframework.stereotype.Service;

@Service
public interface UserRatingDeleteMyRatingService {
    void deleteRating(String contentId, String subsr);
}
