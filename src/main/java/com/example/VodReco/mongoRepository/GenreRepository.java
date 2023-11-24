package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {
    List<Genre> findByGenre(String genre);
}
