package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produit")
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nom")
	String nom;
	
	@Column(name = "etat")
	String etat;
	
	@Column(name = "description")
	String description;

	@Column(name = "idmarque")
	int idmarque;

	public Produit(String nom, String etat, String description, int idmarque) {
		super();
		this.nom = nom;
		this.etat = etat;
		this.description = description;
		this.idmarque = idmarque;
	}

	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdmarque() {
		return idmarque;
	}

	public void setIdmarque(int idmarque) {
		this.idmarque = idmarque;
	}
	
	
	
}
