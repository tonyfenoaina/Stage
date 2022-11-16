package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javafx.application.Application;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ProjetSpringApplication extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(ProjetSpringApplication.class, args);
	}

}
