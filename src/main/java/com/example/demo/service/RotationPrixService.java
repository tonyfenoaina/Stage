package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Rotation_prix;
import com.example.demo.repository.Rotation_prixRep;

@Service
public class RotationPrixService {
    @Autowired
    Rotation_prixRep rotation_prixRep;

    public void test(){
       Rotation_prix rotation_prix = new Rotation_prix();
       rotation_prix = rotation_prixRep.getMoyenne(2);
        System.out.println(rotation_prix.getPrix());
       
    }
}
