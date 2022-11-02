package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.ResultatTotal;

public interface ConcurrenceRep extends JpaRepository<ResultatTotal, String> {
    @Query(value = "Call getTotalStatConcurrenceParCategorie(:idm,:m) ", nativeQuery = true)
    public ArrayList<Integer> getTotalStatConcurrenceParCategorie(int idm,String m);

    @Query(value = "Call getnomStatConcurrenceParCategorie(:idm,:m) ", nativeQuery = true)
    public ArrayList<String> getnomStatConcurrenceParCategorie(int idm,String m);

    @Query(value = "Call getStatConcurrenceCategorie(:idm,:m) ", nativeQuery = true)
    public ArrayList<Integer> getStatConcurrenceCategorie(int idm,String m);

    @Query(value = "Call getPrixConcurrenceCategorie(:idm,:m) ", nativeQuery = true)
    public ArrayList<Integer> getPrixConcurrenceCategorie(int idm,String m);

    @Query(value = "Call getRotationConcurrenceCategorie(:idm,:m) ", nativeQuery = true)
    public ArrayList<Integer> getRotationConcurrenceCategorie(int idm,String m);

}
