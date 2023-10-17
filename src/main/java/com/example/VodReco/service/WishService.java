package com.example.VodReco.service;

import com.example.VodReco.domain.UserWish;
import com.example.VodReco.repository.WishRepository;
import org.springframework.stereotype.Service;

@Service
public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    // 컬럼: useremail / vcode / wish(0or1)
    public void saveWish(UserWish userWish) {
        wishRepository.save(userWish);
    }
}
