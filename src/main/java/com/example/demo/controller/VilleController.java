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


import com.example.demo.model.Ville;
import com.example.demo.repository.VilleRep;

@RestController
public class VilleController {
	@Autowired
	VilleRep villeRep;
	
//	@GetMapping(path = "/listVille")
//	  public ModelAndView listVille(HttpSession session) {	
//		
//		ArrayList<Ville> list = new ArrayList<Ville>();
//		list = villeRep.listVille();
//		session.setAttribute("listVille", list);	
//		
//			return new ModelAndView("crud/quartier/liste");
//			
//		}
	
	 @GetMapping(path = "/ville")
	  public ModelAndView ville(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		 Pageable paging = PageRequest.of(page, size);
		 
		 Page<Ville> pageTuts=  villeRep.listVille(paging);
		 int total = pageTuts.getTotalPages();
		 List<Ville> listVille = new ArrayList<Ville>();
		 listVille =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listVille", listVille);
		
			return new ModelAndView("crud/ville");
			
		}
	
	@GetMapping(path = "/pageville")
	  public ModelAndView listQuartier(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Ville> pageTuts=  villeRep.listVille(paging);
		 int total = pageTuts.getTotalPages();
		 List<Ville> listVille = new ArrayList<Ville>();
		 listVille =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listVille", listVille);
		
			return new ModelAndView("crud/ville");
			
		}
	
	@PostMapping(path = "/AjouterVille")
	  public ModelAndView Ajouter(Model m,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
			
		
//		System.out.print(villeRep.verifNom(nom));
//
//		if( villeRep.verifNom(nom)==null) {
//			villeRep.save(new Ville(nom,"active"));
//		}			
			 Pageable paging = PageRequest.of(page, size);		 
			 Page<Ville> pageTuts=  villeRep.listVille(paging);	
			 int total = pageTuts.getTotalPages();
			 List<Ville> listVille = new ArrayList<Ville>();
			 listVille =  pageTuts.getContent();
			m.addAttribute("total",total);  
			m.addAttribute("current",page+1); 
			
			session.setAttribute("listVille", listVille);			
			return new ModelAndView("crud/ville");			
		}
	
	@PostMapping(path = "/RechercheVille")
	  public ModelAndView RechercheVille(Model m ,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "2") int size) {	
			
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Ville> pageTuts=  villeRep.listVille(paging);
		 int total = pageTuts.getTotalPages();
		 List<Ville> listVille = new ArrayList<Ville>();
		 listVille =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listVille", villeRep.listVilleRec(nom));
			m.addAttribute("retour", noms);
			return new ModelAndView("crud/ville");			
		}
	
	@GetMapping(path = "/DesactiveVille")
	  public ModelAndView DesactiveVille(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {	
		Ville q = new Ville();
		q = villeRep.getById(Integer.parseInt(id));
		q.setEtat("desactive");
		villeRep.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Ville> pageTuts=  villeRep.listVille(paging);
		 int total = pageTuts.getTotalPages();
		 List<Ville> listVille = new ArrayList<Ville>();
		 listVille =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listVille", listVille);
	//	session.setAttribute("listVille", villeRep.listVille());
			return new ModelAndView("crud/ville");			
		}
	
	@GetMapping(path = "/ActiveVille")
	  public ModelAndView ActiveVille(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {	
		Ville q = new Ville();
		q = villeRep.getById(Integer.parseInt(id));
		q.setEtat("active");
		villeRep.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Ville> pageTuts=  villeRep.listVille(paging);
		 int total = pageTuts.getTotalPages();
		 List<Ville> listVille = new ArrayList<Ville>();
		 listVille =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listVille", listVille);
	//	session.setAttribute("listVille", villeRep.listVille());
			return new ModelAndView("crud/ville");			
		}
	
	
	
	@PostMapping(path = "/UpdateVille")
	  public ModelAndView UpdateQuartier(HttpSession session,Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		
		Ville q = new Ville();
		q = villeRep.getById(Integer.parseInt(id));
		q.setNom(nom);
		villeRep.save(q);		
		
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Ville> pageTuts=  villeRep.listVille(paging);
		 int total = pageTuts.getTotalPages();
		 List<Ville> listVille = new ArrayList<Ville>();
		 listVille =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listVille", listVille);
		
		
		//session.setAttribute("listVille", villeRep.listVille());
			return new ModelAndView("crud/ville");			
		}
}
