package com.example.VodReco.util.userWish;

import com.example.VodReco.domain.UserWish;
import com.example.VodReco.dto.wish.UpdateMyWishDto;
import org.springframework.stereotype.Component;

@Component
public class FromUpdateMyWishToUserWishWrapper {
    public UserWish toUserWish(UpdateMyWishDto updateMyWishDto) {
        return this.updateMyWishToUserWish(updateMyWishDto);
    }
    private UserWish updateMyWishToUserWish(UpdateMyWishDto updateMyWishDto) {
        return UserWish.builder().subsr(updateMyWishDto.getSubsr()).uniqueId(updateMyWishDto.getUniqueId()).contentId(updateMyWishDto.getContentId())
                .title(updateMyWishDto.getTitle()).wish(updateMyWishDto.getWish()).posterurl(updateMyWishDto.getPosterurl())
                .build();

    }
}
