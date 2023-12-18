package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.Rec.MoodResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MoodResultRepository extends MongoRepository<MoodResult, String> {
    MoodResult findBySubsr(String subsr);
}
