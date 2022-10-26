package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Resultat_par_produit;
import com.example.demo.repository.Detail_resultat_dnRep;
import com.example.demo.repository.Resultattotal;
import com.example.demo.repository.ResultattotalRep;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailresultatService {
    @Autowired
    Detail_resultat_dnRep detail_resultat_dnRep;

    @Autowired
    ResultattotalRep resultattotalrep;

    public ArrayList getStatistiqueparmoisparmarque(int idmarque,String mois) {
        ArrayList listresultat = new ArrayList<>(); 
       

        List<Resultat_par_produit> listptvente = new ArrayList<Resultat_par_produit>();
        listptvente = detail_resultat_dnRep.getnbpdevente(idmarque,mois);

         List<Resultattotal> listtotal = new ArrayList<Resultattotal>();
        listtotal = resultattotalrep.getTotal(idmarque,mois);

        for (int i = 0; i < listptvente.size(); i++) {
           
            int Total = listtotal.get(i).getNombre();
            int nombre = listptvente.get(i).getNombre();
            double resultat = 0;
            if(Total != 0){
                System.out.println(Total);
                System.out.println(nombre);
                 resultat = (nombre*100)/Total;
            }
           
            listresultat.add(resultat);
        } 
        return listresultat;
    }

    
}
