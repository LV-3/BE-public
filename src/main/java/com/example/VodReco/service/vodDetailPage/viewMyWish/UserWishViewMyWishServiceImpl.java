package com.example.VodReco.service.vodDetailPage.viewMyWish;

import com.example.VodReco.domain.UserWish;
import com.example.VodReco.dto.wish.ViewMyWishResponseDto;
import com.example.VodReco.repository.UserWishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserWishViewMyWishServiceImpl implements UserWishViewMyWishService {

    private final UserWishRepository userWishRepository;

    @Override
    public ViewMyWishResponseDto findMyWish(String subsr, String contentId){
        UserWish userWish = userWishRepository.findBySubsrAndContentId(subsr, contentId);
        if (userWish == null) {
            return null;
        }
        return userWish.toViewMyWishResponseDto(userWish);
    }
}
