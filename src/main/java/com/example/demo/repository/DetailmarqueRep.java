package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Detailmarque;
import com.example.demo.model.Detailquartier;

public interface DetailmarqueRep extends JpaRepository<Detailmarque, Integer> {
	
	@Query(value = "SELECT  * from detailmarque where idcategorie = :idcateg", nativeQuery = true)
	public Page<Detailmarque> listMarque(Pageable paging,int idcateg);
	
	@Query(value = "SELECT  * from detailmarque where nom like :input and idcategorie = :idcateg", nativeQuery = true)
	public ArrayList<Detailmarque> listMarqueRec(String input,int idcateg);

	@Query(value = "SELECT  * from detailmarque where nom like :input", nativeQuery = true)
	public ArrayList<Detailmarque> rechercheEvolution(String input);

}
