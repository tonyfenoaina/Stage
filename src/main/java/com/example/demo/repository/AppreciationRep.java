package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Appreciation;

 public interface AppreciationRep  extends JpaRepository<Appreciation, String>{
    @Query(value = "select count(*) nombre from detailappreciation where idproduit = :idproduit group by valeur", nativeQuery = true)
	public ArrayList<Integer> StatAppreciationparProduit(int idproduit);
}
