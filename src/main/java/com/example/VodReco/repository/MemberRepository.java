package com.example.VodReco.repository;

import com.example.VodReco.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member save(Member member);

    Optional<Member> findByEmail(String email);

    List<Member> findAll();
}