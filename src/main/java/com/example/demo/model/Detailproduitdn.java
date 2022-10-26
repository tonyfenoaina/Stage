package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detailproduitdn")
public class Detailproduitdn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "marque")
	String marque;
	
	@Column(name = "produit")
	String produit;
	
	@Column(name = "iddn")
	int iddn;

	@Column(name = "idproduit")
	int idproduit;

	@Column(name = "idmarque")
	int idmarque;
	
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

	public int getIddn() {
		return iddn;
	}

	public void setIddn(int iddn) {
		this.iddn = iddn;
	}

	public Detailproduitdn() {
		super();
		// TODO Auto-generated constructor stub
	}

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


	
	
	
}
