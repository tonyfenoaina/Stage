package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.ResultatDn;

public interface ResultatDnRep  extends JpaRepository<ResultatDn, String>{

        @Query(value = "select count(*) nombre from detailresultatdn where iddn = :iddn group by idproduitdn", nativeQuery = true)
        public ArrayList<Integer> resultatdntotal(int iddn);

        @Query(value = "select count(*) nombre from detailresultatdn where iddn = :iddn and existence = true group by idproduitdn", nativeQuery = true)
        public ArrayList<Integer> resultatdnstat(int iddn);

        @Query(value = "select produit nombre from detailresultatdn where iddn = :iddn group by idproduitdn", nativeQuery = true)
        public ArrayList<String> resultatnom(int iddn);

        @Query(value = "select floor(avg(prix)) from detailresultatdn where iddn = :iddn group by idproduitdn", nativeQuery = true)
        public ArrayList<Integer> resultatprix(int iddn);

        @Query(value = "select floor(avg(rotation)) from detailresultatdn where iddn = :iddn group by idproduitdn", nativeQuery = true)
        public ArrayList<Integer> resultatrotation(int iddn);

}
