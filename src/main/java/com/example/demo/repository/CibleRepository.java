package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Cible;


public interface CibleRepository extends JpaRepository<Cible , Integer> {
	@Query(value = "SELECT  * from cible", nativeQuery = true)
	public Page<Cible> listCible(Pageable paging);
	
	@Query(value = "SELECT  * from cible where nom like :input", nativeQuery = true)
	public ArrayList<Cible> listCibleRec(String input);

}
