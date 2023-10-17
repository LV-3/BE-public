package com.example.VodReco.service;

import com.example.VodReco.domain.UserRating;
import com.example.VodReco.repository.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    //컬럼: usermail/ vcode / rating(1~5)
    public void saveRating(UserRating userRating) {
        ratingRepository.save(userRating);
    }
}
