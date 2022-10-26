package com.example.demo.model;
import java.time.LocalDate;

import javax.persistence.*;
@Entity
@Table(name = "resultatdn")
public class ResultatDn  {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "idquartier")
    private int idquartier;

    @Column(name = "idenqueteur")
    private int idenqueteur;

    @Column(name = "iddn")
    private int iddn;

    @Column(name = "dateresultat")
    private LocalDate dateresultat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdquartier() {
        return idquartier;
    }

    public void setIdquartier(int idquartier) {
        this.idquartier = idquartier;
    }

    public int getIdenqueteur() {
        return idenqueteur;
    }

    public void setIdenqueteur(int idenqueteur) {
        this.idenqueteur = idenqueteur;
    }

    public int getIddn() {
        return iddn;
    }

    public void setIddn(int iddn) {
        this.iddn = iddn;
    }

    public ResultatDn() {
    }

    public ResultatDn(String id, int idquartier, int idenqueteur, int iddn) {
        this.id = id;
        this.idquartier = idquartier;
        this.idenqueteur = idenqueteur;
        this.iddn = iddn;
    }






    
}