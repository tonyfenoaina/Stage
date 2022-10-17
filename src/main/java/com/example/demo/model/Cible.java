package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cible")
public class Cible {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nom")
	String nom;

	@Column(name = "etat")
	String etat;
	
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

	
	
	public Cible(String nom, String etat) {
		super();
		this.nom = nom;
		this.etat = etat;
	}

	public Cible() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cible(String nom) {
		super();
		this.nom = nom;
	}
	
	
}
