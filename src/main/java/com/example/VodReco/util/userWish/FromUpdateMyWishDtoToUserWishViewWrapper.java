package com.example.VodReco.util.userWish;

import com.example.VodReco.domain.UserWishView;
import com.example.VodReco.dto.wish.UpdateMyWishDto;
import org.springframework.stereotype.Component;

@Component

public class FromUpdateMyWishDtoToUserWishViewWrapper {
    public UserWishView toUserWishView(UpdateMyWishDto updateMyWishDto) {
        return this.updateMyWishDtoToUserWishView(updateMyWishDto);
    }

    private UserWishView updateMyWishDtoToUserWishView(UpdateMyWishDto updateMyWishDto) {
        return UserWishView.builder()
                .uniqueId(updateMyWishDto.getUniqueId())
                .subsr(updateMyWishDto.getSubsr())
                .title(updateMyWishDto.getTitle())
                .contentId(updateMyWishDto.getContentId())
                .posterurl(updateMyWishDto.getPosterurl())
                .wish(updateMyWishDto.getWish())
                .build();
    }
}
