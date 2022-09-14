package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Marque;

public interface MarqueRep extends JpaRepository<Marque, Integer> {
	

}
