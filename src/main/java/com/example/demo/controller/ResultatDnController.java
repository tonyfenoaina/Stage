package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Detaildn;
import com.example.demo.model.Detailproduitdn;
import com.example.demo.repository.DetaildnRep;
import com.example.demo.repository.DetailproduitdnRep;
import com.example.demo.repository.ResultatDnRep;
import com.example.demo.service.ResultatDnService;


@Controller
public class ResultatDnController {
    @Autowired
    DetaildnRep detaildnRep;
    
	 @GetMapping(path = "/resultatdn")
     public ModelAndView Dn(HttpSession session,
             Model m,
             @RequestParam(name="page" ,defaultValue = "0") int page,
             @RequestParam(name="size", defaultValue = "6") int size){
        Pageable paging = PageRequest.of(page, size); 
        Page<Detaildn> pageTuts=  detaildnRep.listDetaildn(paging);
        int total = pageTuts.getTotalPages();
        List<Detaildn> listDn = new ArrayList<Detaildn>();
        listDn =  pageTuts.getContent();
       m.addAttribute("total",total);  
       m.addAttribute("current",page+1); 
       session.setAttribute("listDn", listDn);		
           return new ModelAndView("resultat/dn/resultatdn");   
       }

       @Autowired
       DetailproduitdnRep detailproduitdnRep;
       @GetMapping(path = "/resultatproduitDn")
       public ModelAndView produitDn(HttpSession session,
               Model m,
               @RequestParam(name="page" ,defaultValue = "0") int page,
               @RequestParam(name="size", defaultValue = "6") int size,
               @RequestParam(name="page1" ,defaultValue = "0") int page1,
               @RequestParam(name="size1", defaultValue = "6") int size1,
               @RequestParam(name="iddn", required= false) String idd,
               @RequestParam(name="idcategorie", required= false) int idcategorie
             ){

                if(idd.equals(null)==false){
                    session.setAttribute("iddn",idd);	
                }
         
          session.setAttribute("idcategorie", idcategorie);
          idcategorie = (int)session.getAttribute("idcategorie");
          int iddn = (int)session.getAttribute("iddn");
          
          //produit
          Pageable paging1 = PageRequest.of(page1, size1);
          Page<Detailproduitdn> listproduit = detailproduitdnRep.listDetailproduitdn(paging1, iddn);	
          int total1 = listproduit.getTotalPages();
          List<Detailproduitdn> list1 = new ArrayList<Detailproduitdn>();
          list1 =  listproduit.getContent();
          m.addAttribute("total1",total1);  
          m.addAttribute("current1",page1+1);
          session.setAttribute("listproduitdn", list1);
          return new ModelAndView("resultat/dn/produitDn");
             
         }


@Autowired
ResultatDnRep repResultatDnRep;
@Autowired
ResultatDnService resultatDnService;
         @GetMapping(path = "/resultatdnstat")
         public ModelAndView resultatdnstat(Model m ,HttpSession session,
                 @RequestParam(name="iddn") int iddn) {   
                    session.setAttribute("iddn", iddn);             
                    session.setAttribute("enquete", "DN");
                     session.setAttribute("unite", "%");
                     m.addAttribute("data1", resultatDnService.getStatistiqueresultatdn(iddn));
                     m.addAttribute("nom", repResultatDnRep.resultatnom(iddn));  
               return new ModelAndView("resultat/dn/resultatstat");			
           }

           @GetMapping(path = "/resultatdnprix")
           public ModelAndView resultatdnprix(Model m ,HttpSession session                   ) {   
                     int iddn =(int)session.getAttribute("iddn");             
                      session.setAttribute("enquete", "Prix");
                      session.setAttribute("unite", "en Ariary");
                      m.addAttribute("data1", repResultatDnRep.resultatprix(iddn));
                      m.addAttribute("nom", repResultatDnRep.resultatnom(iddn));  
                 return new ModelAndView("resultat/dn/resultatprix");			
             }

             @GetMapping(path = "/resultatdnrotation")
             public ModelAndView resultatdnrotation(Model m ,HttpSession session                   ) {   
                       int iddn =(int)session.getAttribute("iddn");             
                        session.setAttribute("enquete", "Rotation");
                        session.setAttribute("unite", "par nombre de vente");
                        m.addAttribute("data1", repResultatDnRep.resultatrotation(iddn));
                        m.addAttribute("nom", repResultatDnRep.resultatnom(iddn));  
                   return new ModelAndView("resultat/dn/resultatprix");			
               }
}
