package com.example.demo.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "quartier")

public class Quartier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nom")
	String nom;
	
	@Column(name = "etat")
	String etat;
	
	@Column(name = "idville")
	int idville;
	
	public int getIdville() {
		return idville;
	}

	public Quartier(String nom, String etat, int idville) {
		super();
		this.nom = nom;
		this.etat = etat;
		this.idville = idville;
	}

	public void setIdville(int idville) {
		this.idville = idville;
	}

	

	public Quartier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quartier(String nom) {
		super();
		this.nom = nom;
	}

	public Quartier(String nom, String etat) {
		super();
		this.nom = nom;
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

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	

}
