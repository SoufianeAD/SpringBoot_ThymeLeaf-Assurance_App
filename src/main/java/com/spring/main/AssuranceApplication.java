package com.spring.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.main.entities.User;
import com.spring.main.repositories.UserRepository;

@SpringBootApplication
public class AssuranceApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AssuranceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User("admin", "admin123");
		if(userRepository.findAll() == null) {
			userRepository.save(user);
		}
	}

}
