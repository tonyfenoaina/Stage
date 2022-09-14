package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detailutilisateur")
public class DetailUtilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nom")
	String nom;
	
	@Column(name = "prenom")
	String prenom;
	
	@Column(name = "fonction")
	String fonction;
	
	@Column(name = "idfonction")
	int idfonction;
	
	@Column(name = "matricule")
	String matricule;
	
	@Column(name = "motdepasse")
	String motedepasse;
	
	@Column(name = "role")
	String role;
	
	@Column(name = "etat")
	String etat;

	
	public int getIdfonction() {
		return idfonction;
	}

	public void setIdfonction(int idfonction) {
		this.idfonction = idfonction;
	}

	public DetailUtilisateur() {
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	
	
	
}
