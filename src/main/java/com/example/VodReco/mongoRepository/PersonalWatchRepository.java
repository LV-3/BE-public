package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.PersonalWatch;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonalWatchRepository extends MongoRepository<PersonalWatch, String> {
    List<PersonalWatch> findBySubsr(String subsr);
}
