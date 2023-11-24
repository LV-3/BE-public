package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.Genres;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genres, String> {
    List<Genres> findByGenre(String genre);
}
