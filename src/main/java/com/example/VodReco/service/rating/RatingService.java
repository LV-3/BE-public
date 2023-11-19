package com.example.VodReco.service.rating;

import com.example.VodReco.domain.UserRating;
import com.example.VodReco.dto.rating.RatingResponseDto;

public interface RatingService {

    //컬럼: email/ vcode / rating(1~5)

    //컬럼: usermail/ vcode / rating(1~5)
    void saveRating(UserRating userRating);

    RatingResponseDto findUserRatingByContentId(String contentId);
}
