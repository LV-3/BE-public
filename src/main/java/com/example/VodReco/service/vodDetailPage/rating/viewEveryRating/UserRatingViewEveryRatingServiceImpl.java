package com.example.VodReco.service.vodDetailPage.viewEveryRating;

import com.example.VodReco.domain.UserRating;
import com.example.VodReco.dto.rating.ViewEveryRatingResponseDto;
import com.example.VodReco.repository.UserRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRatingViewEveryRatingServiceImpl implements UserRatingViewEveryRatingService{

    private final UserRatingRepository userRatingRepository;

    @Override
    public List<ViewEveryRatingResponseDto> findEveryUserRating(String contentId) {
        List<ViewEveryRatingResponseDto> ratingResponseDtos = new ArrayList<>();
        List<UserRating> ratingList = userRatingRepository.findAllByContentId(contentId);
        if (ratingList == null) {
            return null;
        }
        for (UserRating r : ratingList) {
            ViewEveryRatingResponseDto viewEveryRatingResponseDto = ViewEveryRatingResponseDto.builder().subsr(r.getSubsr()).rating_date(r.getRating_date()).rating(r.getRating()).review(r.getReview()).title(r.getTitle()).posterurl(r.getPosterurl()).build();
            ratingResponseDtos.add(viewEveryRatingResponseDto);
        }
        return ratingResponseDtos;

    }
}
