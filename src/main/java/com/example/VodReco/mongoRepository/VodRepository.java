package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.Vod;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//@Repository
public interface VodRepository extends MongoRepository<Vod,String> {
    Vod findByContentId(String contentId);

    List<Vod> findAllByGenre(String genre);
    List<VodMapping> findAllBy();
}