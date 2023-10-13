package com.example.VodReco.repository;

import com.example.VodReco.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,String> {

    Movie findByMcode(String mcode);
}
