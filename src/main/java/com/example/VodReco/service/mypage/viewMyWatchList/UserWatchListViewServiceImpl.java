package com.example.VodReco.service.mypage.viewMyWatchList;

import com.example.VodReco.domain.UserWatch;
import com.example.VodReco.dto.mypage.ViewMyWatchListDto;
import com.example.VodReco.mongoRepository.UserWatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserWatchListViewServiceImpl implements UserWatchListViewService{
    private final UserWatchRepository userWatchRepository;

    @Override
    public Optional<List<ViewMyWatchListDto>> findMyWatchList(String subsr){
        List<ViewMyWatchListDto> myWatchListDtos = new ArrayList<>();
        List<UserWatch> myWatchList = userWatchRepository.findAllBySubsr(subsr);

//        TODO : 시리즈물 처리 필요(231204). content_id로 조회된 모든 vod is_series 검사한 뒤 시리즈물이면 content_id 가장 작은 vod 뽑아서 화면에 보내기
//        TODO : 중복 content_id 중 시리즈물 아닌 vod의 user_preference 계산 로직 추가
//        TODO : 중복 content_id 중 시리즈물인 vod의 user_preference 계산 로직 추가
        if(myWatchList.isEmpty()){
            return Optional.empty();
        }
        for (UserWatch w : myWatchList) {
            ViewMyWatchListDto viewMyWatchListDto = ViewMyWatchListDto.builder()
                    .subsr(w.getSubsr())
                    .contentId(w.getContentId())
                    .title(w.getTitle())
                    .user_preference(w.getUser_preference())
                    .posterurl(w.getPosterurl())
                    .build();
            myWatchListDtos.add(viewMyWatchListDto);
        }
        return Optional.of(myWatchListDtos);
    }

}
