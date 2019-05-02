package com.eduKmania.site.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.eduKmania.site.model.DemandeApprenant;
import com.eduKmania.site.model.DemandeRepetiteur;
import com.eduKmania.site.service.UserService;

@Controller
public class MainController {

	private static final String namePage1 = "Soutien pédagique";
	private static final String namePage2 = "Trouver un répétiteur";
	private static final String namePage3 = "Devenir un répétiteur";
	private static final String namePage4 = "Tableau de bord - Admin";
	
	@GetMapping("/")
	public String home(Model model) {
		
		model.addAttribute("title", namePage1);
		
		return "index";
	}
	
	  public static String encrytePassword(String password) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        return encoder.encode(password);
	    }
	
	@GetMapping("/login")
	public String login(Model model) {
		
		//model.addAttribute("title", namePage1);
		
		UserService s = null;
		 String password = "123";
		String pass = s.encrytePassword(password);
		
		System.out.println(pass);
		return "login";
	}
	
	@GetMapping("/apprenant")
	public String apprenant(Model model) {
		
		//model.addAttribute("title", namePage1);
		
		return "/utilisateurs/apprenant/index";
	}
	
	@GetMapping("/demandes/trouver-un-repetiteur")
	public String trouveUnRepetiteur(Model model) {
		
		model.addAttribute("title", namePage2);
		model.addAttribute("demandeApprenant", new DemandeApprenant());
		
		return "/demandes/trouver_repetiteur";
	}
	
	@GetMapping("/demandes/devenir-un-repetiteur")
	public String DevenirUnRepetiteur(Model model) {
		
		model.addAttribute("title", namePage3);
		 model.addAttribute("demandeRepetiteur", new DemandeRepetiteur());
		
		return "/demandes/devenir_repetiteur";
	}

}
