package com.example.VodReco.dynamoRepository;

import com.example.VodReco.domain.Vod;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface VodRepository extends CrudRepository<Vod, String> {

    Vod findByContentId(String contentId);

    List<Vod> findAllByGenre(String genre);
    List<VodMapping> findAllBy();
}
