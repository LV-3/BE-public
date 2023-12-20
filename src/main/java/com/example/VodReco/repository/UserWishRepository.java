package com.example.VodReco.repository;

import com.example.VodReco.domain.user.UserWish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserWishRepository extends JpaRepository<UserWish, Integer> {
    UserWish findBySubsrAndContentId(String contentId, String subsr);
    List<UserWish> findAllBySubsr(String subsr);
}
