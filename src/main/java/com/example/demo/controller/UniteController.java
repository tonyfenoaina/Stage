package com.example.demo.controller;

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


import com.example.demo.model.Unite;
import com.example.demo.repository.UniteRep;

@RestController
public class UniteController {
	@Autowired
	UniteRep uniteRep;
	
	 @GetMapping(path = "/unite")
	  public ModelAndView Unite(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		 Pageable paging = PageRequest.of(page, size);
		 
		 Page<Unite> pageTuts=  uniteRep.listUnite(paging);
		 int total = pageTuts.getTotalPages();
		 List<Unite> listUnite = new ArrayList<Unite>();
		 listUnite =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listUnite", listUnite);
		
			return new ModelAndView("crud/dn/unite");
			
		}
	
	@GetMapping(path = "/pageUnite")
	  public ModelAndView listQuartier(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Unite> pageTuts=  uniteRep.listUnite(paging);
		 int total = pageTuts.getTotalPages();
		 List<Unite> listUnite = new ArrayList<Unite>();
		 listUnite =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listUnite", listUnite);
		
			return new ModelAndView("crud/dn/unite");
			
		}
	
	@PostMapping(path = "/AjouterUnite")
	  public ModelAndView Ajouter(Model m,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
			 uniteRep.save(new Unite(nom,"active"));
			 Pageable paging = PageRequest.of(page, size);		 
			 Page<Unite> pageTuts=  uniteRep.listUnite(paging);
			 int total = pageTuts.getTotalPages();
			 List<Unite> listUnite = new ArrayList<Unite>();
			 listUnite =  pageTuts.getContent();
			m.addAttribute("total",total);  
			m.addAttribute("current",page+1); 
			
			session.setAttribute("listUnite", listUnite);			
			return new ModelAndView("crud/dn/unite");			
		}
	
	@PostMapping(path = "/RechercheUnite")
	  public ModelAndView RechercheUnite(Model m ,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "2") int size) {	
			
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Unite> pageTuts=  uniteRep.listUnite(paging);
		 int total = pageTuts.getTotalPages();
		 List<Unite> listUnite = new ArrayList<Unite>();
		 listUnite =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listUnite", uniteRep.listUniteRec(nom));
			m.addAttribute("retour", noms);
			return new ModelAndView("crud/dn/unite");			
		}
	
	@GetMapping(path = "/DesactiveUnite")
	  public ModelAndView DesactiveUnite(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		Unite q = new Unite();
		q = uniteRep.getById(Integer.parseInt(id));
		q.setEtat("desactive");
		uniteRep.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Unite> pageTuts=  uniteRep.listUnite(paging);
		 int total = pageTuts.getTotalPages();
		 List<Unite> listUnite = new ArrayList<Unite>();
		 listUnite =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listUnite", listUnite);
	//	session.setAttribute("listUnite", uniteRep.listUnite());
			return new ModelAndView("crud/dn/unite");			
		}
	
	@GetMapping(path = "/ActiveUnite")
	  public ModelAndView ActiveUnite(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		Unite q = new Unite();
		q = uniteRep.getById(Integer.parseInt(id));
		q.setEtat("active");
		uniteRep.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Unite> pageTuts=  uniteRep.listUnite(paging);
		 int total = pageTuts.getTotalPages();
		 List<Unite> listUnite = new ArrayList<Unite>();
		 listUnite =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listUnite", listUnite);
	//	session.setAttribute("listUnite", uniteRep.listUnite());
			return new ModelAndView("crud/dn/unite");			
		}
	
	
	
	@PostMapping(path = "/UpdateUnite")
	  public ModelAndView UpdateQuartier(HttpSession session,Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name = "nom",required = false) String nom,
			 
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		
		Unite q = new Unite();
		q = uniteRep.getById(Integer.parseInt(id));
		q.setNom(nom);
		
		uniteRep.save(q);		
		
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Unite> pageTuts=  uniteRep.listUnite(paging);
		 int total = pageTuts.getTotalPages();
		 List<Unite> listUnite = new ArrayList<Unite>();
		 listUnite =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listUnite", listUnite);
		
		
		//session.setAttribute("listUnite", uniteRep.listUnite());
			return new ModelAndView("crud/dn/unite");			
		}
}
