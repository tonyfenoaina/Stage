package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Detailresultatdn;


public interface Detail_resultat_dnRep extends JpaRepository<Detailresultatdn, String> {
    @Query(value = "Call getnbpdevente(:idm,:m)", nativeQuery = true)
	public ArrayList<Integer> getnbpdevente(int idm,String m);

    @Query(value = "Call getnbpdeventeparCategorie(:idm,:m)", nativeQuery = true)
	public ArrayList<Integer> getnbpdeventeparCategorie(int idm,String m);
      
}
