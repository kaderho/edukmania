package com.eduKmania.site.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.eduKmania.site.model.DemandeRepetiteur;
import com.eduKmania.site.model.DemandeRepetiteurBD;
import com.eduKmania.site.repository.DemandeRepetiteurRepository;
import com.eduKmania.site.service.DemandesService;
import com.eduKmania.site.service.FileStorageService;

/*
 *	L' @Controller annotation est utilisée pour définir un contrôleur et l' @ResponseBody 
 *	annotation est utilisée pour indiquer que la valeur de retour d'une méthode 
 *	doit être utilisée comme corps de réponse de la demande.
 */
@Controller
public class DemandeRepetiteurController {
	
	@SuppressWarnings("unused")
	private static final String namePage4 = "Informations supplémentaires";
	
	@Autowired
	DemandeRepetiteurRepository demandeRepetiteurRepository;
	
	@Autowired 
	FileStorageService FileStorageService;
	
	@Autowired
	DemandesService demandesService;
		

	/*
	 * L' @RequestBodyannotation est utilisée pour lier le corps de la demande à un paramètre de méthode.

		L'annotation @Valid s'assure que le corps de la demande est valide. 
		Rappelez-vous, nous avions marqué le titre et le contenu de la demande avec des @NotBlankannotations 
		dans le Notemodèle.
	 */
	
	//creer une demande de type répétiteur
	@PostMapping("/demandes/devenir-un-repetiteur")
	public String createDemandeApprenant(Model model,
	 @Valid @ModelAttribute("demandeRepetiteur") DemandeRepetiteur demandeRepetiteur,
	 BindingResult result)
			 {
		
		Pattern pattern;
		Matcher matcher;
		
		if (result.hasErrors()) {
			
			return "/demandes/devenir_repetiteur"; 
		}
		 
		if(demandesService.emailExist(demandeRepetiteur.getEmail())){
			model.addAttribute("message", "Cette adresse est déjà utilisée.");
			return "/demandes/devenir_repetiteur";
		}
		
		 pattern = Pattern.compile("\\d");
		 matcher = pattern.matcher(demandeRepetiteur.getPrenom());
		 	
		 	if (matcher.find()){
		 		
		 		model.addAttribute("message1", "Information invalide.");
				return "/demandes/devenir_repetiteur";
		 	}
		 try {
			 String fileName = FileStorageService.storeFile(demandeRepetiteur.getFiles());
			 
			 DemandeRepetiteurBD newDemande = new DemandeRepetiteurBD();
			 
	            newDemande.setGenre(demandeRepetiteur.getGenre());
	            newDemande.setPrenom(demandeRepetiteur.getPrenom());
	            newDemande.setNom(demandeRepetiteur.getNom());
	            newDemande.setAdresse(demandeRepetiteur.getAdresse());
	            newDemande.setEmail(demandeRepetiteur.getEmail());
	            newDemande.setSpecialite(demandeRepetiteur.getSpecialite());
	            newDemande.setTelephone(demandeRepetiteur.getTelephone());
	            newDemande.setStatus(demandeRepetiteur.getStatus());
	            newDemande.setFileName(fileName);
	        
	            demandeRepetiteurRepository.save(newDemande); 
		 }
		 
	      // Other error!!
	      catch (Exception e) {
	         
	    	 model.addAttribute("errorMessage", "Error: " + e.getMessage());
	    	 System.out.println(e.getMessage());
	    	  return "/demandes/devenir_repetiteur";
	      }
		 return "validation";
	}
	
}
