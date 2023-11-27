package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.UserRating;
import com.example.VodReco.domain.UserRatingView;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRatingViewRepository extends MongoRepository<UserRatingView, String> {

    List<UserRatingView> findAllByContentId(String contentId);

    List<UserRatingView> findAllBySubsr(String contentId);
}
