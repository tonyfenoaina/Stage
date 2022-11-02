package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.repository.DetailquartierRep;
import com.example.demo.repository.QuartierRep;
import com.example.demo.repository.VilleRep;
import com.example.demo.service.RotationPrixService;

@Controller
public class PageController {
	@Autowired
	QuartierRep quartierRep;
	
	
	@Autowired
	DetailquartierRep detailquartierRep;

	@Autowired
	RotationPrixService rotationPrixService;
	
	 @GetMapping(path = "/")
	  public ModelAndView firstPage() {	
		
		rotationPrixService.test();	 	
			return new ModelAndView("login");
		}
	 
	 @PostMapping(path = "/home")
	  public ModelAndView home(@RequestParam(name = "Email",required = false) String Email,@RequestParam(name = "password",required = false) String password) {		 	
			return new ModelAndView("index");
		}
	 
	
	
	
	
}
