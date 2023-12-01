package com.example.VodReco.service.mypage.viewMyWishList;

import com.example.VodReco.domain.UserWishView;
import com.example.VodReco.dto.mypage.ViewMyWishListDto;
import com.example.VodReco.mongoRepository.UserWishViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserWishListViewServiceImpl implements UserWishListViewService{

    private final UserWishViewRepository userWishViewRepository;
    @Override
    public Optional<List<ViewMyWishListDto>> findMyWishList(String subsr) {
        List<ViewMyWishListDto> myWishListDtos = new ArrayList<>();
        List<UserWishView> myWishList = userWishViewRepository.findAllBySubsr(subsr);

        if (myWishList.isEmpty()) {
            return Optional.empty();
        }

        for (UserWishView w : myWishList){
            ViewMyWishListDto viewMyWishListDto = ViewMyWishListDto.builder()
                    .subsr(w.getSubsr())
                    .contentId(w.getContentId())
                    .wish(w.getWish())
                    .title(w.getTitle())
                    .posterurl(w.getPosterurl())
                    .build();
            myWishListDtos.add(viewMyWishListDto);
        }

        return Optional.of(myWishListDtos);
    }
}
