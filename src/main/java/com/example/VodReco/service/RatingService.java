package com.example.VodReco.service;

import com.example.VodReco.domain.UserRating;
import com.example.VodReco.dto.RatingResponseDto;

public interface RatingService {

    //컬럼: email/ vcode / rating(1~5)

    //컬럼: usermail/ vcode / rating(1~5)
    void saveRating(UserRating userRating);

    RatingResponseDto findUserRatingByVcode(String vcode);
}
