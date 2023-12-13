package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.CategoryGenre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryGenreRepository extends MongoRepository<CategoryGenre, String> {
    CategoryGenre findByMaster(String master);
}
