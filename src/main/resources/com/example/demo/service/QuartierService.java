package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Quartier;
import com.example.demo.repository.QuartierRep;

@Service
public class QuartierService {
	@Autowired
	QuartierRep quartierRep;
	
//	public ArrayList<Quartier> listQuartier() {
//		
//		 List<Quartier> listquartier =  (List<Quartier>) quartierRep.
//		 
//		 System.out.println("reto leizy"+listquartier.size());	 
//		 ArrayList<Quartier> listproduits = new ArrayList<Quartier>();
//		 
//		 for (int i = 0; i<listproduit.size();i++) {
//			 
//				//if (listproduit.get(i).getIdsortie() == 0) {
//					 listproduits.add(listproduit.get(i));
//					 
//			//}
//			 				 
//		 }
//		return listproduits;
//		}

}
