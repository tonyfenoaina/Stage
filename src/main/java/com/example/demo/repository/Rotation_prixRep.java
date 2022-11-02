package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Rotation_prix;

public interface Rotation_prixRep extends JpaRepository<Rotation_prix, Integer> {

    @Query(value = "select * from Rotation_prix where idproduit = :idm order by mois desc limit 1", nativeQuery = true)
    public Rotation_prix getMoyenne(int idm);
    
    @Query(value = "select mois,idproduit,min(prix) as prix ,min(rotation) rotation from detailresultatdn where idproduit = :idm group by mois order by mois desc limit 1", nativeQuery = true)
    public Rotation_prix getMin(int idm);
    
    @Query(value = "select mois,idproduit,max(prix) as prix ,max(rotation) rotation from detailresultatdn where idproduit = :idm group by mois order by mois desc limit 1", nativeQuery = true)
    public Rotation_prix getMax(int idm);

    @Query(value = "select current_dn(:idm)", nativeQuery = true)
    public Integer getcurrent_dn(int idm);

    


}
