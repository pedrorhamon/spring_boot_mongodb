package com.dragonball.servicesmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dragonball.servicesmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
