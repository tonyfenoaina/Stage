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


import com.example.demo.model.Enqueteur;
import com.example.demo.repository.EnqueteurRep;

@RestController
public class EnqueteurController {
	@Autowired
	EnqueteurRep enqueteurRep;
	
	 @GetMapping(path = "/enqueteur")
	  public ModelAndView Enqueteur(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		 Pageable paging = PageRequest.of(page, size);
		 
		 Page<Enqueteur> pageTuts=  enqueteurRep.listEnqueteur(paging);
		 int total = pageTuts.getTotalPages();
		 List<Enqueteur> listEnqueteur = new ArrayList<Enqueteur>();
		 listEnqueteur =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listEnqueteur", listEnqueteur);
		
			return new ModelAndView("crud/Enqueteur");
			
		}
	
	@GetMapping(path = "/pageEnqueteur")
	  public ModelAndView listQuartier(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Enqueteur> pageTuts=  enqueteurRep.listEnqueteur(paging);
		 int total = pageTuts.getTotalPages();
		 List<Enqueteur> listEnqueteur = new ArrayList<Enqueteur>();
		 listEnqueteur =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listEnqueteur", listEnqueteur);
		
			return new ModelAndView("crud/Enqueteur");
			
		}
	
	@PostMapping(path = "/AjouterEnqueteur")
	  public ModelAndView Ajouter(Model m,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name = "prenom",required = false) String prenom,
			  @RequestParam(name = "matricule",required = false) String matricule,
			  @RequestParam(name = "motdepasse",required = false) String motdepasse,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {

			 Pageable paging = PageRequest.of(page, size);		 
			 Page<Enqueteur> pageTuts=  enqueteurRep.listEnqueteur(paging);
			 int total = pageTuts.getTotalPages();
			 List<Enqueteur> listEnqueteur = new ArrayList<Enqueteur>();
			 listEnqueteur =  pageTuts.getContent();
			m.addAttribute("total",total);  
			m.addAttribute("current",page+1); 
			enqueteurRep.save(new Enqueteur(nom,prenom,matricule,motdepasse,"active"));
			session.setAttribute("listEnqueteur", listEnqueteur);			
			return new ModelAndView("crud/Enqueteur");			
		}
	
	@PostMapping(path = "/RechercheEnqueteur")
	  public ModelAndView RechercheEnqueteur(Model m ,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "2") int size) {	
			
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Enqueteur> pageTuts=  enqueteurRep.listEnqueteur(paging);
		 int total = pageTuts.getTotalPages();
		 List<Enqueteur> listEnqueteur = new ArrayList<Enqueteur>();
		 listEnqueteur =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listEnqueteur", enqueteurRep.listEnqueteurRec(nom));
			m.addAttribute("retour", noms);
			return new ModelAndView("crud/Enqueteur");			
		}
	
	@GetMapping(path = "/DesactiveEnqueteur")
	  public ModelAndView DesactiveEnqueteur(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {	
		Enqueteur q = new Enqueteur();
		q = enqueteurRep.getById(Integer.parseInt(id));
		q.setEtat("desactive");
		enqueteurRep.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Enqueteur> pageTuts=  enqueteurRep.listEnqueteur(paging);
		 int total = pageTuts.getTotalPages();
		 List<Enqueteur> listEnqueteur = new ArrayList<Enqueteur>();
		 listEnqueteur =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listEnqueteur", listEnqueteur);
	//	session.setAttribute("listEnqueteur", enqueteurRep.listEnqueteur());
			return new ModelAndView("crud/Enqueteur");			
		}
	
	@GetMapping(path = "/ActiveEnqueteur")
	  public ModelAndView ActiveEnqueteur(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {	
		Enqueteur q = new Enqueteur();
		q = enqueteurRep.getById(Integer.parseInt(id));
		q.setEtat("active");
		enqueteurRep.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Enqueteur> pageTuts=  enqueteurRep.listEnqueteur(paging);
		 int total = pageTuts.getTotalPages();
		 List<Enqueteur> listEnqueteur = new ArrayList<Enqueteur>();
		 listEnqueteur =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listEnqueteur", listEnqueteur);
	//	session.setAttribute("listEnqueteur", enqueteurRep.listEnqueteur());
			return new ModelAndView("crud/Enqueteur");			
		}
	
	
	
	@PostMapping(path = "/UpdateEnqueteur")
	  public ModelAndView UpdateQuartier(HttpSession session,Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name = "prenom",required = false) String prenom,
			  @RequestParam(name = "matricule",required = false) String matricule,
			  @RequestParam(name = "motdepasse",required = false) String motdepasse,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		
		Enqueteur q = new Enqueteur();
		q = enqueteurRep.getById(Integer.parseInt(id));
		q.setNom(nom);
		q.setPrenom(prenom);
		q.setMatricule(matricule);
		q.setMotedepasse(motdepasse);
		
		enqueteurRep.save(q);		
		
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Enqueteur> pageTuts=  enqueteurRep.listEnqueteur(paging);
		 int total = pageTuts.getTotalPages();
		 List<Enqueteur> listEnqueteur = new ArrayList<Enqueteur>();
		 listEnqueteur =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listEnqueteur", listEnqueteur);
		
		
		//session.setAttribute("listEnqueteur", enqueteurRep.listEnqueteur());
			return new ModelAndView("crud/Enqueteur");			
		}
}
