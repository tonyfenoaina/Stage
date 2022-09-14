package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Quartier;


public interface QuartierRep extends JpaRepository<Quartier, Integer> {
	@Query(value = "SELECT  * from quartier", nativeQuery = true)
	public Page<Quartier> listQuartier(Pageable paging);
	
	@Query(value = "SELECT  * from quartier where nom like :input ", nativeQuery = true)
	public ArrayList<Quartier> listQuartierRec(String input);
}
