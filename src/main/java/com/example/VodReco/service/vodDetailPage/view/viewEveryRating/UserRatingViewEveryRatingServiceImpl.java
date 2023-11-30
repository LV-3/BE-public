package com.example.VodReco.service.vodDetailPage.view.viewEveryRating;

import com.example.VodReco.domain.UserRatingView;
import com.example.VodReco.dto.rating.ViewEveryRatingResponseDto;
import com.example.VodReco.mongoRepository.UserRatingViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRatingViewEveryRatingServiceImpl implements UserRatingViewEveryRatingService{

    private final UserRatingViewRepository userRatingViewRepository;

    @Override
    public List<ViewEveryRatingResponseDto> findEveryUserRating(String contentId) {
        List<ViewEveryRatingResponseDto> ratingResponseDtos = new ArrayList<>();
        List<UserRatingView> ratingList = userRatingViewRepository.findAllByContentId(contentId);
        if (ratingList == null) {
            return null;
        }
        for (UserRatingView r : ratingList) {
            ViewEveryRatingResponseDto viewEveryRatingResponseDto = ViewEveryRatingResponseDto.builder().subsr(r.getSubsr()).rating_date(r.getRating_date()).rating(r.getRating()).review(r.getReview()).title(r.getTitle()).posterurl(r.getPosterurl()).build();
            ratingResponseDtos.add(viewEveryRatingResponseDto);
        }
        return ratingResponseDtos;

    }
}
