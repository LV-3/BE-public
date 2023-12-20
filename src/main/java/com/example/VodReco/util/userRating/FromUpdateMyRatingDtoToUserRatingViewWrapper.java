package com.example.VodReco.util.userRating;

import com.example.VodReco.domain.user.UserRatingView;
import com.example.VodReco.dto.rating.UpdateMyRatingDto;
import com.example.VodReco.mongoRepository.UserRatingViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FromUpdateMyRatingDtoToUserRatingViewWrapper {
    private final UserRatingViewRepository userRatingViewRepository;
    public UserRatingView toUserRatingView(UpdateMyRatingDto updateMyRatingDto) {
        return this.updateMyRatingDtoToUserRatingView(updateMyRatingDto);
    }

    private UserRatingView updateMyRatingDtoToUserRatingView(UpdateMyRatingDto updateMyRatingDto) {
//        Object id = userRatingViewRepository.findByUniqueId(updateMyRatingDto.getUniqueId());
        return UserRatingView.builder()
//                .id(id)
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
