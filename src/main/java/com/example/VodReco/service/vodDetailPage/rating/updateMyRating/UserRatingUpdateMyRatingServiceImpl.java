package com.example.VodReco.service.vodDetailPage.rating.updateMyRating;

import com.example.VodReco.dto.rating.UpdateMyRatingDto;
import com.example.VodReco.dto.rating.UpdateMyRatingRequestDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.repository.UserRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRatingUpdateMyRatingServiceImpl implements UserRatingUpdateMyRatingService {
    private final UserRatingRepository userRatingRepository;
    private final VodRepository vodRepository;

    @Override
    public void saveRating(String contentId, UpdateMyRatingRequestDto updateMyRatingRequestDto) {
        String uniqueId = updateMyRatingRequestDto.getSubsr() + contentId;
        System.out.println("새로운 PK 생성 가능? = " + uniqueId);
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
        userRatingRepository.save(updateMyRatingDto.toUserRatingEntity(updateMyRatingDto));
    }

}
