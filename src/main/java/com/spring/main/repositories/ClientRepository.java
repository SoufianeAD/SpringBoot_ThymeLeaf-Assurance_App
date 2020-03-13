package com.spring.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	public Client findById(int id);
}
