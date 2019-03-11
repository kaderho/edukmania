package com.eduKmania.site.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.eduKmania.site.exception.RessourceNotFoundException;
import com.eduKmania.site.model.DemandeApprenant;
import com.eduKmania.site.repository.DemandeApprenantRepository;

/*
 *	L' @Controller annotation est utilisée pour définir un contrôleur et l' @ResponseBody 
 *	annotation est utilisée pour indiquer que la valeur de retour d'une méthode 
 *	doit être utilisée comme corps de réponse de la demande.
 */
@Controller
//@RequestMapping("/demandes")
public class DemandeApprenantController {

	private static final String emailExist = "Cette adresse Email est invalide";
	@Autowired
	DemandeApprenantRepository demandeApprenantRepository;
	/* 
	 * La méthode ci-dessus est assez simple. Il appelle la findAll()méthode 
	 * de JpaRepository pour récupérer toutes les notes de la base de données 
	 * et renvoie la liste complète.
	 */
	// Get All Notes
	@GetMapping("/trouver-un-repetiteur")
	public List<DemandeApprenant> getAllDemandeApprenant() {
	    return demandeApprenantRepository.findAll();
	}
	
	/*
	 * L' @RequestBodyannotation est utilisée pour lier le corps de la demande à un paramètre de méthode.

		L'annotation @Valid s'assure que le corps de la demande est valide. 
		Rappelez-vous, nous avions marqué le titre et le contenu de la demande avec des @NotBlankannotations 
		dans le Notemodèle.
	 */
	// Create a new Note
	@PostMapping("/trouver-un-repetiteur")
	public String createDemandeApprenant(Model model,
			@Valid @ModelAttribute("demandeApprenant")DemandeApprenant demandeApprenant ,
			BindingResult result) {
	    
		if (result.hasErrors()) {
            return "trouver_repetiteur";
        }
		else {
			Collection<DemandeApprenant> demandeInDb = demandeApprenantRepository.findAll();
			
			for (DemandeApprenant x : demandeInDb) {
				
				if(x.getEmail().equals(demandeApprenant.getEmail())) {
					model.addAttribute("emailExist", emailExist);
					return "trouver_repetiteur";
				}
			}
		}
		 try {
	         
			 demandeApprenantRepository.save(demandeApprenant);
	      }
	      // Other error!!
	      catch (Exception e) {
	         
	    	  model.addAttribute("errorMessage", "Error: " + e.getMessage());
	    	  
	    	  return "trouver_repetiteur";
	      }
		
		return "Validation";
	}
	
	/*
	 * 
	 * L'annotation @PathVariable, comme son nom l'indique, est utilisée pour lier une 
	 * variable de chemin avec un paramètre de méthode.

		Dans la méthode ci-dessus, nous lançons a ResourceNotFoundException chaque 
		fois que Notel'identifiant donné n'est pas trouvé.

		Spring Boot renvoie alors une erreur 404 Not Found au client 
		(rappelez-vous que nous avions ajouté une 
		@ResponseStatus(value = HttpStatus.NOT_FOUND)annotation à la 
		ResourceNotFoundExceptionclasse).
	 */
	// Get a Single Note
	@GetMapping("/trouver-un-repetiteur/{id}")
	public DemandeApprenant getDemandeApprenantById(@PathVariable(value = "id") Long demandeApprenantId) {
	    return demandeApprenantRepository.findById(demandeApprenantId)
	            .orElseThrow(() -> new RessourceNotFoundException("Demande d'un apprenant", "id", demandeApprenantId));
	}
	
	// Update a Note
	@PutMapping("/trouver-un-repetiteur/{id}")
	public DemandeApprenant updateDemandeApprenant(@PathVariable(value = "id") Long demandeApprenantId,
	                                        @Valid @RequestBody DemandeApprenant demandeApprenantDetails) {

		DemandeApprenant demandeApprenant = demandeApprenantRepository.findById(demandeApprenantId)
	            .orElseThrow(() -> new RessourceNotFoundException("Note", "id", demandeApprenantId));

		demandeApprenant.setPrenom(demandeApprenantDetails.getPrenom());
		demandeApprenant.setNom(demandeApprenantDetails.getNom());
		demandeApprenant.setSituation(demandeApprenantDetails.getSituation());
		demandeApprenant.setAdresse(demandeApprenantDetails.getAdresse());
		demandeApprenant.setEmail(demandeApprenantDetails.getEmail());
		demandeApprenant.setTelephone(demandeApprenantDetails.getTelephone());
		demandeApprenant.setNiveau(demandeApprenantDetails.getNiveau());
		demandeApprenant.setMatiere(demandeApprenantDetails.getMatiere());
		demandeApprenant.setEtat(demandeApprenantDetails.isEtat());

	    DemandeApprenant updatedDemandeApprenant = demandeApprenantRepository.save(demandeApprenant);
	    return updatedDemandeApprenant;
	}
	
	// Delete a Note
	@DeleteMapping("/trouver-un-repetiteur/{id}")
	public ResponseEntity<?> deleteDemandeApprenant(@PathVariable(value = "id") Long demandeApprenantId) {
	    DemandeApprenant demandeApprenant = demandeApprenantRepository.findById(demandeApprenantId)
	            .orElseThrow(() -> new RessourceNotFoundException("Demande d'un apprenant", "id", demandeApprenantId));

	    demandeApprenantRepository.delete(demandeApprenant);

	    return ResponseEntity.ok().build();
	}
}
