package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ficheenqueteur")
public class Ficheenqueteur {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "iddn")
    private int iddn;

    @Column(name = "idenqueteur")
    private int idenqueteur;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;
    
    @Column(name = "dateresultat")
    private String dateresultat;

    @Column(name = "quartier")
    private String quartier;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIddn() {
        return iddn;
    }

    public void setIddn(int iddn) {
        this.iddn = iddn;
    }

    public int getIdenqueteur() {
        return idenqueteur;
    }

    public void setIdenqueteur(int idenqueteur) {
        this.idenqueteur = idenqueteur;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDateresultat() {
        return dateresultat;
    }

    public void setDateresultat(String dateresultat) {
        this.dateresultat = dateresultat;
    }

    public Ficheenqueteur() {
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    
}
