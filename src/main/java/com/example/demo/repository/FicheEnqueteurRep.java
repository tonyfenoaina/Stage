package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Ficheenqueteur;

public interface FicheEnqueteurRep  extends JpaRepository<Ficheenqueteur , Integer> {	
	@Query(value = "SELECT  * from ficheenqueteur where idenqueteur = :idenqu and dateresultat = :dateres", nativeQuery = true)
	public ArrayList<Ficheenqueteur> listEmplacement(int idenqu,String dateres);
}
