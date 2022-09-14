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

import com.example.demo.model.Categorie;
import com.example.demo.repository.CategorieRep;

@Controller
public class CategorieController {
@Autowired
CategorieRep categorieRep;

@GetMapping(path = "/categorie")
public ModelAndView Categorie(HttpSession session,
		  Model m,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {
	 Pageable paging = PageRequest.of(page, size);
	 
	 Page<Categorie> pageTuts=  categorieRep.listCategorie(paging);
	 int total = pageTuts.getTotalPages();
	 List<Categorie> list = new ArrayList<Categorie>();
	 list =  pageTuts.getContent();
	m.addAttribute("total",total);  
	m.addAttribute("current",page+1); 
	
	session.setAttribute("listCategorie", list);
	
		return new ModelAndView("crud/categorie");
		
	}

@PostMapping(path = "/RechercheCategorie")
public ModelAndView RechercheCategorie(Model m ,HttpSession session,
		  @RequestParam(name = "nom",required = false) String nom,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {	
		
	 Pageable paging = PageRequest.of(page, size);		 
	m.addAttribute("total",1);  
	m.addAttribute("current",page+1); 
		String noms = nom;
		nom = "%"+nom+"%";
		System.out.print("tyyy"+nom);
		System.out.print("tyyy"+categorieRep.listCategorieRec(nom));
		session.setAttribute("listCategorie", categorieRep.listCategorieRec(nom));
		m.addAttribute("retour", noms);
		return new ModelAndView("crud/categorie");			
	}


@GetMapping(path = "/pageCategorie")
public ModelAndView listCategorie(HttpSession session,
		  Model m,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {
	 Pageable paging = PageRequest.of(page, size);		 
	 Page<Categorie> pageTuts=  categorieRep.listCategorie(paging);
	 int total = pageTuts.getTotalPages();
	 List<Categorie> list = new ArrayList<Categorie>();
	 list =  pageTuts.getContent();
	m.addAttribute("total",total);  
	m.addAttribute("current",page+1);
	
	session.setAttribute("listCategorie", list);
	
		return new ModelAndView("crud/categorie");
		
	}
@PostMapping(path = "/AjouterCategorie")
public ModelAndView Ajouter(HttpSession session,Model m,
		  @RequestParam(name = "nom",required = false) String nom,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {
	
		categorieRep.save(new Categorie(nom,"active"));
		
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Categorie> pageTuts=  categorieRep.listCategorie(paging);
		 int total = pageTuts.getTotalPages();
		 List<Categorie> list = new ArrayList<Categorie>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listCategorie", list);	
		
		return new ModelAndView("crud/categorie");			
	}

@GetMapping(path = "/DesactiveCategorie")
public ModelAndView DesactiveCategorie(HttpSession session,  Model m,
		  @RequestParam(name = "id",required = false) String id,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {	
	Categorie q = new Categorie();
	q = categorieRep.getById(Integer.parseInt(id));
	q.setEtat("desactive");
	categorieRep.save(q);			
	Pageable paging = PageRequest.of(page, size);		 
	 Page<Categorie> pageTuts=  categorieRep.listCategorie(paging);
	 int total = pageTuts.getTotalPages();
	 List<Categorie> list = new ArrayList<Categorie>();
	 list =  pageTuts.getContent();
	m.addAttribute("total",1);  
	m.addAttribute("current",page+1); 			
		session.setAttribute("listCategorie", list);
	
		return new ModelAndView("crud/categorie");			
	}

@GetMapping(path = "/ActiveCategorie")
public ModelAndView ActiveCategorie(HttpSession session,  Model m,
		  @RequestParam(name = "id",required = false) String id,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {	
	Categorie q = new Categorie();
	q = categorieRep.getById(Integer.parseInt(id));
	q.setEtat("active");
	categorieRep.save(q);			
	Pageable paging = PageRequest.of(page, size);		 
	 Page<Categorie> pageTuts=  categorieRep.listCategorie(paging);
	 int total = pageTuts.getTotalPages();
	 List<Categorie> list = new ArrayList<Categorie>();
	 list =  pageTuts.getContent();
	m.addAttribute("total",1);  
	m.addAttribute("current",page+1); 			
		session.setAttribute("listCategorie", list);
	
		return new ModelAndView("crud/categorie");			
	}

@PostMapping(path = "/UpdateCategorie")
public ModelAndView Updatecategorie(HttpSession session,Model m,
		  @RequestParam(name = "id",required = false) String id,
		  @RequestParam(name = "nom",required = false) String nom,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {
	
	Categorie q = new Categorie();
	q = categorieRep.getById(Integer.parseInt(id));
	q.setNom(nom);
	categorieRep.save(q);		
	Pageable paging = PageRequest.of(page, size);		 
	 Page<Categorie> pageTuts=  categorieRep.listCategorie(paging);
	 int total = pageTuts.getTotalPages();
	 List<Categorie> list = new ArrayList<Categorie>();
	 list =  pageTuts.getContent();
	m.addAttribute("total",1);  
	m.addAttribute("current",page+1); 
		String noms = nom;
		nom = "%"+nom+"%";
		session.setAttribute("listCategorie", list);
		return new ModelAndView("crud/categorie");			
	}


	
}
