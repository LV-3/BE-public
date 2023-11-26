package com.example.VodReco.service.vodDetailPage.wish.updateMyWish;

import com.example.VodReco.dto.wish.UpdateMyWishDto;
import com.example.VodReco.dto.wish.UpdateMyWishRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface UserWishUpdateMyWishService {
    // 컬럼: id / subsr / content_id / wish(0or1) / title / posterurl
    void saveWish(UpdateMyWishRequestDto updateMyWishRequestDto, String contentId);
}
