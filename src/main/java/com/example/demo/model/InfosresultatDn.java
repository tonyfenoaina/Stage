package com.example.demo.model;
import javax.persistence.*;

@Entity
@Table(name = "infosresultatdn")
public class InfosresultatDn {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "idproduitdn")
    private int idproduitdn;

    @Column(name = "prix")
    private double prix;

    @Column(name = "rotation")
    private double rotation;

    @Column(name = "idresultatdn")
    private String idresultatdn;

    @Column(name = "existence")
    private Boolean existence;
    

    public InfosresultatDn(String id, int idproduitdn, double prix, double rotation, String idresultatdn,
            Boolean existence) {
        this.id = id;
        this.idproduitdn = idproduitdn;
        this.prix = prix;
        this.rotation = rotation;
        this.idresultatdn = idresultatdn;
        this.existence = existence;
    }

    public Boolean getExistence() {
        return existence;
    }

    public void setExistence(Boolean existence) {
        this.existence = existence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdproduitdn() {
        return idproduitdn;
    }

    public void setIdproduitdn(int idproduitdn) {
        this.idproduitdn = idproduitdn;
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

    public String getIdresultatdn() {
        return idresultatdn;
    }

    public void setIdresultatdn(String idresultatdn) {
        this.idresultatdn = idresultatdn;
    }

    public InfosresultatDn() {
    }

    public InfosresultatDn(String id, int idproduitdn, double prix, double rotation, String idresultatdn) {
        this.id = id;
        this.idproduitdn = idproduitdn;
        this.prix = prix;
        this.rotation = rotation;
        this.idresultatdn = idresultatdn;
    }

    


    
}