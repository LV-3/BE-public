package com.example.VodReco.service.vodDetailPage.viewMyWish;

import com.example.VodReco.dto.wish.ViewMyWishResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserWishViewMyWishService {
    public ViewMyWishResponseDto findMyWish(String subsr, String contentId);
}
