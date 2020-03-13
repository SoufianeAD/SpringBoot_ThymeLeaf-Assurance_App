package com.spring.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.entities.Accident;

public interface AccidentRepository extends JpaRepository<Accident, Integer> {
	public List<Accident> findByVehiculeId(int id);
	public Accident findById(int id);
	@Query(value = "SELECT count(id) FROM assurance.accident a where a.date like '%-' ?1 '-%'", nativeQuery = true)
	public int countAccident(String month);
}
