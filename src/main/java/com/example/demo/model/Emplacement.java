package com.example.demo.model;
import javax.persistence.*;

@Entity
@Table(name = "emplacement")
public class Emplacement  {
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "idresultatdn")
    private String idresultatdn;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdresultatdn() {
        return idresultatdn;
    }

    public void setIdresultatdn(String idresultatdn) {
        this.idresultatdn = idresultatdn;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Emplacement(String id, String idresultatdn, double longitude, double latitude) {
        this.id = id;
        this.idresultatdn = idresultatdn;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Emplacement() {
    }


}