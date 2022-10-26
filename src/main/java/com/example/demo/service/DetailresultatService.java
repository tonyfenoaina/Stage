package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ResultatTotal;
import com.example.demo.model.Resultatparproduit;
import com.example.demo.repository.Detail_resultat_dnRep;
import com.example.demo.repository.ResultatTotalRep;

@Service
public class DetailresultatService {
    
    
     @Autowired
     Detail_resultat_dnRep detail_resultat_dnRep;
     
     @Autowired
     ResultatTotalRep resultatTotalRep;
     
  //Par Marques -> liste des produits   
    public ArrayList<Integer> getStatistiqueparmoisparmarque(int idmarque,String mois){
        ArrayList<Integer> list  = new ArrayList<Integer>();
        
        ArrayList<Integer> listtotal = new ArrayList<Integer>();
        ArrayList<Integer> listnpombre =new ArrayList<Integer>();
        
        listtotal = resultatTotalRep.getTotal(idmarque, mois);
        listnpombre = detail_resultat_dnRep.getnbpdevente(idmarque, mois);      
        for (int i = 0; i < listtotal.size(); i++) {
            Integer result = 0;
            if(listtotal.get(i)!=0){
                 result = (listnpombre.get(i) * 100)/listtotal.get(i);     
            }
            list.add(result);          
            System.out.println("total"+listtotal.get(i));
            System.out.println("nbppointdevente"+listnpombre.get(i));
            
        }
  
        return list;     
    }
    public ArrayList<String> getNomparmoisparmarque(int idmarque,String mois){
        ArrayList<String> list  = new ArrayList<String>();  
        ArrayList<String> listtotal = new ArrayList<String>(); 
        listtotal = resultatTotalRep.getnomparmarque(idmarque, mois);
        return listtotal;    
    }

    //Par Marques -> liste des marques 
    public ArrayList<Integer> getStatistiqueparmoisparcategorie(int idcategorie,String mois){
        ArrayList<Integer> list  = new ArrayList<Integer>();
        
        ArrayList<Integer> listtotal = new ArrayList<Integer>();
        ArrayList<Integer> listnpombre =new ArrayList<Integer>();
        
        listtotal = resultatTotalRep.getTotalparCategorie(idcategorie, mois);
        listnpombre = detail_resultat_dnRep.getnbpdeventeparCategorie(idcategorie, mois);      
        for (int i = 0; i < listtotal.size(); i++) {
            Integer result = 0;
            if(listtotal.get(i)!=0){
                 result = (listnpombre.get(i) * 100)/listtotal.get(i);     
            }
            list.add(result);          
            System.out.println("total"+listtotal.get(i));
            System.out.println("nbppointdevente"+listnpombre.get(i)); 
        }  
        return list;     
    }

    public ArrayList<String> getNomparmoisparcategorie(int idcategorie,String mois){
        ArrayList<String> list  = new ArrayList<String>();  
        ArrayList<String> listtotal = new ArrayList<String>(); 
        listtotal = resultatTotalRep.getNomparCategorie(idcategorie, mois);     
        return listtotal;    
    }

}
