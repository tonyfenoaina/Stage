package com.example.demo.model;
import javax.persistence.*;
@Entity
@Table(name = "emplacement")
public class Emplacement  {
    @Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "idresultatdn")
    private String idresultatdn;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdresultatdn() {
        return idresultatdn;
    }

    public void setIdresultatdn(String idresultatdn) {
        this.idresultatdn = idresultatdn;
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

    public Emplacement(int id, String idresultatdn, String longitude, String latitude) {
        this.id = id;
        this.idresultatdn = idresultatdn;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Emplacement() {
    }


}