package com.example.VodReco.service.mypage.viewMyRatingList;

import com.example.VodReco.domain.user.UserRating;
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
//wish, rating을 매길 수 있는 경로 5가지(메인페이지, genre별 vod조회, mood별 vod조회, gpt_subject별 vod조회, gpt_genre별 vod조회)
//        전부 중복검사 후 시리즈별로 묶어서 ... (?) 테이블 다시 요청하기. 시리즈물은 content_id 가장 작은 것만 남기고 다 없앤 테이블 꼭 필요함
//        문제점 1: 모델에서 series_id 넘겨주면 그 중에 하나 뽑아서 메인페이지에 보여줌. -> 여기에 찜or 평점 누르면 대체 여러 개 중 어떤 vod에 매겨져야 함?
//        이걸 가장 작은 content_id로 통일한다고 치자. 이렇게 하면 우리 서비스에서 사용자가 새로 누르는 찜or평점은 한 vod로 모이니까 해결.
//        문제점 2. 시청기록에 있는 content_id는 꼭 가장 작은 content_id 아닐 것. 모두가 무한도전 1회를 보는 건 아님.
//        -> content_id로 vod 조회한 뒤
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

