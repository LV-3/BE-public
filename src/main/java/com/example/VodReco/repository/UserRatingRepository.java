package com.example.VodReco.repository;

import com.example.VodReco.domain.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRatingRepository extends JpaRepository<UserRating, Integer> {

    UserRating findByContentId(String contentId);
}
