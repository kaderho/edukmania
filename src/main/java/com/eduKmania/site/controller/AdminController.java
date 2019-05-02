package com.eduKmania.site.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eduKmania.site.model.DemandeApprenant;
import com.eduKmania.site.model.DemandeRepetiteurBD;
import com.eduKmania.site.service.DemandesService;
import com.eduKmania.site.util.WebUtils;

@Controller
public class AdminController {

	@Autowired
	DemandesService demandesService;
	
	private static final String namePage1 = "Toutes les demandes apprenants";
	private static final String namePage2 = "Les demandes apprenants acceptées";
	private static final String namePage3 = "Tableau de bord - Admin";
	private static final String namePage4 = "Création de tutorats";
	private static final String namePage5 = "Informations supplémentaires";
	private static final String namePage6 = "Devenir un répétiteur";
	private static final String namePage7 = "Devenir un répétiteur";
	
	
	//Mappage de la page d'accueil de l'admin
	@GetMapping("/admin")
	public String admin(Model model,  Principal principal) {
		
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		   
	    String userInfo = WebUtils.toString(loginedUser);
	    long countDemandesApprenant = demandesService.countDemandesApprenantsNotTreated();
	    long countDemandesRepetiteur = demandesService.countDemandesRepetiteursNotTreated();
	    long allDemandes = countDemandesApprenant + countDemandesRepetiteur;
	    
	    long countTutoratEnCours = 0;
	    long countUserApprenants = 0;
	    long countUserRepetiteurs = 0;
	    
	    List<DemandeApprenant> listedemandesApprenants = demandesService.findAllDemandesApprenantsNotTreated();
	    List<DemandeRepetiteurBD> listeDemandesRepetiteurs = demandesService.findAllDemandesRepetiteursNotTreated();
	   
	    
		model.addAttribute("title", namePage3);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("countUserApprenants", countUserApprenants);
		model.addAttribute("countUserRepetiteurs", countUserRepetiteurs);
		model.addAttribute("countTutoratEnCours", countTutoratEnCours);
		model.addAttribute("countAllDemandes", allDemandes);
		model.addAttribute("listedemandesApprenants", listedemandesApprenants);
		model.addAttribute("listeDemandesRepetiteurs", listeDemandesRepetiteurs);
		
		return "/utilisateurs/admin/index";
	}
	
	/*
	 * MENU NOS TUTORATS
	 */
	//Mappage de la page création de tutorat
	@GetMapping("/admin/nos-tutorats/creation-tutorat")
	public String creationTutorat(Model model) {
			
			try {
			List<DemandeApprenant> allDemande = demandesService.findAllDemandesApprenantsNotTreated();
			
			List<DemandeRepetiteurBD> allRepetiteur = demandesService.findAllDemandesRepetiteurs();
			long countDemandesApprenant = demandesService.countDemandesApprenantsNotTreated();
			model.addAttribute("countDemandesApprenants", countDemandesApprenant);
			model.addAttribute("allDemande", allDemande);
			model.addAttribute("allRepetiteur", allRepetiteur);
			model.addAttribute("title", namePage4);
			
			return "/utilisateurs/admin/creation_tutorat";
			}
			catch(Exception ex) {
				
				return "/utilisateurs/admin/creation_tutorat";
			}
			
		}
	
	/*
	 * MENU DEMANDES APPRENANTS
	 */
	
	// Delete a demande apprenant
		@GetMapping("/admin/apprenants/toutes-les-demandes/{id}")
		public String deleteDemandeApprenant(@PathVariable(value = "id") String demandeApprenantId,
				final RedirectAttributes redirectAttributes) {
			
			DemandeApprenant deleteDemande = new DemandeApprenant();
		   try {
			   
			   deleteDemande = demandesService.deleteDemandeApprenant(demandeApprenantId);
		   }
		   catch(Exception ex) {
			   
			   ex.printStackTrace();
		   }
		   
		   redirectAttributes.addFlashAttribute("flashDemande", deleteDemande);
		   
		   return "redirect:/admin/apprenants/toutes-les-demandes";
		}
		
		//Mappage de la page toutes les demandes
		@GetMapping("/admin/apprenants/toutes-les-demandes")
		public String toutesDemandesApprenants(Model model) {
			List <DemandeApprenant> listeAlldemandesApprenants = demandesService.findAllDemandesApprenants();
			
			model.addAttribute("title", namePage1);
			model.addAttribute("listeAlldemandes", listeAlldemandesApprenants);
			
			return "/utilisateurs/admin/toutes_demandes";
		}
		
		//Mappage de la page toutes les demandes
		@GetMapping("/admin/apprenants/informations-supplementaires/{id}")
		public String infoDemandesApprenants(Model model, 
					@PathVariable(value = "id") String demandeApprenantId) {
					
			Optional<DemandeApprenant> infoDemande = demandesService.findById(demandeApprenantId);
			
			DemandeApprenant d = infoDemande.get();
					
			model.addAttribute("title", namePage5);
			model.addAttribute("infoDemande", d);
					
			return "/utilisateurs/admin/infoSuppl";
		}
		
		
	
	//Mappage de la page demandes acceptées
	@GetMapping("/admin/apprenants/demandes-acceptees")
	public String demandesAccepteesApprenants(Model model) {
		
		List<DemandeApprenant> listedemandesApprenants = demandesService.findAllDemandesApprenantsNotTreated();
		
		model.addAttribute("listedemandes", listedemandesApprenants);
		model.addAttribute("title", namePage2);
		
		return "/utilisateurs/admin/demandes_acceptees";
	}
	
	//Mappage de la page demande refusées
	@GetMapping("/admin/apprenants/demandes-refusees")
	public String demandesRefuseesApprenants(Model model) {
		
		model.addAttribute("title", namePage2);
		
		return "/utilisateurs/admin/demandes_refusees";
	}
	
	/*
	 * MENU DEMANDES REPETITEURS
	 */
	
	//Mappage de la page toutes les demandes
		@GetMapping("/admin/repetiteurs/toutes-les-demandes")
		public String toutesDemandesRepetiteurs(Model model) {
			
			List <DemandeRepetiteurBD> listeAlldemandesRepetiteurs = demandesService.findAllDemandesRepetiteurs();
			
			model.addAttribute("title", namePage1);
			model.addAttribute("listeAlldemandes", listeAlldemandesRepetiteurs);
			
			return "/utilisateurs/admin/toutes_demandes";
		}
		
		//Mappage de la page demandes acceptées
		@GetMapping("/admin/repetiteurs/demandes-acceptees")
		public String demandesAccepteesRepetiteurs(Model model) {
			
			List<DemandeRepetiteurBD> listedemandesRepetiteurs = demandesService.findAllDemandesRepetiteursNotTreated();
			
			model.addAttribute("listedemandes", listedemandesRepetiteurs);
			model.addAttribute("title", namePage2);
			
			return "/utilisateurs/admin/demandes_acceptees";
		}
	
}
