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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.model.Degustation;
import com.example.demo.model.DetailQuestion;
import com.example.demo.model.Detailproduitdegustation;
import com.example.demo.repository.DegustationRep;
import com.example.demo.repository.DetailQuestionRep;
import com.example.demo.repository.DetailproduitdegustationRep;

@Controller
public class ResultatDegustationController {

    @Autowired
    DegustationRep degustationRep;

    @GetMapping(path = "/resultatdegustation")
    public ModelAndView Degustation(HttpSession session,
            Model m,
            @RequestParam(name="page" ,defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "6") int size){
       Pageable paging = PageRequest.of(page, size); 
       Page<Degustation> pageTuts=  degustationRep.listDegustation(paging);
       int total = pageTuts.getTotalPages();
       List<Degustation> listDn = new ArrayList<Degustation>();
       listDn =  pageTuts.getContent();
      m.addAttribute("total",total);  
      m.addAttribute("current",page+1); 
      session.setAttribute("listDegustation", listDn);		
          return new ModelAndView("resultat/degustation/ResultatDegustation");
      }

      @PostMapping(path = "/RechercheResultatDegustation")
        public ModelAndView RechercheDn(Model m ,HttpSession session,
                @RequestParam(name = "titre",required = false) String nom,
                @RequestParam(name="page" ,defaultValue = "0") int page,
                @RequestParam(name="size", defaultValue = "2") int size) {	
              
           Pageable paging = PageRequest.of(page, size);		 
           Page<Degustation> pageTuts=  degustationRep.listDegustation(paging);
           int total = pageTuts.getTotalPages();
           List<Degustation> listDn = new ArrayList<Degustation>();
           listDn =  pageTuts.getContent();
          m.addAttribute("total",1);  
          m.addAttribute("current",page+1); 
          
              String noms = nom;
              nom = "%"+nom+"%";
              session.setAttribute("listDn", degustationRep.listDegustationRec(nom));
              m.addAttribute("retour", noms);
              return new ModelAndView("resultat/degustation/ResultatDegustation");			
          }

      @Autowired
      DetailproduitdegustationRep detailproduitDegustationRep;
      @GetMapping(path = "/resultatproduitDegustation")
	  public ModelAndView produitDegustation(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
		      @RequestParam(name="iddegustation", required= false) int iddegustation
	
			){
		 session.setAttribute("iddegustation",iddegustation);	
		 iddegustation = (int)session.getAttribute("iddegustation");
		 
		 //produit
		 Pageable paging1 = PageRequest.of(page1, size1);
		
		 Page<Detailproduitdegustation> listproduit = detailproduitDegustationRep.listDetailproduitDegustation(paging1, iddegustation);
		 System.out.println("etoooo");	
		 int total1 = listproduit.getTotalPages();
		 List<Detailproduitdegustation> list1 = new ArrayList<Detailproduitdegustation>();
		 list1 =  listproduit.getContent();
		 m.addAttribute("total1",total1);  
		 m.addAttribute("current1",page1+1);
		 session.setAttribute("listproduitDegustation", list1);
		 System.out.println("etooooooooo");
		 
		 return new ModelAndView("resultat/degustation/produitDegustationResultat");			
		}
        
        @Autowired
        DetailQuestionRep detailQuestionrQuestionRep;

        @GetMapping(path = "/resultatquestion")
        public ModelAndView detailquestion(HttpSession session,
        Model m,
        @RequestParam(name="page" ,defaultValue = "0") int page,
        @RequestParam(name="size", defaultValue = "6") int size,
        @RequestParam(name="page1" ,defaultValue = "0") int page1,
        @RequestParam(name="iddegustation" ,defaultValue = "1") int iddegustation
        ){
        session.setAttribute("iddegustation",iddegustation);	
        iddegustation = (int)session.getAttribute("iddegustation");
        //question
        Pageable paging1 = PageRequest.of(page1, size);
        Page<DetailQuestion> listproduit = detailQuestionrQuestionRep.listQuestionparIddegustation(paging1,iddegustation);
        System.out.println("etoooo");	
        int total1 = listproduit.getTotalPages();
        List<DetailQuestion> list1 = new ArrayList<DetailQuestion>();
        list1 =  listproduit.getContent();
        m.addAttribute("total1",total1);  
        m.addAttribute("current1",page1+1);
        session.setAttribute("listquestion", list1);
        System.out.println("etooooooooo");
        
        return new ModelAndView("resultat/degustation/resultatquestion");			
        }

        @GetMapping(path = "/analysequestion")
        public ModelAndView analysequestion(HttpSession session,
        Model m,
        @RequestParam(name="id" ,defaultValue = "1") int idquestion
        ){
         
        return new ModelAndView("resultat/degustation/analysequestion");			
        }
}
