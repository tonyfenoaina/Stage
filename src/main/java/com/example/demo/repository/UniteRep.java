package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Fonction;
import com.example.demo.model.Unite;

public interface UniteRep extends JpaRepository<Unite, Integer> {
	@Query(value = "SELECT  * from unite", nativeQuery = true)
	public Page<Unite> listUnite(Pageable paging);
	
	@Query(value = "SELECT  * from unite where nom like :input", nativeQuery = true)
	public ArrayList<Unite> listUniteRec(String input);


}
