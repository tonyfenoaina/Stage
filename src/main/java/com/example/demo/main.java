package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.controller.DetailresultatController;
import com.example.demo.repository.Detail_resultat_dnRep;
import com.example.demo.service.RotationPrixService;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RotationPrixService rotationPrixService = new RotationPrixService();
		rotationPrixService.test();

	
	}

}
