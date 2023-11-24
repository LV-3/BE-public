package com.example.VodReco.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "genre")
public class Genres {
    @Id
    private String genre;
}
