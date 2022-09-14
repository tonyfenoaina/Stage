package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Detailmarque;
import com.example.demo.model.Detailproduit;

public interface DetailProduitRep extends JpaRepository<Detailproduit, Integer> {
	
	@Query(value = "SELECT  * from detailproduit where idmarque = :idm", nativeQuery = true)
	public Page<Detailproduit> listProduit(Pageable paging,int idm);
	
	@Query(value = "SELECT  * from detailproduit where nom like :input and idmarque = :idm", nativeQuery = true)
	public ArrayList<Detailproduit> listProduitRec(String input,int idm);

}
