package com.example.VodReco.dto.wish;

import com.example.VodReco.domain.UserWish;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateMyWishDto {
    private String uniqueId;
    private String subsr;
    private String contentId;
    private Integer wish;

    //조회 속도 개선을 위해 추가(231122)
    private String title;
    private String posterurl;

    public UpdateMyWishDto(){}

    @Builder
    public UpdateMyWishDto(String uniqueId, String subsr, String contentId, Integer wish, String title, String posterurl) {
        this.uniqueId = uniqueId;
        this.subsr = subsr;
        this.contentId = contentId;
        this.wish = wish;
        this.title = title;
        this.posterurl = posterurl;
    }

    public UserWish toWishEntity(UpdateMyWishDto updateMyWishDto) {
        return UserWish.builder()
                .uniqueId(uniqueId)
                .subsr(subsr)
                .contentId(contentId)
                .wish(wish)
                .title(title)
                .posterurl(posterurl)
                .build();
    }
}

