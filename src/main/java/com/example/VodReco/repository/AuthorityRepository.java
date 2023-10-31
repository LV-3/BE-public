package com.example.VodReco.repository;

import com.example.VodReco.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //이거 왜 소스코드에 없지 당연히 있어야 되는 게 아니었나?
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}