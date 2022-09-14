package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.Ville;

public interface VilleRep extends JpaRepository<Ville, Integer> {

	@Query(value = "SELECT  * from ville", nativeQuery = true)
	public Page<Ville> listVille(Pageable paging);
	
	@Query(value = "SELECT  * from ville where nom like :input", nativeQuery = true)
	public ArrayList<Ville> listVilleRec(String input);
	
	
	
}
