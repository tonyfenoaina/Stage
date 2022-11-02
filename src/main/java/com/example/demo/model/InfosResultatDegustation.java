package com.example.demo.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "infosresultatdegustation")
public class InfosResultatDegustation {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "idproduitdegustation")
    private int idproduitdegustation;

    @Column(name = "idchoixcible")
    private int idchoixcible;

    @Column(name = "idquestion")
    private int idquestion;

    @Column(name = "idresultatdegustation")
    private int idresultatdegustation;

    @Column(name = "remarque")
    private String remarque;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdproduitdegustation() {
        return idproduitdegustation;
    }

    public void setIdproduitdegustation(int idproduitdegustation) {
        this.idproduitdegustation = idproduitdegustation;
    }

    public int getIdchoixcible() {
        return idchoixcible;
    }

    public void setIdchoixcible(int idchoixcible) {
        this.idchoixcible = idchoixcible;
    }

    public int getIdquestion() {
        return idquestion;
    }

    public void setIdquestion(int idquestion) {
        this.idquestion = idquestion;
    }

    public int getIdresultatdegustation() {
        return idresultatdegustation;
    }

    public void setIdresultatdegustation(int idresultatdegustation) {
        this.idresultatdegustation = idresultatdegustation;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public InfosResultatDegustation() {
    }

    public InfosResultatDegustation(int id, int idproduitdegustation, int idchoixcible, int idquestion,
            int idresultatdegustation, String remarque) {
        this.id = id;
        this.idproduitdegustation = idproduitdegustation;
        this.idchoixcible = idchoixcible;
        this.idquestion = idquestion;
        this.idresultatdegustation = idresultatdegustation;
        this.remarque = remarque;
    }
}
