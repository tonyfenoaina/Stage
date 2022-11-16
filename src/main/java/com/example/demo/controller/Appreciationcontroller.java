package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repository.AppreciationRep;


@Controller
public class Appreciationcontroller {
    @Autowired 
    AppreciationRep appreciationRep;
    @GetMapping(value="/appreciation")
    public ModelAndView appreciation(@RequestParam(name = "id") int idproduit,HttpSession session,
    Model m) {
        session.setAttribute("idproduit", idproduit);
        idproduit = (int)session.getAttribute("idproduit");
        session.setAttribute("statlist", appreciationRep.StatAppreciationparProduit(idproduit));
        return new ModelAndView("resultat/degustation/Appreciation");
    }
    
}
