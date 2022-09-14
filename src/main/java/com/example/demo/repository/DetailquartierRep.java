package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Detailquartier;
import com.example.demo.model.Quartier;

public interface DetailquartierRep extends JpaRepository<Detailquartier, Integer> {
	@Query(value = "SELECT  * from detailquartier", nativeQuery = true)
	public Page<Detailquartier> listQuartier(Pageable paging);
	
	@Query(value = "SELECT  * from detailquartier where nom like :input", nativeQuery = true)
	public ArrayList<Detailquartier> listQuartierRec(String input);
	
}
