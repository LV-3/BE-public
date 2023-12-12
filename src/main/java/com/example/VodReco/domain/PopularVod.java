package com.example.VodReco.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "popular_vods")
public class PopularVod {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
    @JsonIgnore
    @Field(name = "_id")
    private ObjectId mongoId;
    @Field(name = "content_id")
    private String contentId;
    @Field(name = "TimeGroup")
    private String timeGroup;
    @Field(name = "Count")
    private Integer count;
    private String title;
    @Field(name = "ImgUrl")
    private String posterurl;


    @Builder
    public PopularVod(String contentId, String timeGroup, Integer count, String title, String posterurl) {
        this.contentId = contentId;
        this.timeGroup = timeGroup;
        this.count = count;
        this.title = title;
        this.posterurl = posterurl;
    }

}
