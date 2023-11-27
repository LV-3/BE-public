package com.example.VodReco.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "genre")
public class Genres {
//    @Id
//    이거 붙이니까 mongoDB에서 자동 생성하는 Object Id가 genre 필드에 들어감
//genres.getGenre() 하면 genre가 아닌 _id가 조회되는 에러 해결
    private String genre;
}


