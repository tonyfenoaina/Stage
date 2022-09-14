package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Enqueteur;
import com.example.demo.model.Utilisateur;

public interface EnqueteurRep extends JpaRepository<Enqueteur, Integer> {

	@Query(value = "SELECT  * from enqueteur", nativeQuery = true)
	public Page<Enqueteur> listEnqueteur(Pageable paging);
	
	@Query(value = "SELECT  * from enqueteur where nom like :input", nativeQuery = true)
	public ArrayList<Enqueteur> listEnqueteurRec(String input);
	
}
