package com.example.VodReco.repository;

import com.example.VodReco.domain.Vod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VodRepository extends JpaRepository<Vod,String> {
    Vod findByContentId(String contentId);
}
