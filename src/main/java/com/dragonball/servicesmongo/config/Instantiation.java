package com.dragonball.servicesmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.dragonball.servicesmongo.domain.Post;
import com.dragonball.servicesmongo.domain.User;
import com.dragonball.servicesmongo.dto.AuthorDTO;
import com.dragonball.servicesmongo.dto.CommentDTO;
import com.dragonball.servicesmongo.repository.PostRepository;
import com.dragonball.servicesmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("20/03/2021"), "Partiu viagem", "Vou viajar para Jampa, Abraços!", 
				new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2021"), "Bom dia ", "Acordei muito feliz!",
				new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem boyzao!", sdf.parse("21/03/2021"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveita!", sdf.parse("25/03/2021"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Bom dia!", sdf.parse("16/06/2021"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
		
	}
}
