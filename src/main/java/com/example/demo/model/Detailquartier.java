package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;
@Entity
@Immutable
@Table(name = "detailquartier")

public class Detailquartier {

	@Id
	private int id;
	
	@Column(name = "nom")
	String nom;
	
	@Column(name = "etat")
	String etat;
	
	@Column(name = "idville")
	int idville;
	
	@Column(name = "villenom")
	String villenom;

	public Detailquartier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getIdville() {
		return idville;
	}

	public void setIdville(int idville) {
		this.idville = idville;
	}

	public String getNomville() {
		return villenom;
	}

	public void setNomville(String nomville) {
		this.villenom = nomville;
	}
	
	
	
}

