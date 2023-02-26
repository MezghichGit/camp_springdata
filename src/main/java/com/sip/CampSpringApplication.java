package com.sip;

import com.sip.controllers.ArticleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sip.entities.Provider;
import com.sip.repositories.ProviderRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class CampSpringApplication {


	
	public static void main(String[] args) throws IOException{
		SpringApplication.run(CampSpringApplication.class, args);
		//new File(ArticleController.uploadDirectory).mkdir();
		Path path = Paths.get(ArticleController.uploadDirectory);
		try{
			Files.createDirectory(path);
		}
		catch(IOException ex){

		}

		//System.out.println("Couche Data");
		//Provider p1 = new Provider();
	   // Provider p2 = new Provider();
		
		 	
	}

}
