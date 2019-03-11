package com.eduKmania.site.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eduKmania.site.model.DemandeRepetiteur;
import com.eduKmania.site.repository.DemandeRepetiteurRepository;


/*
 *	L' @Controller annotation est utilisée pour définir un contrôleur et l' @ResponseBody 
 *	annotation est utilisée pour indiquer que la valeur de retour d'une méthode 
 *	doit être utilisée comme corps de réponse de la demande.
 */
@Controller
public class DemandeRepetiteurController {

	@Autowired
	DemandeRepetiteurRepository demandeRepetiteurRepository;
	

	/*
	 * L' @RequestBodyannotation est utilisée pour lier le corps de la demande à un paramètre de méthode.

		L'annotation @Valid s'assure que le corps de la demande est valide. 
		Rappelez-vous, nous avions marqué le titre et le contenu de la demande avec des @NotBlankannotations 
		dans le Notemodèle.
	 */
	// Create a new Note
	@PostMapping("/devenir-un-repetiteur")
	public String createDemandeApprenant(Model model,
			@Valid @ModelAttribute("demandeRepetiteur") DemandeRepetiteur demandeRepetiteur ,
			BindingResult result, RedirectAttributes redirectAttribute) {
	    
		if (result.hasErrors()) {
			model.addAttribute("message", "Cette adresse est déjà utilisée.");
            return "devenir_repetiteur";
        }
		else {
			Collection<DemandeRepetiteur> demandeInDb = demandeRepetiteurRepository.findAll();
			
			for (DemandeRepetiteur x : demandeInDb) {
				
				if(x.getEmail().equals(demandeRepetiteur.getEmail())) {
					
					return "devenir_repetiteur";
				}
			}
		}
		 try {
	         
			 demandeRepetiteurRepository.save(demandeRepetiteur);
	      }
	      // Other error!!
	      catch (Exception e) {
	         
	    	 // model.addAttribute("errorMessage", "Error: " + e.getMessage());
	    	  
	    	  return "devenir_repetiteur";
	      }
		
		return "Validation";
	}
	
}
