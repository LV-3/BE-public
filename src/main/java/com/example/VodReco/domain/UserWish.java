package com.example.VodReco.domain;

import com.example.VodReco.dto.wish.UpdateMyWishDto;
import com.example.VodReco.dto.wish.ViewMyWishResponseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user_wish")
public class UserWish {
    @Id
    //Id를 content_id + subsr로 만드는 방안 고려하기(231123)
    @Column(nullable = false, unique = true, name = "unique_id")
//    @ColumnDefault("defaultId")
    private String uniqueId;
    @Column(nullable = false) //unique = true) // unique = true) wish, rating은 email 겹쳐도 됨. 사용자가 여러 개의 vod에 대한 평가 내림(231104)
    private String subsr;
//    @Column(nullable = false)//, unique = true)
    private String contentId;
    private Integer wish;

    //조회 속도 개선을 위해 추가(231122)
    private String title;
    private String posterurl;

    @Builder
    public UserWish(String uniqueId, String subsr, String contentId, Integer wish, String title, String posterurl) {
        this.uniqueId = uniqueId;
        this.subsr = subsr;
        this.contentId = contentId;
        this.wish = wish;
        this.title = title;
        this.posterurl = posterurl;
    }

    public UpdateMyWishDto toUpdateMyWishResponseDto(UserWish userWish) {
        return UpdateMyWishDto.builder()
                .uniqueId(uniqueId)
                .subsr(subsr)
                .contentId(contentId)
                .wish(wish)
                .title(title)
                .posterurl(posterurl)
                .build();
    }

    public ViewMyWishResponseDto toViewMyWishResponseDto(UserWish userWish) {
        return ViewMyWishResponseDto.builder()
                .wish(wish)
                .build();
    }
}
