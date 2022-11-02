package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

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

import com.example.demo.model.Detailproduit;
import com.example.demo.model.Rotation_prix;
import com.example.demo.repository.ConcurrenceRep;
import com.example.demo.repository.DetailProduitRep;
import com.example.demo.repository.Rotation_prixRep;
import com.example.demo.service.ConcurrenceService;

@Controller
public class FicheProduitController {
    @Autowired
    DetailProduitRep detailproduitRep;

    @Autowired
    Rotation_prixRep rotation_prixRep;

    @GetMapping(path = "/fiche_produit")
    public ModelAndView fiche_produit(HttpSession session,
            @RequestParam(name = "idcategorie",required = false) int idcategorie,
            @RequestParam(name = "idproduit",required = false) int idproduit,
            @RequestParam(name = "nom",required = false) String nom,
            Model m) {
                Rotation_prix listMoyen = new Rotation_prix();
                Rotation_prix listMax  = new Rotation_prix();
                Rotation_prix listMin = new Rotation_prix();

                listMoyen = rotation_prixRep.getMoyenne(idproduit);
                listMax = rotation_prixRep.getMoyenne(idproduit);
                listMin = rotation_prixRep.getMax(idproduit);
                session.setAttribute("idcategorie", idcategorie);
                session.setAttribute("idproduit", idproduit);
                m.addAttribute("listMoyen", listMoyen);
                m.addAttribute("listMax", listMax);
                m.addAttribute("listMin", listMin);
                m.addAttribute("CurrentDn", rotation_prixRep.getcurrent_dn(idproduit));
                m.addAttribute("nom", nom);

          return new ModelAndView("fiche/ficheproduit");        
      }
      @Autowired
      ConcurrenceService concurrenceService;
    @GetMapping(path = "/concurrence_produit")
    public ModelAndView concurrence_produit(HttpSession session,
            Model m
//            @RequestParam(name = "nom",required = false) String nomMarque,
           ) {            
            Date now = new Date();
            int mois = now.getMonth() ;
            int year = now.getYear()+1900;
            String moisnow = year+"-"+mois;
            session.setAttribute("lien", "concurrence_produitparmois");
            session.setAttribute("enquete", "DN");
            session.setAttribute("unite", "%");
            m.addAttribute("mois1", moisnow);
            int idcategorie = (int)session.getAttribute("idcategorie");
            m.addAttribute("nomProduit", concurrenceService.getNomparmoisparcategorie(idcategorie,"2022-1"));
            m.addAttribute("data1", concurrenceService.getStatistiqueparmoisparCategorie(idcategorie,moisnow));
            m.addAttribute("data2", concurrenceService.getStatistiqueparmoisparCategorie(idcategorie,"2022-1"));   
          return new ModelAndView("fiche/concurrenceproduit");        
      }

      @Autowired
      ConcurrenceRep concurrenceRep; 
      @GetMapping(path = "/concurrence_prix")
      public ModelAndView concurrence_prix(HttpSession session,
              Model m
  //            @RequestParam(name = "nom",required = false) String nomMarque,
             ) {
              Date now = new Date();
              int mois = now.getMonth() ;
              int year = now.getYear()+1900;
              String moisnow = year+"-"+mois;
              session.setAttribute("lien", "concurrence_prixparmois");
              session.setAttribute("enquete", "Prix");
              session.setAttribute("unite", "Ariary");
              m.addAttribute("mois1", moisnow);
              int idcategorie = (int)session.getAttribute("idcategorie");
              m.addAttribute("nomProduit", concurrenceService.getNomparmoisparcategorie(idcategorie,"2022-1"));
              m.addAttribute("data1", concurrenceRep.getPrixConcurrenceCategorie(idcategorie,moisnow));
              m.addAttribute("data2", concurrenceRep.getPrixConcurrenceCategorie(idcategorie,"2022-1"));   
            return new ModelAndView("fiche/concurrenceproduit");        
        }

        @GetMapping(path = "/concurrence_rotation")
        public ModelAndView concurrence_rotation(HttpSession session,
                Model m
    //            @RequestParam(name = "nom",required = false) String nomMarque,
               ) {
                Date now = new Date();
                int mois = now.getMonth() ;
                int year = now.getYear()+1900;
                String moisnow = year+"-"+mois;
                session.setAttribute("lien", "concurrence_rotationparmois");
                session.setAttribute("enquete", "Rotation");
                session.setAttribute("unite", "pcks");
                m.addAttribute("mois1", moisnow);
                int idcategorie = (int)session.getAttribute("idcategorie");
                m.addAttribute("nomProduit", concurrenceService.getNomparmoisparcategorie(idcategorie,"2022-1"));
                m.addAttribute("data1", concurrenceRep.getRotationConcurrenceCategorie(idcategorie,moisnow));
              m.addAttribute("data2", concurrenceRep.getRotationConcurrenceCategorie(idcategorie,"2022-1"));    
              return new ModelAndView("fiche/concurrenceproduit");        
          }

      @GetMapping(path = "/concurrence_produitparmois")
      public ModelAndView concurrence_produitparmois(Model m ,HttpSession session,
              @RequestParam(name="mois1") String mois1,
              @RequestParam(name="mois2") String mois2) {
                int idcategorie = (int)session.getAttribute("idcategorie");             
                m.addAttribute("mois1", mois1);
                m.addAttribute("mois2", mois2);      
                m.addAttribute("nomProduit", concurrenceService.getNomparmoisparcategorie(idcategorie,mois1));
                m.addAttribute("data1", concurrenceService.getStatistiqueparmoisparCategorie(idcategorie,mois1));
                m.addAttribute("data2", concurrenceService.getStatistiqueparmoisparCategorie(idcategorie,mois2));         
            return new ModelAndView("fiche/concurrenceproduit");			
        } 

        @GetMapping(path = "/concurrence_prixparmois")
      public ModelAndView concurrence_prixparmois(Model m ,HttpSession session,
              @RequestParam(name="mois1") String mois1,
              @RequestParam(name="mois2") String mois2) {
                int idcategorie = (int)session.getAttribute("idcategorie");             
                m.addAttribute("mois1", mois1);
                m.addAttribute("mois2", mois2);      
                m.addAttribute("nomProduit", concurrenceService.getNomparmoisparcategorie(idcategorie,mois1));
                m.addAttribute("data1", concurrenceRep.getPrixConcurrenceCategorie(idcategorie,mois1));
                m.addAttribute("data2",concurrenceRep.getPrixConcurrenceCategorie(idcategorie,mois2));         
            return new ModelAndView("fiche/concurrenceproduit");			
        } 

        @GetMapping(path = "/concurrence_rotationparmois")
        public ModelAndView concurrence_rotationparmois(Model m ,HttpSession session,
                @RequestParam(name="mois1") String mois1,
                @RequestParam(name="mois2") String mois2) {
                  int idcategorie = (int)session.getAttribute("idcategorie");             
                  m.addAttribute("mois1", mois1);
                  m.addAttribute("mois2", mois2);      
                  m.addAttribute("nomProduit", concurrenceService.getNomparmoisparcategorie(idcategorie,mois1));
                  m.addAttribute("data1", concurrenceRep.getRotationConcurrenceCategorie(idcategorie,mois1));
                  m.addAttribute("data2",concurrenceRep.getRotationConcurrenceCategorie(idcategorie,mois2));         
              return new ModelAndView("fiche/concurrenceproduit");			
          } 
    @GetMapping(path = "/listproduit_fiche")
    public ModelAndView produit(HttpSession session,
            Model m,
            @RequestParam(name = "nom",required = false) String nomMarque,
            @RequestParam(name="page" ,defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "6") int size) {
       Pageable paging = PageRequest.of(page, size);
       session.setAttribute("nomMarque", nomMarque);
       Page<Detailproduit> pageTuts=  detailproduitRep.findAll(paging);
       int total = pageTuts.getTotalPages();
       List<Detailproduit> list = new ArrayList<Detailproduit>();
       list =  pageTuts.getContent();
      m.addAttribute("total",total);  
      m.addAttribute("current",page+1); 
      
      session.setAttribute("listProduit", list);
      
          return new ModelAndView("fiche/produit");        
      }
    
    @PostMapping(path = "/RechercheProduit_fiche")
    public ModelAndView RechercheMarque(Model m ,HttpSession session,           
            @RequestParam(name = "nom",required = false) String nom,
            @RequestParam(name="page" ,defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "6") int size) {    

       Pageable paging = PageRequest.of(page, size);       
          m.addAttribute("total",1);  
          m.addAttribute("current",page+1); 
          String noms = nom;
          nom = "%"+nom+"%";
          session.setAttribute("listProduit", detailproduitRep.listProduitRecfiche(nom));
          m.addAttribute("retour", noms);
          return new ModelAndView("fiche/produit");             
      }
}
