package com.example.VodReco.mongoRepository;


import com.example.VodReco.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

//mongoRepository 패키지에 넣어놓고 JpaRepository를 extend하고 있었음(231123)

//@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findBySubsr(String subsr);
}
