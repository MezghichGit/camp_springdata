package com.sip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sip.entities.Provider;
import com.sip.repositories.ProviderRepository;

@SpringBootApplication
public class CampSpringApplication {


	
	public static void main(String[] args) {
		SpringApplication.run(CampSpringApplication.class, args);
		System.out.println("Couche Data");
		
	}

}
