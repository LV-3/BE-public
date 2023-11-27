package com.example.VodReco.service.vodDetailPage.rating.deleteMyRating;

import com.example.VodReco.repository.UserRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRatingDeleteMyRatingServiceImpl implements UserRatingDeleteMyRatingService{
    private final UserRatingRepository userRatingRepository;

    @Override
    public void deleteRating(String contentId, String subsr) {
        String uniqueId = subsr + contentId;
        userRatingRepository.deleteByUniqueId(uniqueId);
    }
}

