package com.example.VodReco.domain.Rec;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Document(collection =  "weather_rec")
public class WeatherRec {
//    private String _id;
//    @Id
    private String weather;
    @Field(name = "content_id")
    private List<String> contentIdList;
    @Field(name = "ImgUrl")
    private String weatherImg;

}



