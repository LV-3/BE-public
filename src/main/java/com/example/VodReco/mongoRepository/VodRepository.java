package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.Vod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface VodRepository extends MongoRepository<Vod,String> {
    Vod findByContentId(String contentId);
    List<Vod> findAll();
}
