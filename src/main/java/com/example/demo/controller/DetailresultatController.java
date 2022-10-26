package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.repository.Detail_resultat_dnRep;
import com.example.demo.service.DetailresultatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DetailresultatController {
    @Autowired
    Detail_resultat_dnRep detail_resultat_dnRep;

    @Autowired
    DetailresultatService detailresultatService;

    @GetMapping(value="/test")
    public String hello() {
       ArrayList valiny = new ArrayList<>(); 
      valiny = detailresultatService.getStatistiqueparmoisparmarque(5, "2022-10");
        return valiny.toString();
    }
    
      
}
