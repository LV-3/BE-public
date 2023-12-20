package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.Rec.PopularVod;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PopularVodRepository extends MongoRepository<PopularVod, Long> {
    List<PopularVod> findTop10ByTimeGroupOrderByCountDesc(String timeGroup);
}
