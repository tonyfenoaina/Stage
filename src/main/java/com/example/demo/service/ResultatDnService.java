package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ResultatDnRep;

@Service
public class ResultatDnService {

    @Autowired
    ResultatDnRep resultatDnRep;

    public ArrayList<Integer> getStatistiqueresultatdn(int iddn){
        ArrayList<Integer> list  = new ArrayList<Integer>();
        
        ArrayList<Integer> listtotal = new ArrayList<Integer>();
        ArrayList<Integer> listnpombre =new ArrayList<Integer>();
        
        listtotal = resultatDnRep.resultatdntotal(iddn);
        listnpombre = resultatDnRep.resultatdnstat(iddn);      
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
}
