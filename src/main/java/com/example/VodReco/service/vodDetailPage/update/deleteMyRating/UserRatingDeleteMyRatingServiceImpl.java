package com.example.VodReco.service.vodDetailPage.update.deleteMyRating;

import com.example.VodReco.mongoRepository.UserRatingViewRepository;
import com.example.VodReco.repository.UserRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRatingDeleteMyRatingServiceImpl implements UserRatingDeleteMyRatingService{
    private final UserRatingRepository userRatingRepository;
    private final UserRatingViewRepository userRatingViewRepository;

    @Override
    public void deleteRating(String contentId, String subsr) {
        String uniqueId = subsr + contentId;
        userRatingRepository.deleteByUniqueId(uniqueId);
        userRatingViewRepository.deleteByUniqueId(uniqueId);

    }
}

