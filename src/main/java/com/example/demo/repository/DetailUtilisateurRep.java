package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.DetailUtilisateur;


public interface DetailUtilisateurRep extends JpaRepository<DetailUtilisateur, Integer> {
	@Query(value = "SELECT  * from detailUtilisateur", nativeQuery = true)
	public Page<DetailUtilisateur> listDetailUtilisateur(Pageable paging);
	
	@Query(value = "SELECT  * from detailUtilisateur where nom like :input", nativeQuery = true)
	public ArrayList<DetailUtilisateur> listDetailUtilisateurRec(String input);
}

