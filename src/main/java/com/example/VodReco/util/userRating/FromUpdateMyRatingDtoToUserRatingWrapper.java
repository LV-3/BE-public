package com.example.VodReco.util.userRating;

import com.example.VodReco.domain.user.UserRating;
import com.example.VodReco.dto.rating.UpdateMyRatingDto;
import org.springframework.stereotype.Component;

@Component

public class FromUpdateMyRatingDtoToUserRatingWrapper {
    public UserRating toUserRating(UpdateMyRatingDto updateMyRatingDto) {
        return this.updateMyRatingDtoToUserRating(updateMyRatingDto);
    }

    private UserRating updateMyRatingDtoToUserRating(UpdateMyRatingDto updateMyRatingDto) {
        return UserRating.builder()
                .uniqueId(updateMyRatingDto.getUniqueId())
                .subsr(updateMyRatingDto.getSubsr())
                .title(updateMyRatingDto.getTitle())
                .contentId(updateMyRatingDto.getContentId())
                .posterurl(updateMyRatingDto.getPosterurl())
                .rating(updateMyRatingDto.getRating())
                .rating_date(updateMyRatingDto.getRating_date())
                .review(updateMyRatingDto.getReview())
                .build();
    }
}
