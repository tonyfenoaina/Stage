package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.ResultatTotal;

public interface ResultatTotalRep extends JpaRepository<ResultatTotal, String> {
    @Query(value = "Call getTotalPointdeVente(:idm,:m) ", nativeQuery = true)
    public ArrayList<Integer> getTotal(int idm,String m);

    @Query(value = "Call getTotalPointdeVenteparCategorie(:idm,:m) ", nativeQuery = true)
    public ArrayList<Integer> getTotalparCategorie(int idm,String m);

    @Query(value = "Call getnomParCategorie(:idm,:m) ", nativeQuery = true)
    public ArrayList<String> getNomparCategorie(int idm,String m);

    @Query(value = "Call getnomparmarque(:idm,:m) ", nativeQuery = true)
    public ArrayList<String> getnomparmarque(int idm,String m);
    
}


