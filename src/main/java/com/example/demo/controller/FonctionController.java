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


import com.example.demo.model.Fonction;
import com.example.demo.repository.FonctionRep;

@RestController
public class FonctionController {
	@Autowired
	FonctionRep fonctionRep;
	
	 @GetMapping(path = "/Fonction")
	  public ModelAndView Fonction(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		 Pageable paging = PageRequest.of(page, size);
		 
		 Page<Fonction> pageTuts=  fonctionRep.listFonction(paging);
		 int total = pageTuts.getTotalPages();
		 List<Fonction> listFonction = new ArrayList<Fonction>();
		 listFonction =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listFonction", listFonction);
		
			return new ModelAndView("crud/fonction");
			
		}
	
	@GetMapping(path = "/pageFonction")
	  public ModelAndView listQuartier(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Fonction> pageTuts=  fonctionRep.listFonction(paging);
		 int total = pageTuts.getTotalPages();
		 List<Fonction> listFonction = new ArrayList<Fonction>();
		 listFonction =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listFonction", listFonction);
		
			return new ModelAndView("crud/fonction");
			
		}
	
	@PostMapping(path = "/AjouterFonction")
	  public ModelAndView Ajouter(Model m,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {

			 Pageable paging = PageRequest.of(page, size);		 
			 Page<Fonction> pageTuts=  fonctionRep.listFonction(paging);
			 int total = pageTuts.getTotalPages();
			 List<Fonction> listFonction = new ArrayList<Fonction>();
			 listFonction =  pageTuts.getContent();
			m.addAttribute("total",total);  
			m.addAttribute("current",page+1); 
			fonctionRep.save(new Fonction(nom,"active"));
			session.setAttribute("listFonction", listFonction);			
			return new ModelAndView("crud/fonction");			
		}
	
	@PostMapping(path = "/RechercheFonction")
	  public ModelAndView RechercheFonction(Model m ,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "2") int size) {	
			
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Fonction> pageTuts=  fonctionRep.listFonction(paging);
		 int total = pageTuts.getTotalPages();
		 List<Fonction> listFonction = new ArrayList<Fonction>();
		 listFonction =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listFonction", fonctionRep.listFonctionRec(nom));
			m.addAttribute("retour", noms);
			return new ModelAndView("crud/fonction");			
		}
	
	@GetMapping(path = "/DesactiveFonction")
	  public ModelAndView DesactiveFonction(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		Fonction q = new Fonction();
		q = fonctionRep.getById(Integer.parseInt(id));
		q.setEtat("desactive");
		fonctionRep.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Fonction> pageTuts=  fonctionRep.listFonction(paging);
		 int total = pageTuts.getTotalPages();
		 List<Fonction> listFonction = new ArrayList<Fonction>();
		 listFonction =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listFonction", listFonction);
	//	session.setAttribute("listFonction", fonctionRep.listFonction());
			return new ModelAndView("crud/fonction");			
		}
	
	@GetMapping(path = "/ActiveFonction")
	  public ModelAndView ActiveFonction(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		Fonction q = new Fonction();
		q = fonctionRep.getById(Integer.parseInt(id));
		q.setEtat("active");
		fonctionRep.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Fonction> pageTuts=  fonctionRep.listFonction(paging);
		 int total = pageTuts.getTotalPages();
		 List<Fonction> listFonction = new ArrayList<Fonction>();
		 listFonction =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listFonction", listFonction);
	//	session.setAttribute("listFonction", fonctionRep.listFonction());
			return new ModelAndView("crud/fonction");			
		}
	
	
	
	@PostMapping(path = "/UpdateFonction")
	  public ModelAndView UpdateQuartier(HttpSession session,Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name = "nom",required = false) String nom,
			 
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		
		Fonction q = new Fonction();
		q = fonctionRep.getById(Integer.parseInt(id));
		q.setNom(nom);
		
		fonctionRep.save(q);		
		
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Fonction> pageTuts=  fonctionRep.listFonction(paging);
		 int total = pageTuts.getTotalPages();
		 List<Fonction> listFonction = new ArrayList<Fonction>();
		 listFonction =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listFonction", listFonction);
		
		
		//session.setAttribute("listFonction", fonctionRep.listFonction());
			return new ModelAndView("crud/fonction");			
		}
}
