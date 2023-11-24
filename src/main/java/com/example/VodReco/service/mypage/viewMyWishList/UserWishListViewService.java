package com.example.VodReco.service.mypage.viewMyWishList;

import com.example.VodReco.dto.mypage.ViewMyWishListDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserWishListViewService {
    Optional<List<ViewMyWishListDto>> findMyWishList(String subsr);
}
