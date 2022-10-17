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

import com.example.demo.model.ChoixCible;

import com.example.demo.repository.ChoixCibleRep;
import com.example.demo.repository.ValeurChoixRep;

@Controller
public class ChoixCibleController {
	
	@Autowired
	ChoixCibleRep choixCibleRep;

	@Autowired
	ValeurChoixRep valeurChoixRep;
	
	@GetMapping(path = "/choixcible")
	public ModelAndView ChoixCible(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		 Pageable paging = PageRequest.of(page, size);
		 
		 Page<ChoixCible> pageTuts=  choixCibleRep.listChoixCible(paging);
		 int total = pageTuts.getTotalPages();
		 List<ChoixCible> listChoixCible = new ArrayList<ChoixCible>();
		 listChoixCible =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listChoixCible", listChoixCible);
		session.setAttribute("listValeur", valeurChoixRep.findAll());
		
			return new ModelAndView("crud/ChoixCible");	
			
		}

	@GetMapping(path = "/pageChoixCible")
	public ModelAndView listQuartier(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<ChoixCible> pageTuts=  choixCibleRep.listChoixCible(paging);
		 int total = pageTuts.getTotalPages();
		 List<ChoixCible> listChoixCible = new ArrayList<ChoixCible>();
		 listChoixCible =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listChoixCible", listChoixCible);
		
			return new ModelAndView("crud/degustation/ChoixCible");
			
		}

	@PostMapping(path = "/AjouterChoixCible")
	public ModelAndView Ajouter(Model m,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		
			choixCibleRep.save(new ChoixCible(nom,"active"));
			 Pageable paging = PageRequest.of(page, size);		 
			 Page<ChoixCible> pageTuts=  choixCibleRep.listChoixCible(paging);
			 int total = pageTuts.getTotalPages();
			 List<ChoixCible> listChoixCible = new ArrayList<ChoixCible>();
			 listChoixCible =  pageTuts.getContent();
			m.addAttribute("total",total);  
			m.addAttribute("current",page+1); 
			
			session.setAttribute("listChoixCible", listChoixCible);			
			return new ModelAndView("crud/degustation/ChoixCible");			
		}

	@PostMapping(path = "/RechercheChoixCible")
	public ModelAndView RechercheChoixCible(Model m ,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "2") int size) {	
			
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<ChoixCible> pageTuts=  choixCibleRep.listChoixCible(paging);
		 int total = pageTuts.getTotalPages();
		 List<ChoixCible> listChoixCible = new ArrayList<ChoixCible>();
		 listChoixCible =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listChoixCible", choixCibleRep.listChoixCibleRec(nom));
			m.addAttribute("retour", noms);
			return new ModelAndView("crud/degustation/ChoixCible");			
		}

	@GetMapping(path = "/DesactiveChoixCible")
	public ModelAndView DesactiveChoixCible(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {	
		ChoixCible q = new ChoixCible();
		q = choixCibleRep.getById(Integer.parseInt(id));
		q.setEtat("desactive");
		choixCibleRep.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<ChoixCible> pageTuts=  choixCibleRep.listChoixCible(paging);
		 int total = pageTuts.getTotalPages();
		 List<ChoixCible> listChoixCible = new ArrayList<ChoixCible>();
		 listChoixCible =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listChoixCible", listChoixCible);
//		session.setAttribute("listChoixCible", choixCibleRep.listChoixCible());
			return new ModelAndView("crud/degustation/ChoixCible");			
		}

	@GetMapping(path = "/ActiveChoixCible")
	public ModelAndView ActiveChoixCible(HttpSession session,
			  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {	
		ChoixCible q = new ChoixCible();
		q = choixCibleRep.getById(Integer.parseInt(id));
		q.setEtat("active");
		choixCibleRep.save(q);
		Pageable paging = PageRequest.of(page, size);		 
		 Page<ChoixCible> pageTuts=  choixCibleRep.listChoixCible(paging);
		 int total = pageTuts.getTotalPages();
		 List<ChoixCible> listChoixCible = new ArrayList<ChoixCible>();
		 listChoixCible =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listChoixCible", listChoixCible);
//		session.setAttribute("listChoixCible", choixCibleRep.listChoixCible());
			return new ModelAndView("crud/degustation/ChoixCible");			
		}



	@PostMapping(path = "/UpdateChoixCible")
	public ModelAndView UpdateQuartier(HttpSession session,Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name = "nom",required = false) String nom,
			 
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size) {
		
		ChoixCible q = new ChoixCible();
		q = choixCibleRep.getById(Integer.parseInt(id));
		q.setNom(nom);
		
		choixCibleRep.save(q);		
		
		Pageable paging = PageRequest.of(page, size);		 
		 Page<ChoixCible> pageTuts=  choixCibleRep.listChoixCible(paging);
		 int total = pageTuts.getTotalPages();
		 List<ChoixCible> listChoixCible = new ArrayList<ChoixCible>();
		 listChoixCible =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listChoixCible", listChoixCible);
		
		
		//session.setAttribute("listChoixCible", choixCibleRep.listChoixCible());
			return new ModelAndView("crud/degustation/ChoixCible");			
		}

}
