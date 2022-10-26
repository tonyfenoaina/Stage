package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.*;
import com.example.demo.repository.CategorieRep;
import com.example.demo.repository.Detail_resultat_dnRep;
import com.example.demo.repository.DetailmarqueRep;

import com.example.demo.service.DetailresultatService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
@Controller
public class EvolutionProduitController {
    @Autowired
    Detail_resultat_dnRep detail_resultat_dnRep;

    @Autowired
    DetailresultatService detailresultatService;

    @Autowired
    DetailmarqueRep marquerep;

    @Autowired
    CategorieRep categorieRep;

@GetMapping(path = "/categorieEvolution")
public ModelAndView Categorie(HttpSession session,
		  Model m,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "6") int size) {
	 Pageable paging = PageRequest.of(page, size);
	 
	 Page<Categorie> pageTuts=  categorieRep.listCategorie(paging);
	 int total = pageTuts.getTotalPages();
	 List<Categorie> list = new ArrayList<Categorie>();
	 list =  pageTuts.getContent();
	m.addAttribute("total",total);  
	m.addAttribute("current",page+1); 
	session.setAttribute("listCategorie", list);
	
		return new ModelAndView("Evolution/categorie");
		
	}
@PostMapping(path = "/RechercheEvolution")
public ModelAndView RechercheCategorie(Model m ,HttpSession session,
		  @RequestParam(name = "nom",required = false) String nom,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "6") int size) {	
		
	 Pageable paging = PageRequest.of(page, size);		 
	m.addAttribute("total",1);  
	m.addAttribute("current",page+1); 
		String noms = nom;
		nom = "%"+nom+"%";
		System.out.print("tyyy"+nom);
		System.out.print("tyyy"+categorieRep.listCategorieRec(nom));
		session.setAttribute("listCategorie", categorieRep.listCategorieRec(nom));
		m.addAttribute("retour", noms);
		return new ModelAndView("Evolution/categorie");			
	}   
   @GetMapping(value="/marque_list")
    public ModelAndView marque_list(Model m,HttpSession session,
    @RequestParam(name="page" ,defaultValue = "0") int page,
    @RequestParam(name="size", defaultValue = "6") int size) {
    Pageable paging = PageRequest.of(page, size); 
        Page<Detailmarque> pageTuts=  marquerep.findAll(paging);
        int total = pageTuts.getTotalPages();   
        m.addAttribute("total",total);  
		 m.addAttribute("current",page+1);
         List<Detailmarque> list = new ArrayList<Detailmarque>();  
         list =  pageTuts.getContent();
         session.setAttribute("listMarque", list);        
        return new ModelAndView("Evolution/marque");
    }
    @PostMapping(path = "/RechercheMarqueEvolution")
	  public ModelAndView RechercheMarque(Model m ,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {	
		 Pageable paging = PageRequest.of(page, size);		 
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listMarque", marquerep.rechercheEvolution(nom));
			m.addAttribute("retour", noms);
			return new ModelAndView("Evolution/marque");			
		}

        @GetMapping(path = "/Evolution_produit")
        public ModelAndView Evolution_produit(Model m ,HttpSession session,
                @RequestParam(name="idmarque") int idmarque) {
                    m.addAttribute("nomProduit", detailresultatService.getNomparmoisparmarque(idmarque,"2022-1"));  
                    session.setAttribute("idevolutionmarque", idmarque);	
              return new ModelAndView("Evolution/evolutionproduit");			
          }

          @GetMapping(path = "/Evolution_marque")
        public ModelAndView Evolution_produitparCategorie(Model m ,HttpSession session,
                @RequestParam(name="idcategorie") int idcategorie) {
                    m.addAttribute("nomProduit", detailresultatService.getNomparmoisparcategorie(idcategorie,"2022-1"));  
                    session.setAttribute("idevolutioncategorie", idcategorie);	
              return new ModelAndView("Evolution/evolutionmarque");			
          }

      @GetMapping(path = "/Evolution_produitparmois")
          public ModelAndView Evolution_produitparmois(Model m ,HttpSession session,
                  @RequestParam(name="mois1") String mois1,
                  @RequestParam(name="mois2") String mois2) {
                    int idmarque = (int)session.getAttribute("idevolutionmarque");
                    //JsonParser mJSONArray = new JSONParser(detailresultatService.getNomparmoisparmarque(idmarque,mois1).toString()); 
                   
                    m.addAttribute("mois1", mois1);
                        m.addAttribute("mois2", mois2);

                    System.out.println("mois1"+mois1);
                   
                    m.addAttribute("nomProduit", detailresultatService.getNomparmoisparmarque(idmarque,mois1));
                    System.out.println("tyyyyyyy"+detailresultatService.getNomparmoisparmarque(idmarque,mois1));
                    System.out.println("aiza");
                    
                    m.addAttribute("data1", detailresultatService.getStatistiqueparmoisparmarque(idmarque,mois1));
                    m.addAttribute("data2", detailresultatService.getStatistiqueparmoisparmarque(idmarque,mois2));
                                        
                return new ModelAndView("Evolution/evolutionproduit");			
            }

            @GetMapping(path = "/Evolution_marqueparmois")
          public ModelAndView Evolution_marqueparmois(Model m ,HttpSession session,
                  @RequestParam(name="mois1") String mois1,
                  @RequestParam(name="mois2") String mois2) {
                    int idmarque = (int)session.getAttribute("idevolutioncategorie");
                    //JsonParser mJSONArray = new JSONParser(detailresultatService.getNomparmoisparmarque(idmarque,mois1).toString()); 
                   
                    m.addAttribute("mois1", mois1);
                    m.addAttribute("mois2", mois2);

                    System.out.println("mois1"+mois1);
                   
                    m.addAttribute("nomProduit", detailresultatService.getNomparmoisparcategorie(idmarque,mois1));
                    System.out.println("tyyyyyyy"+detailresultatService.getNomparmoisparcategorie(idmarque,mois1));
                    System.out.println("aiza");
                    m.addAttribute("data2", detailresultatService.getStatistiqueparmoisparcategorie(idmarque,mois2)); 
                    m.addAttribute("data1", detailresultatService.getStatistiqueparmoisparcategorie(idmarque,mois1));
                   
                                        
                return new ModelAndView("Evolution/evolutionmarque");			
            }
}
