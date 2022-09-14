package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "marque")
public class Marque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nom")
	String nom;
	
	@Column(name = "etat")
	String etat;
	
	@Column(name = "idcategorie")
	int idcategorie;
	
	@Column(name = "idsociete")
	int idsociete;
	
	

	public int getIdsociete() {
		return idsociete;
	}

	public void setIdsociete(int idsociete) {
		this.idsociete = idsociete;
	}

	public Marque(String nom, String etat, int idcategorie, int idsociete) {
		super();
		this.nom = nom;
		this.etat = etat;
		this.idcategorie = idcategorie;
		this.idsociete = idsociete;
	}

	public Marque() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Marque(String nom, String etat, int idcategorie) {
		super();
		this.nom = nom;
		this.etat = etat;
		this.idcategorie = idcategorie;
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

	public int getIdcategorie() {
		return idcategorie;
	}

	public void setIdcategorie(int idcategorie) {
		this.idcategorie = idcategorie;
	}
	
	
}
