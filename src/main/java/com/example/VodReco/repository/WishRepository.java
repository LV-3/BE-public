package com.example.VodReco.repository;

import com.example.VodReco.domain.UserWish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<UserWish, Integer> {

    UserWish findByVcode(String vcode);
}
