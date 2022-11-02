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

import com.example.demo.model.Emplacement;
import com.example.demo.model.Enqueteur;
import com.example.demo.repository.EnqueteurRep;
import com.example.demo.repository.FicheEnqueteurRep;

@RestController
public class FicheEnqueteurController {
	@Autowired
	EnqueteurRep enqueteurRep;
	
	 @GetMapping(path = "/ficheenqueteur")
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
			return new ModelAndView("fiche/Enqueteur");			
		}	
	@GetMapping(path = "/pageficheEnqueteur")
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
	
			return new ModelAndView("fiche/Enqueteur");	
		}
	@PostMapping(path = "/FicheRechercheEnqueteur")
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
			return new ModelAndView("fiche/Enqueteur");			
		}

		@Autowired
		FicheEnqueteurRep ficheEnqueteurRep;

	@GetMapping(path = "/suivie")
	public ModelAndView suivie(@RequestParam(name = "date",defaultValue = "0") String date, @RequestParam(name = "id",required = false) int id,Model m ,HttpSession session) {		
	long millis=System.currentTimeMillis();      
	java.sql.Date d = new java.sql.Date(millis); 
	session.setAttribute("idenqueteur", id);
	session.setAttribute("date", date);
	if (date.equals("0")) {
		session.setAttribute("date", d);
	}
	String dateres = session.getAttribute("date").toString();
	System.out.println("haha"+ficheEnqueteurRep.listEmplacement(5, "2022-11-11"));	
	int idenqueteur = (int)session.getAttribute("idenqueteur");
	session.setAttribute("emplacement", ficheEnqueteurRep.listEmplacement(idenqueteur,dateres));
	return new ModelAndView("carte");
 }
}
