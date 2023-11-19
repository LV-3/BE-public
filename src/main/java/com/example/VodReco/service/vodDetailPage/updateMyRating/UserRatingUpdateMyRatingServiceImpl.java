package com.example.VodReco.service.vodDetailPage.updateMyRating;

import com.example.VodReco.dto.rating.UpdateMyRatingDto;
import com.example.VodReco.repository.UserRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRatingUpdateMyRatingServiceImpl implements UserRatingUpdateMyRatingService{
    private final UserRatingRepository userRatingRepository;

    @Override
    public void saveRating(UpdateMyRatingDto updateMyRatingDto) {
        userRatingRepository.save(updateMyRatingDto.toUserRatingEntity(updateMyRatingDto));
    }
}
