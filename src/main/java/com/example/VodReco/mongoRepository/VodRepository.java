package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.Vod;
import com.example.VodReco.dto.VodDto;
import com.example.VodReco.service.VodMapping;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//@Repository
public interface VodRepository extends MongoRepository<Vod,String> {
    Vod findByContentId(String contentId);

    List<Vod> findAllByGenre(String genre);
    List<VodMapping> findAllBy();

    Vod findBySubsr(String subsr);
}