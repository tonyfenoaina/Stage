package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "listquestion")
public class Listequestion {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "iddegustation")
    private int iddegustation;

    @Column(name = "numeroproduit")
    private int numeroproduit;
   
    @Column(name = "question")
    private String question;

    @Column(name = "idcible")
    private int idcible;

    @Column(name = "nom")
    private String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIddegustation() {
        return iddegustation;
    }

    public void setIddegustation(int iddegustation) {
        this.iddegustation = iddegustation;
    }

    public int getNumeroproduit() {
        return numeroproduit;
    }

    public void setNumeroproduit(int numeroproduit) {
        this.numeroproduit = numeroproduit;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getIdcible() {
        return idcible;
    }

    public void setIdcible(int idcible) {
        this.idcible = idcible;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
