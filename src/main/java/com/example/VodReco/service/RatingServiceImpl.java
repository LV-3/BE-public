package com.example.VodReco.service;

import com.example.VodReco.domain.UserRating;
import com.example.VodReco.dto.RatingResponseDto;
import com.example.VodReco.repository.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService{
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    //컬럼: email/ vcode / rating(1~5)
    @Override
    public void saveRating(UserRating userRating) {
        ratingRepository.save(userRating);
    }

    @Override
    public RatingResponseDto findUserRatingByContentId(String contentId) {
        UserRating userRating = ratingRepository.findByContentId(contentId);
        return userRating.toRatingResponseDto(userRating);
    }
}
