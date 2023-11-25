package com.example.VodReco.service.vodDetailPage.deleteMyRating;

import com.example.VodReco.repository.UserRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRatingDeleteMyRatingServiceImpl implements UserRatingDeleteMyRatingService{
    private final UserRatingRepository userRatingRepository;

    @Override
    public void deleteRating(String contentId, String subsr) {
        String uniqueId = subsr + contentId;
        System.out.println("새로운 PK 생성 가능? = " + uniqueId);
        userRatingRepository.deleteByUniqueId(uniqueId);
    }
}

