package com.example.demo.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resultatdegustation")
public class ResultatDegustation {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "iddegustation")
    private int iddegustation;

    @Column(name = "age")
    private int age;

    @Column(name = "sexe ")
    private String sexe ;

    @Column(name = "frequence")
    private int frequence;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getFrequence() {
        return frequence;
    }
    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    public ResultatDegustation() {
    }

    public ResultatDegustation(int id, int iddegustation, int age, String sexe, int frequence) {
        this.id = id;
        this.iddegustation = iddegustation;
        this.age = age;
        this.sexe = sexe;
        this.frequence = frequence;
    }

}
