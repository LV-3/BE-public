package com.example.VodReco.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
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

}
