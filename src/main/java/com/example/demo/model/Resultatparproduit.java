package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resultatparproduit")
public class Resultatparproduit {
   
    @Column(name = "id",nullable = true)
    String idproduit;   

    public String getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(String idproduit) {
        this.idproduit = idproduit;
    }
    @Id
    @Column(name = "nom")
    String nom;

    @Column(name = "nbpointdevente")
	int nombre;



    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Resultatparproduit() {
    }

    

}
