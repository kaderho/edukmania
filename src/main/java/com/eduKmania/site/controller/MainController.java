package com.eduKmania.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.eduKmania.site.model.DemandeApprenant;
import com.eduKmania.site.model.DemandeRepetiteur;

@Controller
public class MainController {

	private static final String namePage1 = "Soutien pédagique";
	private static final String namePage2 = "Trouver un répétiteur";
	private static final String namePage3 = "Devenir un repetiteur";
	
	@GetMapping("/")
	public String home(Model model) {
		
		model.addAttribute("title", namePage1);
		
		return "index";
	}
	
	@GetMapping("/demandes")
	public String trouveUnRepetiteur(Model model) {
		
		model.addAttribute("title", namePage2);
		model.addAttribute("demandeApprenant", new DemandeApprenant());
		
		return "trouver_repetiteur";
	}
	
	@GetMapping("/demande")
	public String DevenirUnRepetiteur(Model model) {
		
		model.addAttribute("title", namePage3);
		model.addAttribute("demandeRepetiteur", new DemandeRepetiteur());
		
		return "devenir_repetiteur";
	}

}
