package com.sip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.entities.Provider;
import com.sip.repositories.ProviderRepository;

@Controller
@RequestMapping("/provider")
public class ProviderController {
	
	@Autowired   //equivalent  ProviderRepository pr = new ProviderRepository();
	//IOC, injection de dependance
	ProviderRepository providerRepository;
	
	@RequestMapping("/add")
	@ResponseBody
	public String add()
	{
		Provider provider = new Provider("HP","USA","hp@hotmail");
		//CampSpringApplication c = new CampSpringApplication();
		//c.providerRepository.save(provider);
		Provider inserted = providerRepository.save(provider);
		return "<h2>Added Provider : "+inserted +"</h2>";
	}
	
	

}
