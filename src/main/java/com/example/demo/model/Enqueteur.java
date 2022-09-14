package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enqueteur")
public class Enqueteur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nom")
	String nom;
	
	@Column(name = "prenom")
	String prenom;
	
	@Column(name = "matricule")
	String matricule;
	
	@Column(name = "motdepasse")
	String motedepasse;
	
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getMotedepasse() {
		return motedepasse;
	}

	public void setMotedepasse(String motedepasse) {
		this.motedepasse = motedepasse;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Enqueteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enqueteur(String nom, String prenom,  String matricule, String motedepasse, String etat) {
		super();
		this.nom = nom;
		this.prenom = prenom;	
		this.matricule = matricule;
		this.motedepasse = motedepasse;
		this.etat = etat;
	}
	
	

}
