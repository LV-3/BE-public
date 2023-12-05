package com.example.VodReco.service.mypage.viewMyWatchList;

import com.example.VodReco.domain.UserWatch;
import com.example.VodReco.dto.mypage.ViewMyWatchListDto;
import com.example.VodReco.mongoRepository.UserWatchRepository;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.util.ValidateDuplicateSeriesIdWrapper;
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
    private final ValidateDuplicateSeriesIdWrapper validateDuplicateSeriesIdWrapper;

    @Override
    public Optional<List<ViewMyWatchListDto>> findMyWatchList(String subsr){
        List<ViewMyWatchListDto> myWatchListDtos = new ArrayList<>();
        List<UserWatch> myWatchList = userWatchRepository.findAllBySubsr(subsr);

//        TODO : 시리즈물 처리 필요(231204). if seriesId != null이면 vod테이블 조회해서 -> seriesId로 vodList 조회한 뒤 -> content_id 제일 작은 Vod정보 꺼내오기
//        TODO : 해당 메서드 별도 클래스로 분리 고려.
        if(myWatchList.isEmpty()){
            return Optional.empty();
        }
        for (UserWatch w : myWatchList) {
            if (w.getSeriesId() != null) { // seriesId가 있는 경우 = 시리즈물인 경우
                String minContentId = validateDuplicateSeriesIdWrapper.convertToMinContentId(w.getSeriesId());
                ViewMyWatchListDto viewMyWatchListDto = ViewMyWatchListDto.builder()
                        .subsr(w.getSubsr())
                        .contentId(minContentId)
                        .title(w.getTitle())
                        .user_preference(w.getUser_preference())
                        .posterurl(w.getPosterurl())
                        .build();
                myWatchListDtos.add(viewMyWatchListDto);
            } else { //content_id 있는 경우 = 시리즈물이 아닌 경우
                ViewMyWatchListDto viewMyWatchListDto = ViewMyWatchListDto.builder()
                        .subsr(w.getSubsr())
                        .contentId(w.getContentId())
                        .title(w.getTitle())
                        .user_preference(w.getUser_preference())
                        .posterurl(w.getPosterurl())
                        .build();
                myWatchListDtos.add(viewMyWatchListDto);
            }
        }
        return Optional.of(myWatchListDtos);
    }

}
