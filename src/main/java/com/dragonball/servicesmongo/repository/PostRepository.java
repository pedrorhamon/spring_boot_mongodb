package com.dragonball.servicesmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.dragonball.servicesmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	@Query("{ 'search': { $regex: ?0, $options: 'i'} }")
	List<Post> search(String text);
	
	List<Post> findByTitleContaining(String text);
	
	@Query("{ $and: [ {date: {$gte: ?1} }, { date: { $lte: ?2} } "
			+ "{ $or: [ { 'search': { $regex: ?0, $options: 'i'} }, "
			+ "{ 'body': { $regex: ?0, $options: ''} }, "
			+ "{ 'comments.text': { $regex: ?0, $options: ''} } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);

}
