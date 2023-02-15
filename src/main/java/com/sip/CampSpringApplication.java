package com.sip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CampSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampSpringApplication.class, args);
		System.out.println("Couche Data");
	}

}
