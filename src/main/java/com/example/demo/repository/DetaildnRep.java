package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.Detaildn;

public interface DetaildnRep extends JpaRepository<Detaildn, Integer> {
	@Query(value = "SELECT  * from detaildn", nativeQuery = true)
	public Page<Detaildn> listDetaildn(Pageable paging);
	
	@Query(value = "SELECT  * from detaildn where titre like :input", nativeQuery = true)
	public ArrayList<Detaildn> listDetaildnRec(String input);

}
