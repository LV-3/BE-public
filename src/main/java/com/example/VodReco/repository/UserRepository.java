package com.example.VodReco.repository;


import com.example.VodReco.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findBySubsr(String subsr);
}
