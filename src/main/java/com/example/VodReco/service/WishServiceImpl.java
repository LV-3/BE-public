package com.example.VodReco.service;

import com.example.VodReco.domain.UserWish;
import com.example.VodReco.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishServiceImpl implements WishService {
    private final WishRepository wishRepository;
    @Autowired
    public WishServiceImpl(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    // 컬럼: useremail / vcode / wish(0or1)
    @Override
    public void saveWish(UserWish userWish) {
        wishRepository.save(userWish);
    }
}
