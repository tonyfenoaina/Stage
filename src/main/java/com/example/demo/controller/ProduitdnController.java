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
import com.example.demo.model.Detailproduitdn;
import com.example.demo.model.Produitdn;
import com.example.demo.repository.DetailProduitRep;
import com.example.demo.repository.DetailmarqueRep;
import com.example.demo.repository.DetailproduitdnRep;
import com.example.demo.repository.ProduitdnRep;

@Controller
public class ProduitdnController {
	
	@Autowired
	ProduitdnRep produitdnRep;
	
	@Autowired
	DetailmarqueRep detailmarqueRep;
	
	@Autowired
	DetailproduitdnRep detailproduitdnRep;
	
	@Autowired
	 DetailProduitRep detailproduitRep;
	
	
	@GetMapping(path = "/paginationMarquedn")
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
		
		int iddn = (int)session.getAttribute("iddn"); 
		 Pageable paging1 = PageRequest.of(page1, size1);
		 Page<Detailproduitdn> listproduit = detailproduitdnRep.listDetailproduitdn(paging1, iddn);	
		 int total1 = listproduit.getTotalPages();
		 List<Detailproduitdn> list1 = new ArrayList<Detailproduitdn>();
		 list1 =  listproduit.getContent();
		 m.addAttribute("total1",total1);  
		 m.addAttribute("current1",page1+1);
		 session.setAttribute("listproduitdn", list1);
		
			return new ModelAndView("crud/dn/produitDn");
			
		}
	
	@PostMapping(path = "/AjouterMarquedn")
	  public ModelAndView Ajouter(Model m,HttpSession session,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
			  @RequestParam(name = "idmarque",required = false) int idmarque) {
			int iddn = (int)session.getAttribute("iddn"); 
			int idcategorie = (int)session.getAttribute("idcategorie");
			produitdnRep.save(new Produitdn(idmarque,iddn));
			
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
			 Page<Detailproduitdn> listproduit = detailproduitdnRep.listDetailproduitdn(paging1, iddn);	
			 int total1 = listproduit.getTotalPages();
			 List<Detailproduitdn> list1 = new ArrayList<Detailproduitdn>();
			 list1 =  listproduit.getContent();
			 m.addAttribute("total1",total1);  
			 m.addAttribute("current1",page1+1);
			 session.setAttribute("listproduitdn", list1);
			 
		
			return new ModelAndView("crud/dn/produitDn");			
		}
	
	
	@GetMapping(path = "/DeleteMarquedn")
	  public ModelAndView DeleteMarquedn(Model m,HttpSession session,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
			  @RequestParam(name = "id",required = false) int id) {
			int iddn = (int)session.getAttribute("iddn"); 
			int idcategorie = (int)session.getAttribute("idcategorie");
			
			try {
				produitdnRep.deleteById(id);	
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
			 Page<Detailproduitdn> listproduit = detailproduitdnRep.listDetailproduitdn(paging1, iddn);	
			 int total1 = listproduit.getTotalPages();
			 List<Detailproduitdn> list1 = new ArrayList<Detailproduitdn>();
			 list1 =  listproduit.getContent();
			 m.addAttribute("total1",total1);  
			 m.addAttribute("current1",page1+1);
			 session.setAttribute("listproduitdn", list1);
			 
			
			return new ModelAndView("crud/dn/produitDn");	
					
		}
	
	 @GetMapping(path = "/produitDn")
	  public ModelAndView produitDn(HttpSession session,
			  Model m,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
		      @RequestParam(name="iddn", required= false) int iddn,
		      @RequestParam(name="idcategorie", required= false) int idcategorie
			){
		 session.setAttribute("iddn",iddn);	
		 session.setAttribute("idcategorie", idcategorie);
		 idcategorie = (int)session.getAttribute("idcategorie");
		 iddn = (int)session.getAttribute("iddn");
		 
		 
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
		 Page<Detailproduitdn> listproduit = detailproduitdnRep.listDetailproduitdn(paging1, iddn);	
		 int total1 = listproduit.getTotalPages();
		 List<Detailproduitdn> list1 = new ArrayList<Detailproduitdn>();
		 list1 =  listproduit.getContent();
		 m.addAttribute("total1",total1);  
		 m.addAttribute("current1",page1+1);
		 session.setAttribute("listproduitdn", list1);
		 
		 return new ModelAndView("crud/dn/produitDn");
			
		}
	 
	 @PostMapping(path = "/RechercheMarquedn")
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
			
			int iddn = (int)session.getAttribute("iddn"); 
			 Pageable paging1 = PageRequest.of(page1, size1);
			 Page<Detailproduitdn> listproduit = detailproduitdnRep.listDetailproduitdn(paging1, iddn);	
			 int total1 = listproduit.getTotalPages();
			 List<Detailproduitdn> list1 = new ArrayList<Detailproduitdn>();
			 list1 =  listproduit.getContent();
			 m.addAttribute("total1",total1);  
			 m.addAttribute("current1",page1+1);
			 session.setAttribute("listproduitdn", list1);
			 
			return new ModelAndView("crud/dn/produitDn");			
		}
	 
	
	 @GetMapping(path = "/PageproduitDn")
	  public ModelAndView PageproduitDn(HttpSession session,
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
		 
		 //produit dn
		int iddn = (int)session.getAttribute("iddn");
		 Pageable paging1 = PageRequest.of(page1, size1);
		 Page<Detailproduitdn> listproduit = detailproduitdnRep.listDetailproduitdn(paging1, iddn);	
		 int total1 = listproduit.getTotalPages();
		 List<Detailproduitdn> list1 = new ArrayList<Detailproduitdn>();
		 list1 =  listproduit.getContent();
		 m.addAttribute("total1",total1);  
		 m.addAttribute("current1",page1+1);
		 session.setAttribute("listproduitdn", list1);
		 
		 return new ModelAndView("crud/dn/AjouterproduitDn");
			
		}
	 
	
	 
	 @PostMapping(path = "/RechercheProduitdn")
	  public ModelAndView RechercheProduitdn(Model m ,HttpSession session,			  
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
			
			int iddn = (int)session.getAttribute("iddn"); 
			 Pageable paging1 = PageRequest.of(page1, size1);
			 Page<Detailproduitdn> listproduit = detailproduitdnRep.listDetailproduitdn(paging1, iddn);	
			 int total1 = listproduit.getTotalPages();
			 List<Detailproduitdn> list1 = new ArrayList<Detailproduitdn>();
			 list1 =  listproduit.getContent();
			 m.addAttribute("total1",total1);  
			 m.addAttribute("current1",page1+1);
			 session.setAttribute("listproduitdn", list1);
			return new ModelAndView("crud/dn/AjouterproduitDn");			
		}
	 
	
	 @PostMapping(path = "/AjouterProduitdn")
	  public ModelAndView AjouterProduitdn(Model m,HttpSession session,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
			  @RequestParam(name = "idproduit",required = false) int idproduit) {
			int iddn = (int)session.getAttribute("iddn"); 
			
			int idmarque = (int)session.getAttribute("idmarque");
			produitdnRep.save(new Produitdn(idmarque,idproduit,iddn));
			
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
			 Page<Detailproduitdn> listproduit = detailproduitdnRep.listDetailproduitdn(paging1, iddn);	
			 int total1 = listproduit.getTotalPages();
			 List<Detailproduitdn> list1 = new ArrayList<Detailproduitdn>();
			 list1 =  listproduit.getContent();
			 m.addAttribute("total1",total1);  
			 m.addAttribute("current1",page1+1);
			 session.setAttribute("listproduitdn", list1);
			 
		
			return new ModelAndView("crud/dn/AjouterproduitDn");			
		}
	
	 @GetMapping(path = "/DeleteProduitdn")
	  public ModelAndView DeleteProduitdn(Model m,HttpSession session,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
			  @RequestParam(name = "id",required = false) int id) {
			int iddn = (int)session.getAttribute("iddn"); 
			
			try {
				produitdnRep.deleteById(id);	
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
			 Page<Detailproduitdn> listproduit = detailproduitdnRep.listDetailproduitdn(paging1, iddn);	
			 int total1 = listproduit.getTotalPages();
			 List<Detailproduitdn> list1 = new ArrayList<Detailproduitdn>();
			 list1 =  listproduit.getContent();
			 m.addAttribute("total1",total1);  
			 m.addAttribute("current1",page1+1);
			 session.setAttribute("listproduitdn", list1);
			 
			
			return new ModelAndView("crud/dn/AjouterproduitDn");	
					
		}
	 
	 @GetMapping(path = "/paginationProduitdn")
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
		
		int iddn = (int)session.getAttribute("iddn"); 
		 Pageable paging1 = PageRequest.of(page1, size1);
		 Page<Detailproduitdn> listproduit = detailproduitdnRep.listDetailproduitdn(paging1, iddn);	
		 int total1 = listproduit.getTotalPages();
		 List<Detailproduitdn> list1 = new ArrayList<Detailproduitdn>();
		 list1 =  listproduit.getContent();
		 m.addAttribute("total1",total1);  
		 m.addAttribute("current1",page1+1);
		 session.setAttribute("listproduitdn", list1);
		
			return new ModelAndView("crud/dn/AjouterproduitDn");
			
		}
	

}
