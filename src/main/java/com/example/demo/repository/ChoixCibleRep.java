package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.ChoixCible;
import com.example.demo.model.ValeurChoix;

public interface ChoixCibleRep extends JpaRepository<ChoixCible , Integer>{
	

	@Query(value = "SELECT  * from choixcible", nativeQuery = true)
	public Page<ChoixCible> listChoixCible(Pageable paging);
	
	@Query(value = "SELECT  * from choixcible where nom like :input", nativeQuery = true)
	public ArrayList<ChoixCible> listChoixCibleRec(String input);

}
