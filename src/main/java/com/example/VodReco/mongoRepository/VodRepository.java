package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.Vod;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//@Repository jpa가 아니라면 안 써도 됨
public interface VodRepository extends MongoRepository<Vod,String> {
//    Vod findByContentId(String contentId);
    Vod findByContentId(String contentId);
    List<Vod> findAllByGenre(String genre);

    List<Vod> findByMoodContaining(String mood);


}