package com.example.VodReco.service;

import com.example.VodReco.domain.UserRating;

public interface RatingService {

    //컬럼: usermail/ vcode / rating(1~5)
    void saveRating(UserRating userRating);
}
