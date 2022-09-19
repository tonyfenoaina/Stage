package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.Detailproduitdn;

public interface DetailproduitdnRep extends JpaRepository<Detailproduitdn, Integer> {
	@Query(value = "SELECT  * from detailproduitdn where iddn = :idm", nativeQuery = true)
	public Page<Detailproduitdn> listDetailproduitdn(Pageable paging,int idm);
	
	@Query(value = "SELECT  * from detailproduitdn where (marque like :input or produit like :input)  and iddn = :idm", nativeQuery = true)
	public ArrayList<Detailproduitdn> listDetailproduitdnRec(String input,int idm);
}
