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

import com.example.demo.model.DetailUtilisateur;
import com.example.demo.model.Utilisateur;
import com.example.demo.repository.DetailUtilisateurRep;
import com.example.demo.repository.FonctionRep;
import com.example.demo.repository.UtilisateurRep;

@RestController
public class UtilisateurController {
	@Autowired
	UtilisateurRep utilisateurRep;
	
	@Autowired
	DetailUtilisateurRep detailutilisateurRep;
	
	@Autowired
	FonctionRep fonctionRep;
	
	 @GetMapping(path = "/Utilisateur")
	  public ModelAndView Utilisateur(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		 Pageable paging = PageRequest.of(page, size);
		 session.setAttribute("fonctionInput",fonctionRep.findAll());
		 System.out.println("haa"+fonctionRep.findAll().get(0).getNom());
		 Page<DetailUtilisateur> pageTuts=  detailutilisateurRep.listDetailUtilisateur(paging);
		 int total = pageTuts.getTotalPages();
		 List<DetailUtilisateur> listUtilisateur = new ArrayList<DetailUtilisateur>();
		 listUtilisateur =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listUtilisateur", listUtilisateur);
		
			return new ModelAndView("crud/utilisateur");
			
		}
	
	@GetMapping(path = "/pageUtilisateur")
	  public ModelAndView listQuartier(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<DetailUtilisateur> pageTuts=  detailutilisateurRep.listDetailUtilisateur(paging);
		 int total = pageTuts.getTotalPages();
		 List<DetailUtilisateur> listUtilisateur = new ArrayList<DetailUtilisateur>();
		 listUtilisateur =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listUtilisateur", listUtilisateur);		
			return new ModelAndView("crud/utilisateur");		
		}
	
	@PostMapping(path = "/AjouterUtilisateur")
	  public ModelAndView Ajouter(Model m,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name = "prenom",required = false) String prenom,
			  @RequestParam(name = "fonction",required = false) int fonction,
			  @RequestParam(name = "matricule",required = false) String matricule,
			  @RequestParam(name = "motdepasse",required = false) String motdepasse,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
			utilisateurRep.save(new Utilisateur(nom,prenom,fonction,matricule, motdepasse,"responsable","active"));
			 Pageable paging = PageRequest.of(page, size);		 
			 Page<DetailUtilisateur> pageTuts=  detailutilisateurRep.listDetailUtilisateur(paging);
			 int total = pageTuts.getTotalPages();
			 List<DetailUtilisateur> listUtilisateur = new ArrayList<DetailUtilisateur>();
			 listUtilisateur =  pageTuts.getContent();
			m.addAttribute("total",total);  
			m.addAttribute("current",page+1); 
		
			session.setAttribute("listUtilisateur", listUtilisateur);			
			return new ModelAndView("crud/utilisateur");			
		}
	
	@PostMapping(path = "/RechercheUtilisateur")
	  public ModelAndView RechercheUtilisateur(Model m ,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "2") int size) {	
			
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<DetailUtilisateur> pageTuts=  detailutilisateurRep.listDetailUtilisateur(paging);
		 int total = pageTuts.getTotalPages();
		 List<DetailUtilisateur> listUtilisateur = new ArrayList<DetailUtilisateur>();
		 listUtilisateur =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listUtilisateur", detailutilisateurRep.listDetailUtilisateurRec(nom));
			m.addAttribute("retour", noms);
			return new ModelAndView("crud/utilisateur");			
		}
	
	@GetMapping(path = "/DesactiveUtilisateur")
	  public ModelAndView DesactiveUtilisateur(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		Utilisateur q = new Utilisateur();
		q = utilisateurRep.getById(Integer.parseInt(id));
		q.setEtat("desactive");
		utilisateurRep.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<DetailUtilisateur> pageTuts=  detailutilisateurRep.listDetailUtilisateur(paging);
		 int total = pageTuts.getTotalPages();
		 List<DetailUtilisateur> listUtilisateur = new ArrayList<DetailUtilisateur>();
		 listUtilisateur =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listUtilisateur", listUtilisateur);
	//	session.setAttribute("listUtilisateur", utilisateurRep.listUtilisateur());
			return new ModelAndView("crud/utilisateur");			
		}
	
	@GetMapping(path = "/ActiveUtilisateur")
	  public ModelAndView ActiveUtilisateur(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		Utilisateur q = new Utilisateur();
		q = utilisateurRep.getById(Integer.parseInt(id));
		q.setEtat("active");
		utilisateurRep.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<DetailUtilisateur> pageTuts=  detailutilisateurRep.listDetailUtilisateur(paging);
		 int total = pageTuts.getTotalPages();
		 List<DetailUtilisateur> listUtilisateur = new ArrayList<DetailUtilisateur>();
		 listUtilisateur =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listUtilisateur", listUtilisateur);
	//	session.setAttribute("listUtilisateur", utilisateurRep.listUtilisateur());
			return new ModelAndView("crud/utilisateur");			
		}
	
	
	
	@PostMapping(path = "/UpdateUtilisateur")
	  public ModelAndView UpdateQuartier(HttpSession session,Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name = "prenom",required = false) String prenom,
			  @RequestParam(name = "fonction",required = false) int fonction,
			  @RequestParam(name = "matricule",required = false) String matricule,
			  @RequestParam(name = "motdepasse",required = false) String motdepasse,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		
		Utilisateur q = new Utilisateur();
		q = utilisateurRep.getById(Integer.parseInt(id));
		q.setNom(nom);
		q.setPrenom(prenom);
		q.setFonction(fonction);
		q.setMotedepasse(motdepasse);
		utilisateurRep.save(q);		
		
		Pageable paging = PageRequest.of(page, size);		 
		 Page<DetailUtilisateur> pageTuts=  detailutilisateurRep.listDetailUtilisateur(paging);
		 int total = pageTuts.getTotalPages();
		 List<DetailUtilisateur> listUtilisateur = new ArrayList<DetailUtilisateur>();
		 listUtilisateur =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listUtilisateur", listUtilisateur);
		
		
		//session.setAttribute("listUtilisateur", utilisateurRep.listUtilisateur());
			return new ModelAndView("crud/utilisateur");			
		}
}
