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

import com.example.demo.model.Detailproduit;
import com.example.demo.model.Detailproduit;
import com.example.demo.model.Marque;
import com.example.demo.model.Produit;
import com.example.demo.repository.CategorieRep;
import com.example.demo.repository.DetailProduitRep;

import com.example.demo.repository.MarqueRep;
import com.example.demo.repository.ProduitRep;
import com.example.demo.repository.MarqueRep;
import com.example.demo.repository.SocieteRep;
import com.example.demo.repository.VilleRep;

@Controller
public class ProduitController {
	@Autowired
	MarqueRep MarqueRep;
	
	@Autowired
	SocieteRep societeRep;
	
	@Autowired
	CategorieRep categorieRep;
	
	@Autowired
	DetailProduitRep detailproduitRep;
	
	@Autowired
	ProduitRep produitRep;
	
	
	@GetMapping(path = "/produit")
	  public ModelAndView produit(HttpSession session,
			  Model m,
			  @RequestParam(name = "nom",required = false) String nomMarque,
			  @RequestParam(name="id",required = false) int idMarque,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		 Pageable paging = PageRequest.of(page, size);
		 session.setAttribute("idMarque", idMarque);
		 session.setAttribute("nomMarque", nomMarque);
		 Page<Detailproduit> pageTuts=  detailproduitRep.listProduit(paging,idMarque);
		 int total = pageTuts.getTotalPages();
		 List<Detailproduit> list = new ArrayList<Detailproduit>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		//session.setAttribute("listsocieteinput", societeRep.findByidMarque(idMarque));
		System.out.println("ty o"+societeRep.findAll());
		session.setAttribute("listProduit", list);
		
			return new ModelAndView("crud/produit");
			
		}
	
	@PostMapping(path = "/RechercheProduit")
	  public ModelAndView RechercheMarque(Model m ,HttpSession session,
			  
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		int idMarque = (int)session.getAttribute("idMarque");
		 Pageable paging = PageRequest.of(page, size);		 
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			System.out.print("tyyy"+nom);
			System.out.print("tyyy"+detailproduitRep.listProduitRec(nom,idMarque));
			session.setAttribute("listProduit", detailproduitRep.listProduitRec(nom,idMarque));
			m.addAttribute("retour", noms);
			return new ModelAndView("crud/produit");			
		}
	
	
	@GetMapping(path = "/pageProduit")
	  public ModelAndView listProduit(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		int idMarque = (int)session.getAttribute("idMarque");
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailproduit> pageTuts=  detailproduitRep.listProduit(paging,idMarque);
		 int total = pageTuts.getTotalPages();
		 List<Detailproduit> list = new ArrayList<Detailproduit>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1);
		
		session.setAttribute("listProduit", list);
		
			return new ModelAndView("crud/produit");
			
		}
	@PostMapping(path = "/AjouterProduit")
	  public ModelAndView Ajouter(HttpSession session,Model m,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name = "description",required = false) String description,
		
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
			int idMarque = (int)session.getAttribute("idMarque");
			produitRep.save(new Produit(nom,"active",description,idMarque));
			
			 Pageable paging = PageRequest.of(page, size);		 
			 Page<Detailproduit> pageTuts=  detailproduitRep.listProduit(paging,idMarque);
			 int total = pageTuts.getTotalPages();
			 List<Detailproduit> list = new ArrayList<Detailproduit>();
			 list =  pageTuts.getContent();
			m.addAttribute("total",total);  
			m.addAttribute("current",page+1); 
			session.setAttribute("listProduit", list);	
			
			return new ModelAndView("crud/produit");			
		}
	
	@GetMapping(path = "/DesactiveProduit")
	  public ModelAndView DesactiveProduit(HttpSession session,  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		Produit q = new Produit();
		int idMarque = (int)session.getAttribute("idMarque");
		q = produitRep.getById(Integer.parseInt(id));
		q.setEtat("desactive");
		produitRep.save(q);			
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailproduit> pageTuts=  detailproduitRep.listProduit(paging,idMarque);
		 int total = pageTuts.getTotalPages();
		 List<Detailproduit> list = new ArrayList<Detailproduit>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listProduit", list);
		
			return new ModelAndView("crud/produit");			
		}
	
	@GetMapping(path = "/ActiveProduit")
	  public ModelAndView ActiveProduit(HttpSession session,  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		Produit q = new Produit();
		int idMarque = (int)session.getAttribute("idMarque");
		q = produitRep.getById(Integer.parseInt(id));
		q.setEtat("active");
		produitRep.save(q);			
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailproduit> pageTuts=  detailproduitRep.listProduit(paging,idMarque);
		 int total = pageTuts.getTotalPages();
		 List<Detailproduit> list = new ArrayList<Detailproduit>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listProduit", list);
		
			return new ModelAndView("crud/produit");			
		}
	
	@PostMapping(path = "/UpdateProduit")
	  public ModelAndView UpdateProduit(HttpSession session,Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name = "description",required = false) String description,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		
		Produit q = new Produit();
		int idMarque = (int)session.getAttribute("idMarque");
		q = produitRep.getById(Integer.parseInt(id));
		q.setDescription(description);
		q.setNom(nom);
		produitRep.save(q);		

		Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailproduit> pageTuts=  detailproduitRep.listProduit(paging,idMarque);
		 int total = pageTuts.getTotalPages();
		 List<Detailproduit> list = new ArrayList<Detailproduit>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listProduit", list);
			return new ModelAndView("crud/produit");			
		}
}
