package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.DetailQuestion;

public interface DetailQuestionRep extends JpaRepository<DetailQuestion , Integer>{
    @Query(value = "SELECT  * from detailquestion where iddegustation = :input ", nativeQuery = true)
	public Page<DetailQuestion> listQuestionparIddegustation(org.springframework.data.domain.Pageable paging1, int input);
	
	@Query(value = "SELECT  * from cible where nom like :nom and iddegustation = :input", nativeQuery = true)
	public ArrayList<DetailQuestion> listQuestionparIddegustationRec(int input,String nom);
}
