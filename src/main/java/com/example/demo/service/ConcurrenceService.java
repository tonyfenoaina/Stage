package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ConcurrenceRep;

@Service
public class ConcurrenceService {
    @Autowired
    ConcurrenceRep concurrenceRep;

    public ArrayList<Integer> getStatistiqueparmoisparCategorie(int idmarque,String mois){
        ArrayList<Integer> list  = new ArrayList<Integer>();
        
        ArrayList<Integer> listtotal = new ArrayList<Integer>();
        ArrayList<Integer> listnpombre =new ArrayList<Integer>();
        
        listtotal = concurrenceRep.getTotalStatConcurrenceParCategorie(idmarque, mois);
        listnpombre = concurrenceRep.getStatConcurrenceCategorie(idmarque, mois);      
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
    public ArrayList<String> getNomparmoisparcategorie(int idmarque,String mois){
        ArrayList<String> list  = new ArrayList<String>();  
        ArrayList<String> listtotal = new ArrayList<String>(); 
        listtotal = concurrenceRep.getnomStatConcurrenceParCategorie(idmarque, mois);
        return listtotal;    
    }
    
}
