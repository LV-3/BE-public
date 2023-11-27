package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.ForDeepFM;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ForDeepFMRepository extends MongoRepository<ForDeepFM, String> {
    List<ForDeepFM> findBySubsr(String subsr);
}
