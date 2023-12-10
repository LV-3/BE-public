package com.example.VodReco.service.mypage.viewMyWatchList;

import com.example.VodReco.domain.UserWatch;
import com.example.VodReco.dto.mypage.ViewMyWatchListDto;
import com.example.VodReco.mongoRepository.UserWatchRepository;
import com.example.VodReco.util.series.ValidateDuplicateSeriesIdWrapper;
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
                        .user_preference(w.getUserPreference())
                        .posterurl(w.getPosterurl())
                        .build();
                myWatchListDtos.add(viewMyWatchListDto);
            } else { //content_id 있는 경우 = 시리즈물이 아닌 경우
                ViewMyWatchListDto viewMyWatchListDto = ViewMyWatchListDto.builder()
                        .subsr(w.getSubsr())
                        .contentId(w.getContentId())
                        .title(w.getTitle())
                        .user_preference(w.getUserPreference())
                        .posterurl(w.getPosterurl())
                        .build();
                myWatchListDtos.add(viewMyWatchListDto);
            }
        }
        return Optional.of(myWatchListDtos);
    }

}
