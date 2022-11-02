package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appreciation")
public class Appreciation {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "idresultatdegustation")
    private int idresultatdegustation;

    @Column(name = "valeur")
    private String valeur;

    @Column(name = "idproduitdegustation")
    private int idproduitdegustation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdresultatdegustation() {
        return idresultatdegustation;
    }

    public void setIdresultatdegustation(int idresultatdegustation) {
        this.idresultatdegustation = idresultatdegustation;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public int getIdproduitdegustation() {
        return idproduitdegustation;
    }

    public void setIdproduitdegustation(int idproduitdegustation) {
        this.idproduitdegustation = idproduitdegustation;
    }

    public Appreciation(int id, int idresultatdegustation, String valeur, int idproduitdegustation) {
        this.id = id;
        this.idresultatdegustation = idresultatdegustation;
        this.valeur = valeur;
        this.idproduitdegustation = idproduitdegustation;
    }

    public Appreciation() {
    }

    


}
