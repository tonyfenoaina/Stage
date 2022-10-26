package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detailquestion")
public class DetailQuestion {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(name = "question")
	String question;

    @Column(name = "idcible")
	int idcible;

    @Column(name = "nomcible")
	String nomCible;

    @Column(name = "iddegustation")
	int iddegustation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getIddegustation() {
        return iddegustation;
    }

    public void setIddegustation(int iddegustation) {
        this.iddegustation = iddegustation;
    }

    public DetailQuestion() {
    }

    
}
