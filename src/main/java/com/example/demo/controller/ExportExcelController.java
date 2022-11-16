package com.example.demo.controller;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.ExportExcel;
import com.example.demo.model.Categorie;
import com.example.demo.repository.CategorieRep;

 
@Controller
public class ExportExcelController {
	
	
	 
	    @Autowired
	    private CategorieRep service;
	     
	     
	    @GetMapping("/users/export/excel")
	    public void exportToExcel(HttpServletResponse response) throws IOException {
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	         
	        List<Categorie> listUsers = service.findAll();
	         
	        ExportExcel excelExporter = new ExportExcel(listUsers);
	         
	        excelExporter.export(response);    
	      
	 
	}
}
