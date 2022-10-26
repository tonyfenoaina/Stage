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
import com.example.demo.model.Detailproduit;
import com.example.demo.model.Detailproduitdegustation;
import com.example.demo.model.ProduitDegustation;
import com.example.demo.repository.DetailProduitRep;
import com.example.demo.repository.DetailmarqueRep;
import com.example.demo.repository.DetailproduitdegustationRep;
import com.example.demo.repository.ProduitdegustationRep;

@Controller
public class ProduitDegustationController {
	
	@Autowired
	ProduitdegustationRep produitDegustationRep;
	
	@Autowired
	DetailmarqueRep detailmarqueRep;
	
	@Autowired
	DetailproduitdegustationRep detailproduitDegustationRep;
	
	@Autowired
	 DetailProduitRep detailproduitRep;
	
	
	@GetMapping(path = "/paginationMarquedegustation")
	  public ModelAndView listMarque(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1) {
		
		int idCateg = (int)session.getAttribute("idcategorie");
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailmarque> pageTuts=  detailmarqueRep.listMarque(paging,idCateg);
		 int total = pageTuts.getTotalPages();
		 List<Detailmarque> list = new ArrayList<Detailmarque>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1);		
		session.setAttribute("listMarque", list);		
		int iddegustation = (int)session.getAttribute("iddegustation"); 
		 Pageable paging1 = PageRequest.of(page1, size1);
		 Page<Detailproduitdegustation> listproduit = detailproduitDegustationRep.listDetailproduitDegustation(paging1, iddegustation);	
		 int total1 = listproduit.getTotalPages();
		 List<Detailproduitdegustation> list1 = new ArrayList<Detailproduitdegustation>();
		 list1 =  listproduit.getContent();
		 m.addAttribute("total1",total1);  
		 m.addAttribute("current1",page1+1);
		 session.setAttribute("listproduitDegustation", list1);
		
			return new ModelAndView("crud/degustationproduitDegustation");
			
		}
	
	@PostMapping(path = "/AjouterMarquedegustation")
	  public ModelAndView Ajouter(Model m,HttpSession session,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
			  @RequestParam(name = "idmarque",required = false) int idmarque,
			  @RequestParam(name = "numero",required = false) int numero) {
			int iddegustation = (int)session.getAttribute("iddegustation"); 
			int idcategorie = (int)session.getAttribute("idcategorie");
			produitDegustationRep.save(new ProduitDegustation(idmarque,iddegustation,numero));
			
			Pageable paging = PageRequest.of(page, size);	 
			 Page<Detailmarque> pageTuts=  detailmarqueRep.listMarque(paging,idcategorie);
			 int total = pageTuts.getTotalPages();
			 List<Detailmarque> list = new ArrayList<Detailmarque>();
			 list =  pageTuts.getContent();
			 m.addAttribute("total",total);  
			 m.addAttribute("current",page+1); 
			 session.setAttribute("listMarque", list);
			 
			 //produit
			 Pageable paging1 = PageRequest.of(page1, size1);
			 Page<Detailproduitdegustation> listproduit = detailproduitDegustationRep.listDetailproduitDegustation(paging1, iddegustation);	
			 int total1 = listproduit.getTotalPages();
			 List<Detailproduitdegustation> list1 = new ArrayList<Detailproduitdegustation>();
			 list1 =  listproduit.getContent();
			 m.addAttribute("total1",total1);  
			 m.addAttribute("current1",page1+1);
			 session.setAttribute("listproduitDegustation", list1);
			 
		
			return new ModelAndView("crud/degustation/produitDegustation");			
		}
	
	
	@GetMapping(path = "/DeleteMarquedegustation")
	  public ModelAndView DeleteMarquedegustation(Model m,HttpSession session,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
			  @RequestParam(name = "id",required = false) int id) {
			int iddegustation = (int)session.getAttribute("iddegustation"); 
			int idcategorie = (int)session.getAttribute("idcategorie");
			
			try {
				produitDegustationRep.deleteById(id);	
			}catch(Exception e) {
				
			}
			Pageable paging = PageRequest.of(page, size);	 
			 Page<Detailmarque> pageTuts=  detailmarqueRep.listMarque(paging,idcategorie);
			 int total = pageTuts.getTotalPages();
			 List<Detailmarque> list = new ArrayList<Detailmarque>();
			 list =  pageTuts.getContent();
			 m.addAttribute("total",total);  
			 m.addAttribute("current",page+1); 
			 session.setAttribute("listMarque", list);
			 
			 //produit
			 Pageable paging1 = PageRequest.of(page1, size1);
			 Page<Detailproduitdegustation> listproduit = detailproduitDegustationRep.listDetailproduitDegustation(paging1, iddegustation);	
			 int total1 = listproduit.getTotalPages();
			 List<Detailproduitdegustation> list1 = new ArrayList<Detailproduitdegustation>();
			 list1 =  listproduit.getContent();
			 m.addAttribute("total1",total1);  
			 m.addAttribute("current1",page1+1);
			 session.setAttribute("listproduitDegustation", list1);
			 
			
			return new ModelAndView("crud/degustation/produitDegustation");	
					
		}
	
	 @GetMapping(path = "/produitDegustation")
	  public ModelAndView produitDegustation(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
		      @RequestParam(name="iddegustation", required= false) int iddegustation,
		      @RequestParam(name="idcategorie", required= false) int idcategorie
			){
		 session.setAttribute("iddegustation",iddegustation);	
		 session.setAttribute("idcategorie", idcategorie);
		 idcategorie = (int)session.getAttribute("idcategorie");
		 iddegustation = (int)session.getAttribute("iddegustation");
		 
		 
		 //marque a ajouter
		 Pageable paging = PageRequest.of(page, size);	 
		 Page<Detailmarque> pageTuts=  detailmarqueRep.listMarque(paging,idcategorie);
		 int total = pageTuts.getTotalPages();
		 List<Detailmarque> list = new ArrayList<Detailmarque>();
		 list =  pageTuts.getContent();
		 m.addAttribute("total",total);  
		 m.addAttribute("current",page+1); 
		 session.setAttribute("listMarque", list);	 
	 
		 //produit
		 Pageable paging1 = PageRequest.of(page1, size1);
		
		 Page<Detailproduitdegustation> listproduit = detailproduitDegustationRep.listDetailproduitDegustation(paging1, iddegustation);
		 System.out.println("etoooo");	
		 int total1 = listproduit.getTotalPages();
		 List<Detailproduitdegustation> list1 = new ArrayList<Detailproduitdegustation>();
		 list1 =  listproduit.getContent();
		 m.addAttribute("total1",total1);  
		 m.addAttribute("current1",page1+1);
		 session.setAttribute("listproduitDegustation", list1);
		 System.out.println("etooooooooo");
		 
		 return new ModelAndView("crud/degustation/produitDegustation");			
		}
	 
	 @PostMapping(path = "/RechercheMarquedegustation")
	  public ModelAndView RechercheMarque(Model m ,HttpSession session,
			  
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1) {	
		 
		int idcategorie = (int)session.getAttribute("idcategorie");
		 Pageable paging = PageRequest.of(page, size);		 
		m.addAttribute("total",1);  
		m.addAttribute("current",page+1); 
			String noms = nom;
			nom = "%"+nom+"%";
			session.setAttribute("listMarque", detailmarqueRep.listMarqueRec(nom,idcategorie));
			m.addAttribute("retour", noms);
			
			int iddegustation = (int)session.getAttribute("iddegustation"); 
			 Pageable paging1 = PageRequest.of(page1, size1);
			 Page<Detailproduitdegustation> listproduit = detailproduitDegustationRep.listDetailproduitDegustation(paging1, iddegustation);	
			 int total1 = listproduit.getTotalPages();
			 List<Detailproduitdegustation> list1 = new ArrayList<Detailproduitdegustation>();
			 list1 =  listproduit.getContent();
			 m.addAttribute("total1",total1);  
			 m.addAttribute("current1",page1+1);
			 session.setAttribute("listproduitDegustation", list1);
			 
			return new ModelAndView("crud/degustation/produitDegustation");			
		}
	 
	
	 @GetMapping(path = "/PageproduitDegustation")
	  public ModelAndView PageproduitDegustation(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
		      @RequestParam(name = "nom",required = false) String nomMarque,
			  @RequestParam(name="idmarque",required = false) int idMarque
		  
			){
		
		 //produit a ajouter
		 
		 session.setAttribute("idmarque", idMarque);
		 Pageable paging = PageRequest.of(page, size);
		 Page<Detailproduit> pageTuts=  detailproduitRep.listProduit(paging,idMarque);
		 int total = pageTuts.getTotalPages();
		 List<Detailproduit> list = new ArrayList<Detailproduit>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1); 
		session.setAttribute("listProduit", list);
		 
		 //produit degustation
		int iddegustation = (int)session.getAttribute("iddegustation");
		 Pageable paging1 = PageRequest.of(page1, size1);
		 Page<Detailproduitdegustation> listproduit = detailproduitDegustationRep.listDetailproduitDegustation(paging1, iddegustation);	
		 int total1 = listproduit.getTotalPages();
		 List<Detailproduitdegustation> list1 = new ArrayList<Detailproduitdegustation>();
		 list1 =  listproduit.getContent();
		 m.addAttribute("total1",total1);  
		 m.addAttribute("current1",page1+1);
		 session.setAttribute("listproduitDegustation", list1);
		 
		 return new ModelAndView("crud/degustation/AjouterproduitDegustation");
			
		}
	 
	
	 
	 @PostMapping(path = "/RechercheProduitDegustation")
	  public ModelAndView RechercheProduitDegustation(Model m ,HttpSession session,			  
			  @RequestParam(name = "nom",required = false) String nom,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1) {	
		int idMarque = (int)session.getAttribute("idmarque");
		 Pageable paging = PageRequest.of(page, size);		 
			m.addAttribute("total",1);  
			m.addAttribute("current",page+1); 
				String noms = nom;
				nom = "%"+nom+"%";
				session.setAttribute("listProduit", detailproduitRep.listProduitRec(nom,idMarque));
				m.addAttribute("retour", noms);
			
			int iddegustation = (int)session.getAttribute("iddegustation"); 
			 Pageable paging1 = PageRequest.of(page1, size1);
			 Page<Detailproduitdegustation> listproduit = detailproduitDegustationRep.listDetailproduitDegustation(paging1, iddegustation);	
			 int total1 = listproduit.getTotalPages();
			 List<Detailproduitdegustation> list1 = new ArrayList<Detailproduitdegustation>();
			 list1 =  listproduit.getContent();
			 m.addAttribute("total1",total1);  
			 m.addAttribute("current1",page1+1);
			 session.setAttribute("listproduitDegustation", list1);
			return new ModelAndView("crud/degustation/AjouterproduitDegustation");			
		}
	 
	
	 @PostMapping(path = "/AjouterProduitDegustation")
	  public ModelAndView AjouterProduitDegustation(Model m,HttpSession session,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
			  @RequestParam(name = "idproduit",required = false) int idproduit) {
			int iddegustation = (int)session.getAttribute("iddegustation"); 
			System.out.println("haha"+iddegustation);
			
			int idmarque = (int)session.getAttribute("idmarque");
			produitDegustationRep.save(new ProduitDegustation(idmarque,idproduit,iddegustation,1));
			
			 Pageable paging = PageRequest.of(page, size);
			 Page<Detailproduit> pageTuts=  detailproduitRep.listProduit(paging,idmarque);
			 int total = pageTuts.getTotalPages();
			 List<Detailproduit> list = new ArrayList<Detailproduit>();
			 list =  pageTuts.getContent();
			m.addAttribute("total",total);  
			m.addAttribute("current",page+1); 
			session.setAttribute("listProduit", list);
			 
			 //produit
			 Pageable paging1 = PageRequest.of(page1, size1);
			 Page<Detailproduitdegustation> listproduit = detailproduitDegustationRep.listDetailproduitDegustation(paging1, iddegustation);	
			 int total1 = listproduit.getTotalPages();
			 List<Detailproduitdegustation> list1 = new ArrayList<Detailproduitdegustation>();
			 list1 =  listproduit.getContent();
			 m.addAttribute("total1",total1);  
			 m.addAttribute("current1",page1+1);
			 session.setAttribute("listproduitDegustation", list1);
			 
		
			return new ModelAndView("crud/degustation/AjouterproduitDegustation");			
		}
	
	 @GetMapping(path = "/DeleteProduitDegustation")
	  public ModelAndView DeleteProduitDegustation(Model m,HttpSession session,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
			  @RequestParam(name = "id",required = false) int id) {
			int iddegustation = (int)session.getAttribute("iddegustation"); 
			
			try {
				produitDegustationRep.deleteById(id);	
			}catch(Exception e) {
				
			}
			int idmarque = (int)session.getAttribute("idmarque");
			 Pageable paging = PageRequest.of(page, size);
			 Page<Detailproduit> pageTuts=  detailproduitRep.listProduit(paging,idmarque);
			 int total = pageTuts.getTotalPages();
			 List<Detailproduit> list = new ArrayList<Detailproduit>();
			 list =  pageTuts.getContent();
			m.addAttribute("total",total);  
			m.addAttribute("current",page+1); 
			session.setAttribute("listProduit", list);
			 
			 //produit
			 Pageable paging1 = PageRequest.of(page1, size1);
			 Page<Detailproduitdegustation> listproduit = detailproduitDegustationRep.listDetailproduitDegustation(paging1, iddegustation);	
			 int total1 = listproduit.getTotalPages();
			 List<Detailproduitdegustation> list1 = new ArrayList<Detailproduitdegustation>();
			 list1 =  listproduit.getContent();
			 m.addAttribute("total1",total1);  
			 m.addAttribute("current1",page1+1);
			 session.setAttribute("listproduitDegustation", list1);
			 
			
			return new ModelAndView("crud/degustation/AjouterproduitDegustation");	
					
		}
	 
	 @GetMapping(path = "/paginationProduitDegustation")
	  public ModelAndView listProduit(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1) {
		int idMarque = (int)session.getAttribute("idmarque");
		 Pageable paging = PageRequest.of(page, size);		 
		 Page<Detailproduit> pageTuts=  detailproduitRep.listProduit(paging,idMarque);
		 int total = pageTuts.getTotalPages();
		 List<Detailproduit> list = new ArrayList<Detailproduit>();
		 list =  pageTuts.getContent();
		m.addAttribute("total",total);  
		m.addAttribute("current",page+1);
		session.setAttribute("listProduit", list);
		
		int iddegustation = (int)session.getAttribute("iddegustation"); 
		 Pageable paging1 = PageRequest.of(page1, size1);
		 Page<Detailproduitdegustation> listproduit = detailproduitDegustationRep.listDetailproduitDegustation(paging1, iddegustation);	
		 int total1 = listproduit.getTotalPages();
		 List<Detailproduitdegustation> list1 = new ArrayList<Detailproduitdegustation>();
		 list1 =  listproduit.getContent();
		 m.addAttribute("total1",total1);  
		 m.addAttribute("current1",page1+1);
		 session.setAttribute("listproduitDegustation", list1);
		
			return new ModelAndView("crud/degustation/AjouterproduitDegustation");
			
		}
	

}
