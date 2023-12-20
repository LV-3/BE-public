package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.user.UserWatch;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserWatchRepository extends MongoRepository<UserWatch, String> {
    List<UserWatch> findBySubsr(String subsr);

    List<UserWatch> findAllBySubsr(String subsr);

    List<UserWatch> findBySubsrOrderByUserPreferenceDesc(String subsr);
}
