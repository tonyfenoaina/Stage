package com.example.demo.repository;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Listequestion;

public interface ListequestionRep extends JpaRepository<Listequestion, Integer>  {
    @Query(value = "Call getlistquestion()", nativeQuery = true)
    public ArrayList<Listequestion> listquestion();
}
