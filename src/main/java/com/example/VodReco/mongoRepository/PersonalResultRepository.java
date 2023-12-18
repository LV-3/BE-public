package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.PersonalResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonalResultRepository extends MongoRepository<PersonalResult, String> {
    PersonalResult findBySubsr(String subsr);


}
