package com.dragonball.servicesmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dragonball.servicesmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
