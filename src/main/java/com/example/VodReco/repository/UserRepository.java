package com.example.VodReco.repository;


import com.example.VodReco.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // ??????????
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByEmail(String email); //orElse(null) 쓰려고 Optional로 감싸서 리턴

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByNickname(String nickname); //orElse(null) 쓰려고 Optional로 감싸서 리턴
}