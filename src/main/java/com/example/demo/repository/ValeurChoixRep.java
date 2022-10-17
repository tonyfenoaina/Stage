package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.ValeurChoix;

public interface ValeurChoixRep extends JpaRepository<ValeurChoix , Integer>{
	
	@Query(value = "SELECT  * from valeurChoix", nativeQuery = true)
	public Page<ValeurChoix> listValeurChoix(Pageable paging);
	
	@Query(value = "SELECT  * from valeurChoix where nom like :input", nativeQuery = true)
	public ArrayList<ValeurChoix> listValeurChoixRec(String input);

}
