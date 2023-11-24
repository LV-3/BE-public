package com.example.VodReco.service.vodDetailPage.updateMyWish;

import com.example.VodReco.dto.wish.UpdateMyWishDto;
import org.springframework.stereotype.Service;

@Service
public interface UserWishUpdateMyWishService {
    void saveWish(UpdateMyWishDto updateMyWishDto);
}
