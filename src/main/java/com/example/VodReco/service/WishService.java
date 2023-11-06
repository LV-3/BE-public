package com.example.VodReco.service;

import com.example.VodReco.domain.UserWish;
import com.example.VodReco.dto.WishResponseDto;

public interface WishService {

    // 컬럼: email / vcode / wish(0or1)
    void saveWish(UserWish userWish);

    WishResponseDto findUserWishByContentId(String contentId);
}
