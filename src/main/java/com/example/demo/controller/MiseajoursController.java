package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Detaildn;
import com.example.demo.repository.DetaildnRep;

@RestController
public class MiseajoursController {

@Autowired	
DetaildnRep detaildenrep; 

@GetMapping(path = "/miseajours")
public ResponseEntity<List<Detaildn>> MiseajoursDn(){
	
	List<Detaildn> list = detaildenrep.findAll();
	
	return ResponseEntity.ok(list);
}


}
