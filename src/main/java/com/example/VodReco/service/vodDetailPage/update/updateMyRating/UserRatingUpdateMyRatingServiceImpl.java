package com.example.VodReco.service.vodDetailPage.update.updateMyRating;

import com.example.VodReco.dto.rating.UpdateMyRatingDto;
import com.example.VodReco.dto.rating.UpdateMyRatingRequestDto;
import com.example.VodReco.mongoRepository.UserRatingViewRepository;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.repository.UserRatingRepository;
import com.example.VodReco.util.userRating.FromUpdateMyRatingDtoToUserRatingViewWrapper;
import com.example.VodReco.util.userRating.FromUpdateMyRatingDtoToUserRatingWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRatingUpdateMyRatingServiceImpl implements UserRatingUpdateMyRatingService {
    private final UserRatingRepository userRatingRepository;
    private final VodRepository vodRepository;
    private final UserRatingViewRepository userRatingViewRepository;
    private final FromUpdateMyRatingDtoToUserRatingWrapper toUserRatingWrapper;
    private final FromUpdateMyRatingDtoToUserRatingViewWrapper toUserRatingViewWrapper;

    @Override
    public void saveRating(String contentId, UpdateMyRatingRequestDto updateMyRatingRequestDto) {
        String uniqueId = updateMyRatingRequestDto.getSubsr() + contentId;
        UpdateMyRatingDto updateMyRatingDto = UpdateMyRatingDto.builder()
                .uniqueId(uniqueId)
                .subsr(updateMyRatingRequestDto.getSubsr())
                .contentId(contentId)
                .rating(updateMyRatingRequestDto.getRating())
                .review(updateMyRatingRequestDto.getReview())
                .rating_date(updateMyRatingRequestDto.getRating_date())
                .title(vodRepository.findByContentId(contentId).getTitle())
                .posterurl(vodRepository.findByContentId(contentId).getPosterurl())
                .build();
        userRatingRepository.save(toUserRatingWrapper.toUserRating(updateMyRatingDto));
        userRatingViewRepository.save(toUserRatingViewWrapper.toUserRatingView(updateMyRatingDto));
    }

}
