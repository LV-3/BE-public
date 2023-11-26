package com.example.VodReco.service.vodDetailPage.wish.updateMyWish;

import com.example.VodReco.dto.wish.UpdateMyWishDto;
import com.example.VodReco.dto.wish.UpdateMyWishRequestDto;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.repository.UserWishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserWishUpdateMyWishServiceImpl implements UserWishUpdateMyWishService{
    private final UserWishRepository userWishRepository;
    private final VodRepository vodRepository;


    // 컬럼: id / subsr / content_id / wish(0or1) / title / posterurl
    @Override
    public void saveWish(UpdateMyWishRequestDto updateMyWishRequestDto, String contentId) {
        String uniqueId = updateMyWishRequestDto.getSubsr() + contentId;
        System.out.println("새로운 PK 생성 가능? = " + uniqueId);
        UpdateMyWishDto updateMyWishDto = UpdateMyWishDto.builder().uniqueId(uniqueId).subsr(updateMyWishRequestDto.getSubsr())
                .contentId(contentId).wish(updateMyWishRequestDto.getWish()).title(vodRepository.findByContentId(contentId).getTitle()).
                posterurl(vodRepository.findByContentId(contentId).getPosterurl()).build();
        userWishRepository.save(updateMyWishDto.toWishEntity(updateMyWishDto));
    }
}
