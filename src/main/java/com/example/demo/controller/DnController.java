package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Categorie;
import com.example.demo.model.Detaildn;
import com.example.demo.model.Detailmarque;
import com.example.demo.model.Dn;
import com.example.demo.repository.CategorieRep;
import com.example.demo.repository.DetaildnRep;
import com.example.demo.repository.DetailmarqueRep;
import com.example.demo.repository.DnRep;
import com.example.demo.repository.UniteRep;

@RestController
public class DnController {
	@Autowired
	DnRep dnRep;
	
	@Autowired
	DetaildnRep detaildnRep;
	
	@Autowired
	UniteRep uniteRep;
	
	@Autowired
	CategorieRep categorierep;
	
	@Autowired
	DetailmarqueRep detailmarqueRep;
	
	
	
	 @GetMapping(path = "/Dn")
	  public ModelAndView Dn(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size){
		 Pageable paging = PageRequest.of(page, size); 
		 session.setAttribute("listUniteinput",uniteRep.findAll());
		 Page<Detaildn> pageTuts=  detaildnRep.listDetaildn(paging);
		 int total = pageTuts.getTotalPages();
		 List<Detaildn> listDn = new ArrayList<Detaildn>();
		 listDn =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listDn", listDn);
		
			return new ModelAndView("crud/dn/Dn");
			
		}
	 
	 @GetMapping(path = "/pageAjoutEnquete")
	  public ModelAndView pageAjoutEnquete(HttpSession session,
			  Model m,
			  @RequestParam(name="idCateg" ) int idCateg){	
		session.setAttribute("idCategories", idCateg);		
			return new ModelAndView("crud/dn/AjoutDn");
			
		}
	 
	 @GetMapping(path = "/produitDn")
	  public ModelAndView produitDn(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size,
		      @RequestParam(name="idCateg" , defaultValue = "1" ) int idCateg
			){		 
		 	Pageable paging = PageRequest.of(page, size);	 
		 Page<Detailmarque> pageTuts=  detailmarqueRep.listMarque(paging,1);
		 int total = pageTuts.getTotalPages();
		 List<Detailmarque> list = new ArrayList<Detailmarque>();
		 list =  pageTuts.getContent();
		 m.addAttribute("total",total);  
		 m.addAttribute("current",page+1); 
		 session.setAttribute("listMarque", list);
			
			return new ModelAndView("crud/dn/produitDn");
			
		}
	 
	 @GetMapping(path = "/CategorieDn")
	  public ModelAndView CategorieDn(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		 Pageable paging = PageRequest.of(page, size);
		 
		 Page<Categorie> pageTuts=  categorierep.listCategorie(paging);
		 int total = pageTuts.getTotalPages();
		 List<Categorie> list = new ArrayList<Categorie>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 		
		session.setAttribute("listCategorie", list);	
		return new ModelAndView("crud/dn/categorie");			
		}
	 
	 @PostMapping(path = "/RechercheCategorieDn")
	 public ModelAndView RechercheCategorieDn(Model m ,HttpSession session,
	 		  @RequestParam(name = "nom",required = false) String nom,
	 		  @RequestParam(name="page" ,defaultValue = "0") int page,
	 	      @RequestParam(name="size", defaultValue = "10") int size) {	
	 		
	 	 Pageable paging = PageRequest.of(page, size);		 
	 	m.addAttribute("total",1);  
	 	m.addAttribute("current",page+1); 
	 		String noms = nom;
	 		nom = "%"+nom+"%";
	 		System.out.print("tyyy"+nom);
	 		System.out.print("tyyy"+categorierep.listCategorieRec(nom));
	 		session.setAttribute("listCategorie", categorierep.listCategorieRec(nom));
	 		m.addAttribute("retour", noms);
	 		return new ModelAndView("crud/dn/categorie");			
	 	}
	 
	 @GetMapping(path = "/pageCategorieDn")
	 public ModelAndView listCategorie(HttpSession session,
	 		  Model m,
	 		  @RequestParam(name="page" ,defaultValue = "0") int page,
	 	      @RequestParam(name="size", defaultValue = "10") int size) {
	 	 Pageable paging = PageRequest.of(page, size);		 
	 	 Page<Categorie> pageTuts=  categorierep.listCategorie(paging);
	 	 int total = pageTuts.getTotalPages();
	 	 List<Categorie> list = new ArrayList<Categorie>();
	 	 list =  pageTuts.getContent();
	 	m.addAttribute("total",total);  
	 	m.addAttribute("current",page+1);	 	
	 	session.setAttribute("listCategorie", list);	 	
	 		return new ModelAndView("crud/categorie");
	 		
	 	}
	 
	 
	
	@GetMapping(path = "/pageDn")
	  public ModelAndView listQuartier(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Detaildn> pageTuts=  detaildnRep.listDetaildn(paging);
		 int total = pageTuts.getTotalPages();
		 List<Detaildn> listDn = new ArrayList<Detaildn>();
		 listDn =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listDn", listDn);
		
			return new ModelAndView("crud/dn/Dn");
			
		}
	
	@PostMapping(path = "/AjouterDn")
	  public ModelAndView Ajouter(Model m,HttpSession session,
			  @RequestParam(name = "titre",required = false) String titre,
			  @RequestParam(name = "debut",required = false) LocalDate debut,
			  @RequestParam(name = "fin",required = false) LocalDate fin,
			  @RequestParam(name = "unite",required = false) int unite,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		
			int idcategorie= (int)session.getAttribute("idcategoriesinput");
			dnRep.save(new Dn(titre,debut,fin,idcategorie,unite));
			 Pageable paging = PageRequest.of(page, size);		 
			 Page<Detaildn> pageTuts=  detaildnRep.listDetaildn(paging);
			 int total = pageTuts.getTotalPages();
			 List<Detaildn> listDn = new ArrayList<Detaildn>();
			 listDn =  pageTuts.getContent();
			m.addAttribute("total",total);  
			m.addAttribute("current",page+1); 	
			session.setAttribute("listDn", listDn);			
			return new ModelAndView("crud/dn/Dn");			
		}
	
	@PostMapping(path = "/RechercheDn")
	  public ModelAndView RechercheDn(Model m ,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "2") int size) {	
			
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Detaildn> pageTuts=  detaildnRep.listDetaildn(paging);
		 int total = pageTuts.getTotalPages();
		 List<Detaildn> listDn = new ArrayList<Detaildn>();
		 listDn =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listDn", detaildnRep.listDetaildnRec(nom));
			m.addAttribute("retour", noms);
			return new ModelAndView("crud/dn/Dn");			
		}

	
	@PostMapping(path = "/UpdateDn")
	  public ModelAndView UpdateQuartier(HttpSession session,Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name = "titre",required = false) String titre,
			  @RequestParam(name = "debut",required = false) LocalDate debut,
			  @RequestParam(name = "fin",required = false) LocalDate fin,
			  @RequestParam(name = "unite",required = false) int unite,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		
		Dn q = new Dn();
		q = dnRep.getById(Integer.parseInt(id));
		q.setTitre(titre);
		q.setDebut(debut);
		q.setFin(fin);
		q.setIdunite(unite);
	
		dnRep.save(q);		
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Detaildn> pageTuts=  detaildnRep.listDetaildn(paging);
		 int total = pageTuts.getTotalPages();
		 List<Detaildn> listDn = new ArrayList<Detaildn>();
		 listDn =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms =titre;
			titre = "%"+titre+"%";
			session.setAttribute("listDn", listDn);
		//session.setAttribute("listDn", dnRep.listDn());
			return new ModelAndView("crud/dn/Dn");			
		}
	
	
}
