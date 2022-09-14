package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detaildn")
public class Detaildn {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "titre")
	String titre;
	
	@Column(name = "unite")
	String unite;
	
	@Column(name = "categorie")
	String categorie;
	
	
	@Column(name = "debut")
	LocalDate debut;
	
	@Column(name = "fin")
	LocalDate fin;
	
	@Column(name = "idcategorie")
	int idcategorie;
	
	@Column(name = "idunite")
	int idunite;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public int getIdcategorie() {
		return idcategorie;
	}

	public void setIdcategorie(int idcategorie) {
		this.idcategorie = idcategorie;
	}

	public int getIdunite() {
		return idunite;
	}

	public void setIdunite(int idunite) {
		this.idunite = idunite;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public Detaildn() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
