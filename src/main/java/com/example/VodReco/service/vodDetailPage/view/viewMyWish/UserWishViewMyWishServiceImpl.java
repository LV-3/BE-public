package com.example.VodReco.service.vodDetailPage.view.viewMyWish;

import com.example.VodReco.domain.UserWishView;
import com.example.VodReco.dto.wish.ViewMyWishResponseDto;
import com.example.VodReco.mongoRepository.UserWishViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserWishViewMyWishServiceImpl implements UserWishViewMyWishService {

    private final UserWishViewRepository userWishViewRepository;

    @Override
    public ViewMyWishResponseDto findMyWish(String subsr, String contentId){
        UserWishView userWishView = userWishViewRepository.findBySubsrAndContentId(subsr, contentId);
        //dto로 받기. 수정 요망(231124)

        if (userWishView.getWish() == 0) {
            return null;
        }
        // TODO : 변환 메서드 별도 클래스로 분리
        return userWishView.toViewMyWishResponseDto(userWishView);
    }
}
