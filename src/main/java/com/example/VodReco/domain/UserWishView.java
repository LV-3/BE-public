package com.example.VodReco.domain;

import com.example.VodReco.dto.wish.ViewMyWishResponseDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Document(collection = "user_wish_view")
public class UserWishView {
    @Id
    @Field(name = "unique_id")
//    @ColumnDefault("defaultId")
    private String uniqueId;
    private String subsr;
    @Field(name = "content_id")
    private String contentId;
    private Integer wish;
    private String title;
    private String posterurl;

    @Builder
    public UserWishView(String uniqueId, String subsr, String contentId, Integer wish, String title, String posterurl) {
        this.uniqueId = uniqueId;
        this.subsr = subsr;
        this.contentId = contentId;
        this.wish = wish;
        this.title = title;
        this.posterurl = posterurl;
    }

    public ViewMyWishResponseDto toViewMyWishResponseDto(UserWishView userWishView) {
        return ViewMyWishResponseDto.builder()
                .wish(wish)
                .build();
    }
}
