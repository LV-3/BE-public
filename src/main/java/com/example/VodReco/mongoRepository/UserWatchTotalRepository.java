package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.user.UserWatchTotal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserWatchTotalRepository extends MongoRepository<UserWatchTotal, Object> {

    List<UserWatchTotal> findBySubsr(String subsr);

}
