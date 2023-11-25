package com.example.VodReco.service.vodDetailPage.deleteMyRating;

import org.springframework.stereotype.Service;

@Service
public interface UserRatingDeleteMyRatingService {
    void deleteRating(String contentId, String subsr);
}
