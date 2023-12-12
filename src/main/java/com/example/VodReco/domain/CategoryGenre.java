package com.example.VodReco.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "categories_genres")
public class CategoryGenre {
    private String master;
    private String slave;
}
