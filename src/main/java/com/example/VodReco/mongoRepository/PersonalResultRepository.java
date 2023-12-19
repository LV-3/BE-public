package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.Rec.PersonalResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonalResultRepository extends MongoRepository<PersonalResult, String> {
    PersonalResult findBySubsr(String subsr);


}
