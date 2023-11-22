package com.example.VodReco.dto.wish;

import com.example.VodReco.domain.UserWish;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateMyWishDto {
    @Id
    @Column(nullable = false, unique = true)
    private String subsr;
    @Column(nullable = false, unique = true)
    private String contentId;
    private Integer wish;

    //조회 속도 개선을 위해 추가(231122)
    private String title;
    private String posterurl;

    public UpdateMyWishDto(){}

    @Builder
    public UpdateMyWishDto(String subsr, String contentId, Integer wish, String title, String posterurl) {
        this.subsr = subsr;
        this.contentId = contentId;
        this.wish = wish;
        this.title = title;
        this.posterurl = posterurl;
    }

    public UserWish toWishEntity(UpdateMyWishDto updateMyWishDto) {
        return UserWish.builder()
                .subsr(subsr)
                .contentId(contentId)
                .wish(wish)
                .title(title)
                .posterurl(posterurl)
                .build();
    }
}

