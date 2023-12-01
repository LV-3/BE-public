package com.example.VodReco.service.vodDetailPage.update.updateMyWish;

import com.example.VodReco.dto.wish.UpdateMyWishDto;
import com.example.VodReco.dto.wish.UpdateMyWishRequestDto;
import com.example.VodReco.mongoRepository.UserWishViewRepository;
import com.example.VodReco.mongoRepository.VodRepository;
import com.example.VodReco.repository.UserWishRepository;
import com.example.VodReco.util.userWish.FromUpdateMyWishDtoToUserWishViewWrapper;
import com.example.VodReco.util.userWish.FromUpdateMyWishDtoToUserWishWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserWishUpdateMyWishServiceImpl implements UserWishUpdateMyWishService{
    private final UserWishRepository userWishRepository;
    private final UserWishViewRepository userWishViewRepository;
    private final VodRepository vodRepository;
    private final FromUpdateMyWishDtoToUserWishWrapper toUserWishWrapper;
    private final FromUpdateMyWishDtoToUserWishViewWrapper toUserWishViewWrapper;
    @Override
    @Transactional
    public void saveWish(UpdateMyWishRequestDto updateMyWishRequestDto, String contentId) {
        String uniqueId = updateMyWishRequestDto.getSubsr() + contentId;
        UpdateMyWishDto updateMyWishDto = UpdateMyWishDto.builder().uniqueId(uniqueId).subsr(updateMyWishRequestDto.getSubsr())
                .contentId(contentId).wish(updateMyWishRequestDto.getWish()).title(vodRepository.findByContentId(contentId).getTitle()).
                posterurl(vodRepository.findByContentId(contentId).getPosterurl()).build();
        userWishRepository.save(toUserWishWrapper.toUserWish(updateMyWishDto));
        userWishViewRepository.save(toUserWishViewWrapper.toUserWishView(updateMyWishDto));
    }
}
