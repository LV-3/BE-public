package com.example.VodReco.repository;

import com.example.VodReco.domain.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<UserRating, Integer> {

    UserRating findByVcode(String vcode);
}
