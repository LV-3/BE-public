package com.example.VodReco.service.vodDetailPage.viewMyWish;

import com.example.VodReco.dto.wish.UpdateMyWishResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserWishViewMyWishService {

    UpdateMyWishResponseDto findMyWish(String subsr, String contentId);

}
