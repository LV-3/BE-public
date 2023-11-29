package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.User;
import com.example.VodReco.domain.UserWatch;
import com.example.VodReco.domain.Vod;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserWatchRepository extends MongoRepository<UserWatch, String> {
    List<UserWatch> findBySubsr(String subsr);

    List<UserWatch> findAllBySubsr(String subsr);
}