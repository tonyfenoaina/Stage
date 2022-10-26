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

import com.example.demo.model.Cible;
import com.example.demo.repository.CibleRepository;

@Controller
public class CibleController {
	
	@Autowired
	CibleRepository cibleRepository;
	
	@GetMapping(path = "/cible")
	  public ModelAndView Cible(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		 Pageable paging = PageRequest.of(page, size);
		 
		 Page<Cible> pageTuts=  cibleRepository.listCible(paging);
		 int total = pageTuts.getTotalPages();
		 List<Cible> listCible = new ArrayList<Cible>();
		 listCible =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listCible", listCible);
		
			return new ModelAndView("crud/degustation/Cible");
			
		}
	
	@GetMapping(path = "/pageCible")
	  public ModelAndView listQuartier(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Cible> pageTuts=  cibleRepository.listCible(paging);
		 int total = pageTuts.getTotalPages();
		 List<Cible> listCible = new ArrayList<Cible>();
		 listCible =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listCible", listCible);
		
			return new ModelAndView("crud/degustation/Cible");
			
		}
	
	@PostMapping(path = "/AjouterCible")
	  public ModelAndView Ajouter(Model m,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
				
			 cibleRepository.save(new Cible(nom,"active"));
			
			 Pageable paging = PageRequest.of(page, size);		 
			 Page<Cible> pageTuts=  cibleRepository.listCible(paging);
			 int total = pageTuts.getTotalPages();
			 List<Cible> listCible = new ArrayList<Cible>();
			 listCible =  pageTuts.getContent();
			m.addAttribute("total",total);  
			m.addAttribute("current",page+1); 
			
			session.setAttribute("listCible", listCible);			
			return new ModelAndView("crud/degustation/Cible");			
		}
	
	@PostMapping(path = "/RechercheCible")
	  public ModelAndView RechercheCible(Model m ,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "2") int size) {	
			
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Cible> pageTuts=  cibleRepository.listCible(paging);
		 int total = pageTuts.getTotalPages();
		 List<Cible> listCible = new ArrayList<Cible>();
		 listCible =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listCible", cibleRepository.listCibleRec(nom));
			m.addAttribute("retour", noms);
			return new ModelAndView("crud/degustation/Cible");			
		}
	
	@GetMapping(path = "/DesactiveCible")
	  public ModelAndView DesactiveCible(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {	
		Cible q = new Cible();
		q = cibleRepository.getById(Integer.parseInt(id));
		q.setEtat("desactive");
		cibleRepository.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Cible> pageTuts=  cibleRepository.listCible(paging);
		 int total = pageTuts.getTotalPages();
		 List<Cible> listCible = new ArrayList<Cible>();
		 listCible =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listCible", listCible);
	//	session.setAttribute("listCible", cibleRepository.listCible());
			return new ModelAndView("crud/degustation/Cible");			
		}
	
	@GetMapping(path = "/ActiveCible")
	  public ModelAndView ActiveCible(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {	
		Cible q = new Cible();
		q = cibleRepository.getById(Integer.parseInt(id));
		q.setEtat("active");
		cibleRepository.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Cible> pageTuts=  cibleRepository.listCible(paging);
		 int total = pageTuts.getTotalPages();
		 List<Cible> listCible = new ArrayList<Cible>();
		 listCible =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listCible", listCible);
	//	session.setAttribute("listCible", cibleRepository.listCible());
			return new ModelAndView("crud/degustation/Cible");			
		}
	
	
	
	@PostMapping(path = "/UpdateCible")
	  public ModelAndView UpdateQuartier(HttpSession session,Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name = "nom",required = false) String nom,
			 
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		
		Cible q = new Cible();
		q = cibleRepository.getById(Integer.parseInt(id));
		q.setNom(nom);
		
		cibleRepository.save(q);		
		
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Cible> pageTuts=  cibleRepository.listCible(paging);
		 int total = pageTuts.getTotalPages();
		 List<Cible> listCible = new ArrayList<Cible>();
		 listCible =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listCible", listCible);
		
		
		//session.setAttribute("listCible", cibleRepository.listCible());
			return new ModelAndView("crud/degustation/Cible");			
		}
	

}
