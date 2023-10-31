package com.example.VodReco.service;

import com.example.VodReco.domain.UserWish;
import com.example.VodReco.dto.WishResponseDto;
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

    // 컬럼: email / vcode / wish(0or1)
    @Override
    public void saveWish(UserWish userWish) {
        wishRepository.save(userWish);
    }

    //API 테스트용
    //실제 서비스 구현 시 사용자가 찜 삭제하면 바로 데이터 delete되기 때문에 쓸 일 없음
    //추후 삭제
    @Override
    public WishResponseDto findUserWishByVcode(String vcode){
        UserWish userWish = wishRepository.findByVcode(vcode);
        return userWish.toWishResponseDto(userWish);
    }
}
