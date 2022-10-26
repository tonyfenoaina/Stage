package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produitdegustation")
public class ProduitDegustation {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "idmarque")
	int idmarque;
	
	@Column(name = "idproduit")
	int idproduit;
	
	@Column(name = "iddegustation")
	int iddegustation;

    @Column(name = "numeroproduit")
	int numeroproduit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdmarque() {
        return idmarque;
    }

    public void setIdmarque(int idmarque) {
        this.idmarque = idmarque;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getIddegustation() {
        return iddegustation;
    }

    public void setIddegustation(int iddegustation) {
        this.iddegustation = iddegustation;
    }

    public int getNumeroproduit() {
        return numeroproduit;
    }

    public void setNumeroproduit(int numeroproduit) {
        this.numeroproduit = numeroproduit;
    }

    public ProduitDegustation() {
    }

    public ProduitDegustation(int idmarque, int idproduit, int iddegustation, int numeroproduit) {
        this.idmarque = idmarque;
        this.idproduit = idproduit;
        this.iddegustation = iddegustation;
        this.numeroproduit = numeroproduit;
    }

    public ProduitDegustation(int idmarque, int iddegustation, int numeroproduit) {
        this.idmarque = idmarque;
        this.iddegustation = iddegustation;
        this.numeroproduit = numeroproduit;
    }

    

}
