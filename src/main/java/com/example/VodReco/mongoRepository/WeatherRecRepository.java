package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.Rec.WeatherRec;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WeatherRecRepository extends MongoRepository<WeatherRec, String> {

    List<WeatherRec> findAll();
}
