package com.spring.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserNameAndPassword(String userName, String password);
}
