package com.example.VodReco.service.vodDetailPage.updateMyWish;

import com.example.VodReco.dto.wish.UpdateMyWishDto;
import com.example.VodReco.repository.UserWishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserWishUpdateMyWishServiceImpl implements UserWishUpdateMyWishService{

    private UserWishRepository userWishRepository;

    // 컬럼: id / subsr / content_id / wish(0or1)
    @Override
    public void saveWish(UpdateMyWishDto updateMyWishDto) {
        userWishRepository.save(updateMyWishDto.toWishEntity(updateMyWishDto));
    }
}
