package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Emplacement;

public interface EmplacementRep  extends JpaRepository<Emplacement, String> {
    
}
