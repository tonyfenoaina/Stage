package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Produit;

public interface ProduitRep extends JpaRepository<Produit, Integer> {

}
