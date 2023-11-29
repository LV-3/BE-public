package com.example.VodReco.repository;

import com.example.VodReco.domain.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingRepository extends JpaRepository<LogEntity, Long> {

}
