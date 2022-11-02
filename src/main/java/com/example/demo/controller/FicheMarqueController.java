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
import com.example.demo.repository.DetailmarqueRep;

@Controller
public class FicheMarqueController {
    
    @Autowired
    DetailmarqueRep marquerep;
    
    @GetMapping(value="/marque_list_fiche")
    public ModelAndView marque_list(Model m,HttpSession session,
    @RequestParam(name="page" ,defaultValue = "0") int page,
    @RequestParam(name="size", defaultValue = "6") int size) {
    Pageable paging = PageRequest.of(page, size); 
        Page<Detailmarque> pageTuts=  marquerep.findAll(paging);
        int total = pageTuts.getTotalPages();   
        m.addAttribute("total",total);  
         m.addAttribute("current",page+1);
         List<Detailmarque> list = new ArrayList<Detailmarque>();  
         list =  pageTuts.getContent();
         session.setAttribute("listMarque", list);        
        return new ModelAndView("fiche/marque");
    }
    @PostMapping(path = "/RechercheMarquefiche")
      public ModelAndView RechercheMarque(Model m ,HttpSession session,
              @RequestParam(name = "nom",required = false) String nom,
              @RequestParam(name="page" ,defaultValue = "0") int page,
              @RequestParam(name="size", defaultValue = "6") int size) {    
         Pageable paging = PageRequest.of(page, size);       
        m.addAttribute("total",1);  
        m.addAttribute("current",page+1); 
            String noms = nom;
            nom = "%"+nom+"%";
            session.setAttribute("listMarque", marquerep.rechercheEvolution(nom));
            m.addAttribute("retour", noms);
            return new ModelAndView("fiche/marque");            
        }


}
