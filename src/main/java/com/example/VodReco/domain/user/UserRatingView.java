package com.example.VodReco.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor
@Document(collection = "user_rating_view")
public class UserRatingView {
//    @Field(name = "_id")
//    private Object id; // 이거 이용하려면 String으로 캐스팅하면 됨(그냥 private String id; 쓰면 됨)

//    몽고디비만 사용하는 게 아니라 다른 DB와 함께 사용하기 때문에 어쩔 수 없는 상황. 몽고디비만 사용한다면 ObjectId를 PK로 두는 게 제일 편할 것이지만
//    이 프로젝트에서는 RDB를 함께 사용했고, 그쪽에서 PK가 uniqueId기 때문에 PK를 새로 만들기보단 이렇게 사용함
    @Id
    @Field(name = "unique_id") //몽고비디는 PK이름이 무조건 _id임. 여기서 이름을 지정해도 우선순위에서 밀려서 무시됨. TODO : 삭제
    private String uniqueId;
    // wish, rating은 subsr 겹쳐도 됨. 사용자가 여러 개의 vod에 대한 평가 내림(231104)
    private String subsr;
    @Field(name = "content_id")
    private String contentId;
    private Integer rating;
//    @Column(nullable = true) // review는 null 가능!
    private String review;
    private String rating_date;
    private String title;
    private String posterurl;

    @Builder
    public UserRatingView(String uniqueId, String subsr, String contentId, Integer rating, String review, String rating_date, String title, String posterurl) {
//        this.id = id;
        this.uniqueId = uniqueId;
        this.subsr = subsr;
        this.contentId = contentId;
        this.rating = rating;
        this.review = review;
        this.rating_date = rating_date;
        this.title = title;
        this.posterurl = posterurl;
    }
}
