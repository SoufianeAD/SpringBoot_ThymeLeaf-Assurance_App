package com.spring.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.entities.Remboursement;

public interface RemboursementRepository extends JpaRepository<Remboursement, Integer> {
	@Query(value = "SELECT IFNULL(sum(value), 0)  FROM assurance.remboursement a where a.date like '%-' ?1 '-%'", nativeQuery = true)
	public int countSumRemboursement(String month);
}
