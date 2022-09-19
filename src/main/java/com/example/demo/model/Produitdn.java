package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produitdn")
public class Produitdn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "idmarque")
	int idmarque;
	
	@Column(name = "idproduit")
	int idproduit;
	
	@Column(name = "iddn")
	int iddn;

	public Produitdn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produitdn(int idmarque, int idproduit, int iddn) {
		super();
		this.idmarque = idmarque;
		this.idproduit = idproduit;
		this.iddn = iddn;
	}

	public Produitdn(int idmarque, int iddn) {
		super();
		this.idmarque = idmarque;
		this.iddn = iddn;
	}

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

	public int getIddn() {
		return iddn;
	}

	public void setIddn(int iddn) {
		this.iddn = iddn;
	}
	
	
	
	
	

}
