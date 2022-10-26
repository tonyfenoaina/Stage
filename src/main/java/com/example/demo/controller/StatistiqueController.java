package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.repository.Detail_resultat_dnRep;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class StatistiqueController {
@Autowired
Detail_resultat_dnRep detail_resultat_dnRep;

@GetMapping(value="path")
public ModelAndView getMethodName(Model m ) {
    m.getAttribute("haha");
    
    return new ModelAndView("evolution");
}    

}
