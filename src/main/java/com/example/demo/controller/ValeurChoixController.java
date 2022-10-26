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

import com.example.demo.model.ValeurChoix;
import com.example.demo.repository.ValeurChoixRep;

@Controller
public class ValeurChoixController {
	
@Autowired
ValeurChoixRep valeurChoixRep;

@GetMapping(path = "/valeurchoix")
public ModelAndView ValeurChoix(HttpSession session,
		  Model m,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "6") int size) {
	 Pageable paging = PageRequest.of(page, size);
	 
	 Page<ValeurChoix> pageTuts=  valeurChoixRep.listValeurChoix(paging);
	 int total = pageTuts.getTotalPages();
	 List<ValeurChoix> listValeurChoix = new ArrayList<ValeurChoix>();
	 listValeurChoix =  pageTuts.getContent();
	m.addAttribute("total",total);  
	m.addAttribute("current",page+1); 
	session.setAttribute("listValeurChoix", listValeurChoix);
	
		return new ModelAndView("crud/degustation/ValeurChoix");
		
	}

@GetMapping(path = "/pageValeurChoix")
public ModelAndView listQuartier(HttpSession session,
		  Model m,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "6") int size) {
	 Pageable paging = PageRequest.of(page, size);		 
	 Page<ValeurChoix> pageTuts=  valeurChoixRep.listValeurChoix(paging);
	 int total = pageTuts.getTotalPages();
	 List<ValeurChoix> listValeurChoix = new ArrayList<ValeurChoix>();
	 listValeurChoix =  pageTuts.getContent();
	m.addAttribute("total",total);  
	m.addAttribute("current",page+1); 
	session.setAttribute("listValeurChoix", listValeurChoix);
	
		return new ModelAndView("crud/degustation/ValeurChoix");
		
	}

@PostMapping(path = "/AjouterValeurChoix")
public ModelAndView Ajouter(Model m,HttpSession session,
		  @RequestParam(name = "nom",required = false) String nom,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "6") int size) {
	
		valeurChoixRep.save(new ValeurChoix(nom,"active"));
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<ValeurChoix> pageTuts=  valeurChoixRep.listValeurChoix(paging);
		 int total = pageTuts.getTotalPages();
		 List<ValeurChoix> listValeurChoix = new ArrayList<ValeurChoix>();
		 listValeurChoix =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		
		session.setAttribute("listValeurChoix", listValeurChoix);			
		return new ModelAndView("crud/degustation/ValeurChoix");			
	}

@PostMapping(path = "/RechercheValeurChoix")
public ModelAndView RechercheValeurChoix(Model m ,HttpSession session,
		  @RequestParam(name = "nom",required = false) String nom,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "2") int size) {	
		
	 Pageable paging = PageRequest.of(page, size);		 
	 Page<ValeurChoix> pageTuts=  valeurChoixRep.listValeurChoix(paging);
	 int total = pageTuts.getTotalPages();
	 List<ValeurChoix> listValeurChoix = new ArrayList<ValeurChoix>();
	 listValeurChoix =  pageTuts.getContent();
	m.addAttribute("total",1);  
	m.addAttribute("current",page+1); 
		String noms = nom;
		nom = "%"+nom+"%";
		session.setAttribute("listValeurChoix", valeurChoixRep.listValeurChoixRec(nom));
		m.addAttribute("retour", noms);
		return new ModelAndView("crud/degustation/ValeurChoix");			
	}

@GetMapping(path = "/DesactiveValeurChoix")
public ModelAndView DesactiveValeurChoix(HttpSession session,
		  Model m,
		  @RequestParam(name = "id",required = false) String id,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "6") int size) {	
	ValeurChoix q = new ValeurChoix();
	q = valeurChoixRep.getById(Integer.parseInt(id));
	q.setEtat("desactive");
	valeurChoixRep.save(q);
	Pageable paging = PageRequest.of(page, size);		 
	 Page<ValeurChoix> pageTuts=  valeurChoixRep.listValeurChoix(paging);
	 int total = pageTuts.getTotalPages();
	 List<ValeurChoix> listValeurChoix = new ArrayList<ValeurChoix>();
	 listValeurChoix =  pageTuts.getContent();
	m.addAttribute("total",1);  
	m.addAttribute("current",page+1); 			
		session.setAttribute("listValeurChoix", listValeurChoix);
//	session.setAttribute("listValeurChoix", valeurChoixRep.listValeurChoix());
		return new ModelAndView("crud/degustation/ValeurChoix");			
	}

@GetMapping(path = "/ActiveValeurChoix")
public ModelAndView ActiveValeurChoix(HttpSession session,
		  Model m,
		  @RequestParam(name = "id",required = false) String id,
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "6") int size) {	
	ValeurChoix q = new ValeurChoix();
	q = valeurChoixRep.getById(Integer.parseInt(id));
	q.setEtat("active");
	valeurChoixRep.save(q);
	Pageable paging = PageRequest.of(page, size);		 
	 Page<ValeurChoix> pageTuts=  valeurChoixRep.listValeurChoix(paging);
	 int total = pageTuts.getTotalPages();
	 List<ValeurChoix> listValeurChoix = new ArrayList<ValeurChoix>();
	 listValeurChoix =  pageTuts.getContent();
	m.addAttribute("total",1);  
	m.addAttribute("current",page+1); 			
		session.setAttribute("listValeurChoix", listValeurChoix);
//	session.setAttribute("listValeurChoix", valeurChoixRep.listValeurChoix());
		return new ModelAndView("crud/degustation/ValeurChoix");			
	}



@PostMapping(path = "/UpdateValeurChoix")
public ModelAndView UpdateQuartier(HttpSession session,Model m,
		  @RequestParam(name = "id",required = false) String id,
		  @RequestParam(name = "nom",required = false) String nom,
		 
		  @RequestParam(name="page" ,defaultValue = "0") int page,
	      @RequestParam(name="size", defaultValue = "6") int size) {
	
	ValeurChoix q = new ValeurChoix();
	q = valeurChoixRep.getById(Integer.parseInt(id));
	q.setNom(nom);
	
	valeurChoixRep.save(q);		
	
	Pageable paging = PageRequest.of(page, size);		 
	 Page<ValeurChoix> pageTuts=  valeurChoixRep.listValeurChoix(paging);
	 int total = pageTuts.getTotalPages();
	 List<ValeurChoix> listValeurChoix = new ArrayList<ValeurChoix>();
	 listValeurChoix =  pageTuts.getContent();
	m.addAttribute("total",1);  
	m.addAttribute("current",page+1); 
		String noms = nom;
		nom = "%"+nom+"%";
		session.setAttribute("listValeurChoix", listValeurChoix);
	
	
	//session.setAttribute("listValeurChoix", valeurChoixRep.listValeurChoix());
		return new ModelAndView("crud/degustation/ValeurChoix");			
	}

}
