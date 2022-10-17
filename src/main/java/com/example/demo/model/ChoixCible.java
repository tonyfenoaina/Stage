package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "choixcible")
public class ChoixCible {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nom")
	String nom;
	
	@Column(name = "idcible")
	String idcible;

	@Column(name = "etat")
	String etat;
	

	public ChoixCible() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChoixCible(String nom, String idcible) {
		super();
		this.nom = nom;
		this.idcible = idcible;
	}

	
	public ChoixCible(String nom, String idcible, String etat) {
		super();
		this.nom = nom;
		this.idcible = idcible;
		this.etat = etat;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
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

	public String getIdcible() {
		return idcible;
	}

	public void setIdcible(String idcible) {
		this.idcible = idcible;
	}
	
	
	
	
	
	
	
	
	

}
