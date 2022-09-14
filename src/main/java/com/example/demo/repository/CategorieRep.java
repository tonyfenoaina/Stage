package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Categorie;


public interface CategorieRep extends JpaRepository<Categorie, Integer> {
	@Query(value = "SELECT  * from categorie", nativeQuery = true)
	public Page<Categorie> listCategorie(Pageable paging);
	
	@Query(value = "SELECT  * from categorie where nom like :input", nativeQuery = true)
	public ArrayList<Categorie> listCategorieRec(String input);
}
