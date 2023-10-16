package com.example.VodReco.repository;

import com.example.VodReco.domain.WishRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRatingRepository extends JpaRepository<WishRating, String> {

}
