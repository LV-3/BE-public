package com.example.VodReco.util.userRating;

import com.example.VodReco.domain.UserRatingView;
import com.example.VodReco.dto.rating.UpdateMyRatingDto;
import org.springframework.stereotype.Component;

@Component

public class FromUpdateMyRatingDtoToUserRatingViewWrapper {
    public UserRatingView toUserRatingView(UpdateMyRatingDto updateMyRatingDto) {
        return this.updateMyRatingDtoToUserRatingView(updateMyRatingDto);
    }

    private UserRatingView updateMyRatingDtoToUserRatingView(UpdateMyRatingDto updateMyRatingDto) {
        return UserRatingView.builder()
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
