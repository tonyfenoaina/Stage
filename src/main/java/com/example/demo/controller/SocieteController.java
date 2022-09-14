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

import com.example.demo.model.Societe;
import com.example.demo.repository.SocieteRep;

@Controller
public class SocieteController {
@Autowired
SocieteRep societeRep;

@GetMapping(path = "/societe")
public ModelAndView Societe(HttpSession session,
		  Model m,
		  @RequestParam(name="id",required = false) int idCategorie,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {
	 Pageable paging = PageRequest.of(page, size);
	 session.setAttribute("idCate", idCategorie);
	 Page<Societe> pageTuts=  societeRep.listSociete(paging,idCategorie);
	 int total = pageTuts.getTotalPages();
	 List<Societe> list = new ArrayList<Societe>();
	 list =  pageTuts.getContent();
	m.addAttribute("total",total);  
	m.addAttribute("current",page+1); 
	
	session.setAttribute("listSociete", list);
	
		return new ModelAndView("crud/societe");
		
	}

@PostMapping(path = "/RechercheSociete")
public ModelAndView RechercheSociete(Model m ,HttpSession session,
		  @RequestParam(name = "nom",required = false) String nom,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {	
		int idCateg = (int)session.getAttribute("idCate");
	 Pageable paging = PageRequest.of(page, size);		 
	m.addAttribute("total",1);  
	m.addAttribute("current",page+1); 
		String noms = nom;
		nom = "%"+nom+"%";
		System.out.print("tyyy"+nom);
		System.out.print("tyyy"+societeRep.listSocieteRec(nom,idCateg));
		session.setAttribute("listSociete", societeRep.listSocieteRec(nom,idCateg));
		m.addAttribute("retour", noms);
		return new ModelAndView("crud/societe");			
	}


@GetMapping(path = "/pageSociete")
public ModelAndView listSociete(HttpSession session,
		  Model m,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {
	int idCateg = (int)session.getAttribute("idCate");
	 Pageable paging = PageRequest.of(page, size);		 
	 Page<Societe> pageTuts=  societeRep.listSociete(paging,idCateg);
	 int total = pageTuts.getTotalPages();
	 List<Societe> list = new ArrayList<Societe>();
	 list =  pageTuts.getContent();
	m.addAttribute("total",total);  
	m.addAttribute("current",page+1);
	
	session.setAttribute("listSociete", list);
	
		return new ModelAndView("crud/societe");
		
	}
@PostMapping(path = "/AjouterSociete")
public ModelAndView Ajouter(HttpSession session,Model m,
		  @RequestParam(name = "nom",required = false) String nom,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {
		
		int idCateg = (int)session.getAttribute("idCate");
		societeRep.save(new Societe(nom,"active",idCateg ));
		
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Societe> pageTuts=  societeRep.listSociete(paging,idCateg);
		 int total = pageTuts.getTotalPages();
		 List<Societe> list = new ArrayList<Societe>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listSociete", list);	
		
		return new ModelAndView("crud/societe");			
	}

@GetMapping(path = "/DesactiveSociete")
public ModelAndView DesactiveSociete(HttpSession session,  Model m,
		  @RequestParam(name = "id",required = false) String id,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {
	int idCateg = (int)session.getAttribute("idCate");
	Societe q = new Societe();
	q = societeRep.getById(Integer.parseInt(id));
	q.setEtat("desactive");
	societeRep.save(q);			
	Pageable paging = PageRequest.of(page, size);		 
	 Page<Societe> pageTuts=  societeRep.listSociete(paging,idCateg);
	 int total = pageTuts.getTotalPages();
	 List<Societe> list = new ArrayList<Societe>();
	 list =  pageTuts.getContent();
	m.addAttribute("total",1);  
	m.addAttribute("current",page+1); 			
		session.setAttribute("listSociete", list);
	
		return new ModelAndView("crud/societe");			
	}

@GetMapping(path = "/ActiveSociete")
public ModelAndView ActiveSociete(HttpSession session,  Model m,
		  @RequestParam(name = "id",required = false) String id,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {	
	Societe q = new Societe();
	int idCateg = (int)session.getAttribute("idCate");
	q = societeRep.getById(Integer.parseInt(id));
	q.setEtat("active");
	societeRep.save(q);			
	Pageable paging = PageRequest.of(page, size);		 
	 Page<Societe> pageTuts=  societeRep.listSociete(paging,idCateg);
	 int total = pageTuts.getTotalPages();
	 List<Societe> list = new ArrayList<Societe>();
	 list =  pageTuts.getContent();
	m.addAttribute("total",1);  
	m.addAttribute("current",page+1); 			
		session.setAttribute("listSociete", list);
	
		return new ModelAndView("crud/societe");			
	}

@PostMapping(path = "/UpdateSociete")
public ModelAndView UpdateSociete(HttpSession session,Model m,
		  @RequestParam(name = "id",required = false) String id,
		  @RequestParam(name = "nom",required = false) String nom,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "10") int size) {
	int idCateg = (int)session.getAttribute("idCate");
	Societe q = new Societe();
	q = societeRep.getById(Integer.parseInt(id));
	q.setNom(nom);
	societeRep.save(q);		
	Pageable paging = PageRequest.of(page, size);		 
	 Page<Societe> pageTuts=  societeRep.listSociete(paging,idCateg);
	 int total = pageTuts.getTotalPages();
	 List<Societe> list = new ArrayList<Societe>();
	 list =  pageTuts.getContent();
	m.addAttribute("total",1);  
	m.addAttribute("current",page+1); 
		String noms = nom;
		nom = "%"+nom+"%";
		session.setAttribute("listSociete", list);
		return new ModelAndView("crud/societe");			
	}
	
}
