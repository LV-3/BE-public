package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.ForGenreView;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ForGenreViewRepository extends MongoRepository<ForGenreView, String> {
     List<ForGenreView> findAllByContentId(String contentId);

     ForGenreView findByContentId(String contentId);

     List<ForGenreView> findAllByGenre(String genre);

}
