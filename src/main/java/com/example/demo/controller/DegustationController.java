package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Categorie;
import com.example.demo.model.Degustation;
import com.example.demo.model.Detailmarque;
import com.example.demo.model.Detailproduitdegustation;
import com.example.demo.model.Detailproduitdn;
import com.example.demo.repository.CategorieRep;
import com.example.demo.repository.DegustationRep;
import com.example.demo.repository.DetailmarqueRep;
import com.example.demo.repository.DetailproduitdegustationRep;
import com.example.demo.repository.DetailproduitdnRep;
import com.example.demo.repository.ProduitdegustationRep;



@RestController
public class DegustationController {
@Autowired
DegustationRep degustationRep;

@Autowired
CategorieRep categorieRep;

@Autowired
DetailmarqueRep detailmarqueRep;

@GetMapping(path = "/degustation")
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
      return new ModelAndView("crud/degustation/Degustation");
  }

  @GetMapping(path = "/pageCategorieDegustation")
  public ModelAndView CategorieDn(HttpSession session,
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
    return new ModelAndView("crud/degustation/categorie");			
    }

    @GetMapping(path = "/pageAjoutDegustation")
	  public ModelAndView pageAjoutEnquete(HttpSession session,
			  Model m,
			  @RequestParam(name="idCateg" ) int idCateg){	
		session.setAttribute("idcategorie", idCateg);		
			return new ModelAndView("crud/degustation/AjoutDegustation");
			
              }

    @PostMapping(path = "/RechercheCategorieDegustation")
    public ModelAndView RechercheCategorieDn(Model m ,HttpSession session,
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
            return new ModelAndView("crud/degustation/categorie");			
        }

@Autowired
DetailproduitdegustationRep produitdegustationRep;
        @PostMapping(path = "/AjouterDegustation")
	  public ModelAndView Ajouter(Model m,HttpSession session,
			  @RequestParam(name = "titre",required = false) String titre,
			  @RequestParam(name = "debut",required = false) String debuts,
			  @RequestParam(name = "fin",required = false) String fins,
			  @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size){
		
		LocalDate debut = LocalDate.parse(debuts);
		LocalDate fin = LocalDate.parse(fins);
		
			int idcategorie= (int)session.getAttribute("idcategorie");
			Degustation dn = degustationRep.save(new Degustation(titre,debut,fin,idcategorie));		
			session.setAttribute("iddegustation", dn.getId());
			 int iddegustation = (int)session.getAttribute("iddegustation");
			 Pageable paging = PageRequest.of(page, size);	 
			 Page<Detailmarque> pageTuts=  detailmarqueRep.listMarque(paging,idcategorie);
			 int total = pageTuts.getTotalPages();
			 List<Detailmarque> list = new ArrayList<Detailmarque>();
			 list =  pageTuts.getContent();
			 m.addAttribute("total",total);  
			 m.addAttribute("current",page+1); 
			 session.setAttribute("listMarque", list);	 
			 
			 //produit
			 Pageable paging1 = PageRequest.of(page1, size1);
             System.out.println("IDDEGUSTATION :"+iddegustation);
			 Page<Detailproduitdegustation> listproduit = produitdegustationRep.listDetailproduitDegustation(paging1, iddegustation);	
			 int total1 = listproduit.getTotalPages();
			 List<Detailproduitdegustation> list1 = new ArrayList<Detailproduitdegustation>();
			 list1 =  listproduit.getContent();
			 m.addAttribute("total1",total1);  
			 m.addAttribute("current1",page1+1);
			 session.setAttribute("listproduitdn", list1);		
			return new ModelAndView("crud/degustation/produitDegustation");			
		}

        @PostMapping(path = "/RechercheDegustation")
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
              return new ModelAndView("crud/degustation/Degustation");			
          }
  
      
      @PostMapping(path = "/UpdateDegustation")
        public ModelAndView UpdateQuartier(HttpSession session,Model m,
                @RequestParam(name = "id",required = false) String id,
                @RequestParam(name = "titre",required = false) String titre,
                @RequestParam(name = "debut",required = false) String debuts,
                @RequestParam(name = "fin",required = false) String fins,
                @RequestParam(name = "unite",required = false) int unite,
                @RequestParam(name="page" ,defaultValue = "0") int page,
                @RequestParam(name="size", defaultValue = "6") int size) {
          
          LocalDate debut = LocalDate.parse(debuts);
          LocalDate fin = LocalDate.parse(fins);
          Degustation q = new Degustation();
          q = degustationRep.getById(Integer.parseInt(id));
          q.setTitre(titre);
          q.setDebut(debut);
          q.setFin(fin);
        
      
          degustationRep.save(q);		
          Pageable paging = PageRequest.of(page, size);		 
           Page<Degustation> pageTuts=  degustationRep.listDegustation(paging);
           int total = pageTuts.getTotalPages();
           List<Degustation> listDn = new ArrayList<Degustation>();
           listDn =  pageTuts.getContent();
          m.addAttribute("total",1);  
          m.addAttribute("current",page+1); 
              String noms =titre;
              titre = "%"+titre+"%";
              session.setAttribute("listDn", listDn);
          //session.setAttribute("listDn", dnRep.listDn());
              return new ModelAndView("crud/degustation/Degustation");			
          }
      

}
