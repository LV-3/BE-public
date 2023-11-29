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


    @Override
    public void saveWish(UpdateMyWishRequestDto updateMyWishRequestDto, String contentId) {
        String uniqueId = updateMyWishRequestDto.getSubsr() + contentId;
        UpdateMyWishDto updateMyWishDto = UpdateMyWishDto.builder().uniqueId(uniqueId).subsr(updateMyWishRequestDto.getSubsr())
                .contentId(contentId).wish(updateMyWishRequestDto.getWish()).title(vodRepository.findByContentId(contentId).getTitle()).
                posterurl(vodRepository.findByContentId(contentId).getPosterurl()).build();
        userWishRepository.save(updateMyWishDto.toWishEntity(updateMyWishDto));
        //[세연] kafka에 topic send, userWishViewRepository에 데이터 삽입 후
        //if (userWishViewRepository.findBy() 해서 이 함수의 파라미터와 같으면
        //그때 ResponseEntity.ok 띄우고 // 같지 않으면 에러 status 띄우기(231128)
    }
}
