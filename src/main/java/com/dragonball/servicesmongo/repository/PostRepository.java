package com.dragonball.servicesmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.dragonball.servicesmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	@Query("{ 'search': { $regex: ?0, $options: ''} }")
	List<Post> search(String text);
	
	List<Post> findByTitleContaining(String text);

}
