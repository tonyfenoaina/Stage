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
import com.example.demo.model.DetailQuestion;
import com.example.demo.model.Question;
import com.example.demo.repository.CibleRepository;
import com.example.demo.repository.DetailQuestionRep;
import com.example.demo.repository.QuestionRep;

/**
 * QuestionController
 */
@Controller
public class QuestionController {
@Autowired
CibleRepository cibleRepository;

@Autowired
QuestionRep questionRep;

@Autowired
DetailQuestionRep detailQuestionrQuestionRep;

@GetMapping(path = "/detailquestion")
public ModelAndView detailquestion(HttpSession session,
Model m,
@RequestParam(name="page" ,defaultValue = "0") int page,
@RequestParam(name="size", defaultValue = "6") int size,
@RequestParam(name="page1" ,defaultValue = "0") int page1,
@RequestParam(name="iddegustation" ,defaultValue = "1") int iddegustation
){
session.setAttribute("iddegustation",iddegustation);	
iddegustation = (int)session.getAttribute("iddegustation");


//cible a ajouter
Pageable paging = PageRequest.of(page, size);	 
Page<Cible> pageTuts=  cibleRepository.listCible(paging);
int total = pageTuts.getTotalPages();
List<Cible> list = new ArrayList<Cible>();
list =  pageTuts.getContent();
m.addAttribute("total",total);  
m.addAttribute("current",page+1); 
session.setAttribute("listCible", list);	 

//question
Pageable paging1 = PageRequest.of(page1, size);
Page<DetailQuestion> listproduit = detailQuestionrQuestionRep.listQuestionparIddegustation(paging1,iddegustation);
System.out.println("etoooo");	
int total1 = listproduit.getTotalPages();
List<DetailQuestion> list1 = new ArrayList<DetailQuestion>();
list1 =  listproduit.getContent();
m.addAttribute("total1",total1);  
m.addAttribute("current1",page1+1);
session.setAttribute("listquestion", list1);
System.out.println("etooooooooo");

return new ModelAndView("crud/degustation/detailquestion");			
}

@PostMapping(path = "/AjouterQuestion")
	  public ModelAndView Ajouter(Model m,HttpSession session,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
			  @RequestParam(name = "question",required = false) String question,
			  @RequestParam(name = "idcible",required = false) int idcible) {
			int iddegustation = (int)session.getAttribute("iddegustation"); 
			
			questionRep.save(new Question(question,idcible,iddegustation));
			
			Pageable paging = PageRequest.of(page, size);	 
                Page<Cible> pageTuts=  cibleRepository.listCible(paging);
                int total = pageTuts.getTotalPages();
                List<Cible> list = new ArrayList<Cible>();
                list =  pageTuts.getContent();
                m.addAttribute("total",total);  
                m.addAttribute("current",page+1); 
                session.setAttribute("listCible", list);	 

                //question
                Pageable paging1 = PageRequest.of(page1, size);
                Page<DetailQuestion> listproduit = detailQuestionrQuestionRep.listQuestionparIddegustation(paging1,iddegustation);
                System.out.println("etoooo");	
                int total1 = listproduit.getTotalPages();
                List<DetailQuestion> list1 = new ArrayList<DetailQuestion>();
                list1 =  listproduit.getContent();
                m.addAttribute("total1",total1);  
                m.addAttribute("current1",page1+1);
                session.setAttribute("listquestion", list1);
                System.out.println("etooooooooo");
		
			return new ModelAndView("crud/degustation/detailquestion");			
		}

        @PostMapping(path = "/DeleteQuestion")
	  public ModelAndView DeleteQuestion(Model m,HttpSession session,
			  @RequestParam(name="page" ,defaultValue = "0") int page,
		      @RequestParam(name="size", defaultValue = "6") int size,
		      @RequestParam(name="page1" ,defaultValue = "0") int page1,
		      @RequestParam(name="size1", defaultValue = "6") int size1,
			  @RequestParam(name = "id",required = false) int id) {
			int iddegustation = (int)session.getAttribute("iddegustation"); 
			
			try {
				questionRep.deleteById(id);	
			}catch(Exception e) {
				
			}			
			Pageable paging = PageRequest.of(page, size);	 
                Page<Cible> pageTuts=  cibleRepository.listCible(paging);
                int total = pageTuts.getTotalPages();
                List<Cible> list = new ArrayList<Cible>();
                list =  pageTuts.getContent();
                m.addAttribute("total",total);  
                m.addAttribute("current",page+1); 
                session.setAttribute("listCible", list);	 
                //question
                Pageable paging1 = PageRequest.of(page1, size);
                Page<DetailQuestion> listproduit = detailQuestionrQuestionRep.listQuestionparIddegustation(paging1,iddegustation);
                System.out.println("etoooo");	
                int total1 = listproduit.getTotalPages();
                List<DetailQuestion> list1 = new ArrayList<DetailQuestion>();
                list1 =  listproduit.getContent();
                m.addAttribute("total1",total1);  
                m.addAttribute("current1",page1+1);
                session.setAttribute("listquestion", list1);
                System.out.println("etooooooooo");
			return new ModelAndView("crud/degustation/detailquestion");			
		}


    
}