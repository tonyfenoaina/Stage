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

import com.example.demo.model.Detailmarque;
import com.example.demo.model.Marque;
import com.example.demo.repository.CategorieRep;
import com.example.demo.repository.DetailmarqueRep;
import com.example.demo.repository.MarqueRep;
import com.example.demo.repository.MarqueRep;
import com.example.demo.repository.SocieteRep;
import com.example.demo.repository.VilleRep;

@Controller
public class MarqueController {
	@Autowired
	MarqueRep MarqueRep;
	
	@Autowired
	SocieteRep societeRep;
	
	@Autowired
	CategorieRep categorieRep;
	
	@Autowired
	DetailmarqueRep detailmarqueRep;
	
	
	@GetMapping(path = "/marque")
	  public ModelAndView Marque(HttpSession session,
			  Model m,
			  @RequestParam(name="id",required = false) int idCategorie,
			  @RequestParam(name="nomCategorie",required = false) String nomCategorie,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		session.setAttribute("idCateg", idCategorie);
		 session.setAttribute("nomCategorie", nomCategorie);
		 Pageable paging = PageRequest.of(page, size);
		 
		 Page<Detailmarque> pageTuts=  detailmarqueRep.listMarque(paging,idCategorie);
		 int total = pageTuts.getTotalPages();
		 List<Detailmarque> list = new ArrayList<Detailmarque>();
		 list =  pageTuts.getContent();
		 m.addAttribute("total",total);  
		 m.addAttribute("current",page+1); 
		 session.setAttribute("listsocieteinput", societeRep.findByIdcategorie(idCategorie));
		 System.out.println("ty o"+societeRep.findAll());
		 session.setAttribute("listMarque", list);
		
			return new ModelAndView("crud/marque");
			
		}
	
	@PostMapping(path = "/RechercheMarque")
	  public ModelAndView RechercheMarque(Model m ,HttpSession session,
			  
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		int idCateg = (int)session.getAttribute("idCateg");
		 Pageable paging = PageRequest.of(page, size);		 
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			System.out.print("tyyy"+nom);
			System.out.print("tyyy"+detailmarqueRep.listMarqueRec(nom,idCateg));
			session.setAttribute("listMarque", detailmarqueRep.listMarqueRec(nom,idCateg));
			m.addAttribute("retour", noms);
			return new ModelAndView("crud/marque");			
		}
	
	
	@GetMapping(path = "/pageMarque")
	  public ModelAndView listMarque(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		int idCateg = (int)session.getAttribute("idCateg");
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailmarque> pageTuts=  detailmarqueRep.listMarque(paging,idCateg);
		 int total = pageTuts.getTotalPages();
		 List<Detailmarque> list = new ArrayList<Detailmarque>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1);
		
		session.setAttribute("listMarque", list);
		
			return new ModelAndView("crud/marque");
			
		}
	@PostMapping(path = "/AjouterMarque")
	  public ModelAndView Ajouter(HttpSession session,Model m,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name = "idsociete",required = false) int idsociete,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
			int idCateg = (int)session.getAttribute("idCateg");
			MarqueRep.save(new Marque(nom,"active",idCateg,idsociete));
			
			 Pageable paging = PageRequest.of(page, size);		 
			 Page<Detailmarque> pageTuts=  detailmarqueRep.listMarque(paging,idCateg);
			 int total = pageTuts.getTotalPages();
			 List<Detailmarque> list = new ArrayList<Detailmarque>();
			 list =  pageTuts.getContent();
			m.addAttribute("total",total);  
			m.addAttribute("current",page+1); 
			session.setAttribute("listMarque", list);	
			
			return new ModelAndView("crud/marque");			
		}
	
	@GetMapping(path = "/DesactiveMarque")
	  public ModelAndView DesactiveMarque(HttpSession session,  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		Marque q = new Marque();
		int idCateg = (int)session.getAttribute("idCateg");
		q = MarqueRep.getById(Integer.parseInt(id));
		q.setEtat("desactive");
		MarqueRep.save(q);			
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailmarque> pageTuts=  detailmarqueRep.listMarque(paging,idCateg);
		 int total = pageTuts.getTotalPages();
		 List<Detailmarque> list = new ArrayList<Detailmarque>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listMarque", list);
		
			return new ModelAndView("crud/marque");			
		}
	
	@GetMapping(path = "/ActiveMarque")
	  public ModelAndView ActiveMarque(HttpSession session,  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		Marque q = new Marque();
		int idCateg = (int)session.getAttribute("idCateg");
		q = MarqueRep.getById(Integer.parseInt(id));
		q.setEtat("active");
		MarqueRep.save(q);			
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailmarque> pageTuts=  detailmarqueRep.listMarque(paging,idCateg);
		 int total = pageTuts.getTotalPages();
		 List<Detailmarque> list = new ArrayList<Detailmarque>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listMarque", list);
		
			return new ModelAndView("crud/marque");			
		}
	
	@PostMapping(path = "/UpdateMarque")
	  public ModelAndView UpdateMarque(HttpSession session,Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name = "idsociete",required = false) int idsociete,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		
		Marque q = new Marque();
		int idCateg = (int)session.getAttribute("idCateg");
		q = MarqueRep.getById(Integer.parseInt(id));
		q.setIdsociete(idsociete);
		q.setNom(nom);
		MarqueRep.save(q);		
		
		
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailmarque> pageTuts=  detailmarqueRep.listMarque(paging,idCateg);
		 int total = pageTuts.getTotalPages();
		 List<Detailmarque> list = new ArrayList<Detailmarque>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listMarque", list);
			return new ModelAndView("crud/marque");			
		}
}
