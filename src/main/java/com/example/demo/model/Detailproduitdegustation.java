package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detailproduitdegustation")
public class Detailproduitdegustation {
    @Id
	private Integer id;
	
	@Column(name = "marque")
	String marque;
	
	@Column(name = "produit")
	String produit;
	
	@Column(name = "iddegustation")
	int iddegustation;

	@Column(name = "idproduit")
	int idproduit;

	@Column(name = "idmarque")
	int idmarque;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public int getIddegustation() {
        return iddegustation;
    }

    public void setIddegustation(int iddegustation) {
        this.iddegustation = iddegustation;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getIdmarque() {
        return idmarque;
    }

    public void setIdmarque(int idmarque) {
        this.idmarque = idmarque;
    }

    public Detailproduitdegustation() {
    }

    public Detailproduitdegustation(String marque, String produit, int iddegustation, int idproduit, int idmarque) {
        this.marque = marque;
        this.produit = produit;
        this.iddegustation = iddegustation;
        this.idproduit = idproduit;
        this.idmarque = idmarque;
    }
}
