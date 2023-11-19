package com.example.VodReco.service.vodDetailPage.updateMyWish;

import com.example.VodReco.domain.UserWish;
import org.springframework.stereotype.Service;

@Service
public interface UserWishUpdateMyWishService {

    void saveWish(UserWish userWish);
}
