package com.example.VodReco.service.rating;

import com.example.VodReco.domain.UserRating;
import com.example.VodReco.dto.rating.RatingResponseDto;
import com.example.VodReco.repository.UserRatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService{
    private final UserRatingRepository userRatingRepository;

    public RatingServiceImpl(UserRatingRepository userRatingRepository) {
        this.userRatingRepository = userRatingRepository;
    }

    //컬럼: email/ vcode / rating(1~5)
    @Override
    public void saveRating(UserRating userRating) {
        userRatingRepository.save(userRating);
    }

    @Override
    public RatingResponseDto findUserRatingByContentId(String contentId) {
        UserRating userRating = userRatingRepository.findByContentId(contentId);
        return userRating.toRatingResponseDto(userRating);
    }
}
