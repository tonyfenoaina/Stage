package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Detailproduitdegustation;

public interface DetailproduitdegustationRep extends JpaRepository<Detailproduitdegustation, Integer> {
	@Query(value = "SELECT  * from detailproduitdegustation where iddegustation = :idm", nativeQuery = true)
	public Page<Detailproduitdegustation> listDetailproduitDegustation(Pageable paging,int idm);
	
	@Query(value = "SELECT  * from detailproduitdegustation where (marque like :input or produit like :input)  and iddn = :idm", nativeQuery = true)
	public ArrayList<Detailproduitdegustation> listDetailproduitDegustationRec(String input,int idm);
}
