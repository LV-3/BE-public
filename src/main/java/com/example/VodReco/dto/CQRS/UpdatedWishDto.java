package com.example.VodReco.dto.CQRS;

import com.example.VodReco.domain.UserWishView;
import com.example.VodReco.dto.wish.UpdateMyWishDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


//필드 총 6개
@Getter
@NoArgsConstructor
@ToString
public class UpdatedWishDto {
    private String task;
    private UpdateMyWishDto updateMyWishDto;
    @Builder
    public UpdatedWishDto(String task, UpdateMyWishDto updateMyWishDto) {
        this.task = task;
        this.updateMyWishDto = updateMyWishDto;
    }

//@Component 스프링 빈 등록 시 에러남. 이유 불명(231129)
    //TODO: 별도 클래스 분리 요망(231129)
    public static UserWishView toUserWishViewEntity(UpdatedWishDto updatedWishDto) {
        UpdateMyWishDto myWishDto = updatedWishDto.getUpdateMyWishDto();
        return UserWishView.builder()
                .uniqueId(myWishDto.getUniqueId())
                .subsr(myWishDto.getSubsr())
                .title(myWishDto.getTitle())
                .posterurl(myWishDto.getPosterurl())
                .contentId(myWishDto.getContentId())
                .build();
    }
}