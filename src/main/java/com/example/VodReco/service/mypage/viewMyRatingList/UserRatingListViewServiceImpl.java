package com.example.VodReco.service.mypage.viewMyRatingList;

import com.example.VodReco.domain.UserRating;
import com.example.VodReco.dto.mypage.ViewMyRatingListDto;
import com.example.VodReco.repository.UserRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRatingListViewServiceImpl implements UserRatingListViewService {
    private final UserRatingRepository userRatingRepository;

    @Override
    public Optional<List<ViewMyRatingListDto>> findMyRatingList(String subsr) {
        List<ViewMyRatingListDto> myRatingListDtos = new ArrayList<>();
        List<UserRating> myRatingList = userRatingRepository.findAllBySubsr(subsr);

        if (myRatingList.isEmpty()) {
            return Optional.empty();
        }

        for (UserRating r : myRatingList){
            ViewMyRatingListDto viewMyRatingListDto = ViewMyRatingListDto.builder()
                    .subsr(r.getSubsr())
                    .contentId(r.getContentId())
                    .rating_date(r.getRating_date())
                    .rating(r.getRating())
                    .review(r.getReview())
                    .title(r.getTitle())
                    .posterurl(r.getPosterurl())
                    .build();
            myRatingListDtos.add(viewMyRatingListDto);
        }
        return Optional.of(myRatingListDtos);
    }
}

