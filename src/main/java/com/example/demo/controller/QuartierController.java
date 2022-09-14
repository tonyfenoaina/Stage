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

import com.example.demo.model.Detailquartier;
import com.example.demo.model.Quartier;
import com.example.demo.model.Ville;
import com.example.demo.repository.DetailquartierRep;
import com.example.demo.repository.QuartierRep;
import com.example.demo.repository.VilleRep;

@Controller
public class QuartierController {
	@Autowired
	QuartierRep quartierRep;
	
	@Autowired
	VilleRep villeRep;
	
	@Autowired
	DetailquartierRep detailquartierRep;
	
	@GetMapping(path = "/quartier")
	  public ModelAndView quartier(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		 Pageable paging = PageRequest.of(page, size);
		 
		 Page<Detailquartier> pageTuts=  detailquartierRep.listQuartier(paging);
		 int total = pageTuts.getTotalPages();
		 List<Detailquartier> list = new ArrayList<Detailquartier>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listvilleinput", villeRep.findAll());
		System.out.println("ty o"+villeRep.findAll());
		session.setAttribute("listQuartier", list);
		
			return new ModelAndView("crud/quartier");
			
		}
	
	@PostMapping(path = "/RechercheQuartier")
	  public ModelAndView RechercheQuartier(Model m ,HttpSession session,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
			
		 Pageable paging = PageRequest.of(page, size);		 
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			System.out.print("tyyy"+nom);
			System.out.print("tyyy"+detailquartierRep.listQuartierRec(nom));
			session.setAttribute("listQuartier", detailquartierRep.listQuartierRec(nom));
			m.addAttribute("retour", noms);
			return new ModelAndView("crud/quartier");			
		}
	
	
	@GetMapping(path = "/pageQuartier")
	  public ModelAndView listQuartier(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailquartier> pageTuts=  detailquartierRep.listQuartier(paging);
		 int total = pageTuts.getTotalPages();
		 List<Detailquartier> list = new ArrayList<Detailquartier>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1);
		
		session.setAttribute("listQuartier", list);
		
			return new ModelAndView("crud/quartier");
			
		}
	@PostMapping(path = "/AjouterQuartier")
	  public ModelAndView Ajouter(HttpSession session,Model m,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name = "idville",required = false) int id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		
			quartierRep.save(new Quartier(nom,"active",id));
			
			 Pageable paging = PageRequest.of(page, size);		 
			 Page<Detailquartier> pageTuts=  detailquartierRep.listQuartier(paging);
			 int total = pageTuts.getTotalPages();
			 List<Detailquartier> list = new ArrayList<Detailquartier>();
			 list =  pageTuts.getContent();
			m.addAttribute("total",total);  
			m.addAttribute("current",page+1); 
			session.setAttribute("listQuartier", list);	
			
			return new ModelAndView("crud/quartier");			
		}
	
	@GetMapping(path = "/DesactiveQuartier")
	  public ModelAndView DesactiveQuartier(HttpSession session,  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		Quartier q = new Quartier();
		q = quartierRep.getById(Integer.parseInt(id));
		q.setEtat("desactive");
		quartierRep.save(q);			
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailquartier> pageTuts=  detailquartierRep.listQuartier(paging);
		 int total = pageTuts.getTotalPages();
		 List<Detailquartier> list = new ArrayList<Detailquartier>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listQuartier", list);
		
			return new ModelAndView("crud/quartier");			
		}
	
	@GetMapping(path = "/ActiveQuartier")
	  public ModelAndView ActiveQuartier(HttpSession session,  Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {	
		Quartier q = new Quartier();
		q = quartierRep.getById(Integer.parseInt(id));
		q.setEtat("active");
		quartierRep.save(q);			
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailquartier> pageTuts=  detailquartierRep.listQuartier(paging);
		 int total = pageTuts.getTotalPages();
		 List<Detailquartier> list = new ArrayList<Detailquartier>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 			
			session.setAttribute("listQuartier", list);
		
			return new ModelAndView("crud/quartier");			
		}
	
	@PostMapping(path = "/UpdateQuartier")
	  public ModelAndView UpdateQuartier(HttpSession session,Model m,
			  @RequestParam(name = "id",required = false) String id,
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "10") int size) {
		
		Quartier q = new Quartier();
		q = quartierRep.getById(Integer.parseInt(id));
		q.setNom(nom);
		quartierRep.save(q);		
		Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailquartier> pageTuts=  detailquartierRep.listQuartier(paging);
		 int total = pageTuts.getTotalPages();
		 List<Detailquartier> list = new ArrayList<Detailquartier>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listQuartier", list);
			return new ModelAndView("crud/quartier");			
		}

}
