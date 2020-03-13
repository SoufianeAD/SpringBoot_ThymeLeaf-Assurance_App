package com.spring.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.entities.Vehicule;

public interface VehiculeRepository extends JpaRepository<Vehicule, Integer> {
	public List<Vehicule> findByClientId(int id);
	public Vehicule findById(int id);
}
