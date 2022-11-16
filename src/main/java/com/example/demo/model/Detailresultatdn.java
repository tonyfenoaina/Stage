package com.example.demo.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detailresultatdn")
public class Detailresultatdn {
    @Id
	private Integer id;

	@Column(name = "mois")
	int mois;

    @Column(name = "iddn")
	int iddn;

    public int getIddn() {
		return iddn;
	}

	public void setIddn(int iddn) {
		this.iddn = iddn;
	}

	@Column(name = "annee")
	int annee;

	@Column(name = "idmarque")
	int idmarque;

	@Column(name = "idproduit")
	int idproduit;

	@Column(name = "idcategorie")
	int idcategorie;

	@Column(name = "prix")
	double prix;

	@Column(name = "rotation")
	double rotation;

	@Column(name = "existence")
	boolean existence;

	@Column(name = "marque")
	String marque;

	@Column(name = "produit")
	String produit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
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

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public boolean isExistence() {
		return existence;
	}

	public void setExistence(boolean existence) {
		this.existence = existence;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	

	public Detailresultatdn() {
	}

	public int getIdcategorie() {
		return idcategorie;
	}

	public void setIdcategorie(int idcategorie) {
		this.idcategorie = idcategorie;
	}
}
