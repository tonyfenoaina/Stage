package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ChoixCible;
import com.example.demo.model.Degustation;
import com.example.demo.model.Detailproduitdn;
import com.example.demo.model.Dn;
import com.example.demo.model.Enqueteur;
import com.example.demo.model.Listequestion;
import com.example.demo.repository.ChoixCibleRep;
import com.example.demo.repository.DegustationRep;
import com.example.demo.repository.DetailproduitdnRep;
import com.example.demo.repository.DnRep;
import com.example.demo.repository.EnqueteurRep;
import com.example.demo.repository.ListequestionRep;

@RestController
public class MiseajoursController {

@Autowired	
DnRep dnrep; 

@Autowired	
DegustationRep degustationRep; 

@Autowired
DetailproduitdnRep detailproduitdnRep;

@Autowired
EnqueteurRep enqueteurRep;


@GetMapping(path = "/miseajours_Enqueteurs")
public ResponseEntity<List<Enqueteur>> MiseajoursEnqueteur(){
	List<Enqueteur> list = enqueteurRep.findAll();
	return ResponseEntity.ok(list);
}

@GetMapping(path = "/miseajours_Dn")
public ResponseEntity<List<Dn>> MiseajoursDn(){
	List<Dn> list = dnrep.findAll();
	return ResponseEntity.ok(list);
}

@GetMapping(path = "/miseajours_Degustation")
public ResponseEntity<List<Degustation>> miseajours_Degustation(){
	List<Degustation> list = degustationRep.findAll();
	return ResponseEntity.ok(list);
}

@GetMapping(path = "/miseajours_ProduitDn")
public ResponseEntity<List<Detailproduitdn>> MiseajoursProduitDn(){
	List<Detailproduitdn> list = detailproduitdnRep.findAll();
	return ResponseEntity.ok(list);
}

@GetMapping(path = "/miseajours_Quartier")
public ResponseEntity<List<Detailproduitdn>> miseajours_Quartier(){
	List<Detailproduitdn> list = detailproduitdnRep.findAll();
	return ResponseEntity.ok(list);
}

@Autowired
ListequestionRep listequestionRep;
@GetMapping(path = "/miseajours_Question")
public ResponseEntity<List<Listequestion>> miseajours_Question(){
	List<Listequestion> list = listequestionRep.listquestion();
	return ResponseEntity.ok(list);	
}

@Autowired
ChoixCibleRep choixCibleRep;
@GetMapping(path = "/miseajours_ChoixCible")
public ResponseEntity<List<ChoixCible>> miseajours_ChoixCible(){
	List<ChoixCible> list = choixCibleRep.findAll();
	return ResponseEntity.ok(list);	
}

@PostMapping("/testAjoutEnqueteur")
  public ResponseEntity<Enqueteur> createTutorial(@RequestBody List<Enqueteur> list) {
    try {
		Enqueteur _tutorial;
		for (Enqueteur enquete : list) {
			 _tutorial = enqueteurRep
			.save(enquete);
		} 
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
