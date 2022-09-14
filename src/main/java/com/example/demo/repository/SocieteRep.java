package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Societe;
import com.example.demo.model.Ville;

public interface SocieteRep extends JpaRepository<Societe, Integer> {
	@Query(value = "SELECT  * from societe where idcategorie = :idc", nativeQuery = true)
	public Page<Societe> listSociete(Pageable paging,int idc);
	
	@Query(value = "SELECT  * from societe where nom like :input and idcategorie = :idc", nativeQuery = true)
	public ArrayList<Societe> listSocieteRec(String input,int idc);

	public ArrayList<Societe> findByIdcategorie(int id);
}
