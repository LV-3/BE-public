package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.UserWishView;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserWishViewRepository extends MongoRepository<UserWishView, String> {
    UserWishView findBySubsrAndContentId(String contentId, String subsr);
    List<UserWishView> findAllBySubsr(String subsr);

    UserWishView findByUniqueId(String uniqueId);
}
