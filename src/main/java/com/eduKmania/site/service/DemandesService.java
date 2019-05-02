package com.eduKmania.site.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.eduKmania.site.exception.EmailExistsException;
import com.eduKmania.site.exception.FileNotFoundException;
import com.eduKmania.site.model.DemandeApprenant;
import com.eduKmania.site.model.DemandeRepetiteur;
import com.eduKmania.site.model.DemandeRepetiteurBD;
import com.eduKmania.site.model.appuser.Role;
import com.eduKmania.site.model.appuser.Users;
import com.eduKmania.site.model.appuser.UserRole;
import com.eduKmania.site.repository.DemandeApprenantRepository;
import com.eduKmania.site.repository.DemandeRepetiteurRepository;
import com.eduKmania.site.repository.RoleRepository;
import com.eduKmania.site.repository.UserRepository;
import com.eduKmania.site.repository.UserRoleRepository;

@Service
public class DemandesService {

	@Autowired
	private DemandeApprenantRepository demandeApprenantRepository;
	
	@Autowired
	DemandeRepetiteurRepository demandeRepetiteurRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	private String code;
	/*
	 * @Transactional public User registerNewUserAccount(UserDTO accountDto) throws
	 * EmailExistsException {
	 * 
	 * if (emailExist(accountDto.getUserName())) { throw new EmailExistsException(
	 * "There is an account with that email adress: " + accountDto.getUserName()); }
	 * // the rest of the registration operation }
	 */
	 
	 //verification de l'existance de l'adresse email
	public boolean emailExist(String email) {
		List<DemandeRepetiteurBD> user = demandeRepetiteurRepository.findByEmail(email);
	        if (user != null) {
	            return true;
	        }
	        return false;
	    }
	
	//generateur de code;
	public long getMaxCode() {
		
		List <DemandeApprenant> recupDemande = demandeApprenantRepository.findAll();
		
		 	long max = 0;
	        for (DemandeApprenant x: recupDemande) {
	        	
	        	String s = x.getCode().substring(1);
	        	Long code = Long.parseLong(s);
	            
	        	System.out.println(s);
	        	if (code > max) {
	                max = code;
	            }
	        	
	        }
	        return max;

	}
	
	
	/*
	 * LES METHODES LIEES AUX DEMANDES DES APPRENANTS
	 */
	
	//enregistrement de nouveau compte utlisisateur apprenant
	
	
	// Trouver les profs disponibles par la matiere demandée par l'apprenant
	public List<DemandeRepetiteurBD> findBySpecialite(String id){
		
		DemandeApprenant specialite = demandeApprenantRepository.getOne(id);
		
		return demandeRepetiteurRepository.findBySpecialite(specialite.getMatiere());
	}
	
	//Stocker les données des demandes es apprenants dans base de données
	public void storeDemandeApprenant(DemandeApprenant demandeApprenant) {
		
		
		  long codeMax = getMaxCode() +1;
			if(codeMax< 10) {
				
				code = "A00"+codeMax;
			}
			
			else if(codeMax>9 && codeMax<999) {
				code = "A0"+codeMax;
			}
			
			else {
				code = "A"+codeMax;
			}
			
		DemandeApprenant demandeStored = new DemandeApprenant();
		
		demandeStored.setCode(code);
		demandeStored.setAdresse(demandeApprenant.getAdresse());
		demandeStored.setEmail(demandeApprenant.getEmail());
		demandeStored.setMatiere(demandeApprenant.getMatiere());
		demandeStored.setNiveau(demandeApprenant.getNiveau());
		demandeStored.setNom(demandeApprenant.getNom());
		demandeStored.setPrenom(demandeApprenant.getPrenom());
		demandeStored.setSituation(demandeApprenant.getSituation());
		demandeStored.setStatus(demandeApprenant.getStatus());
		demandeStored.setTelephone(demandeApprenant.getTelephone());
		
		demandeApprenantRepository.save(demandeStored);
	}
	
	//Obtenir le nombre total de toutes les nouvelles demandes des apprenants
	public long countDemandesApprenantsNotTreated() {
		long count = demandeApprenantRepository.findByStatus("Non traitée").size();
		return count;
	}
	
	//Obtenir toutes les nouvelles demandes des apprenants qui ne sont pas encore traitées
	public List<DemandeApprenant> findAllDemandesApprenantsNotTreated(){
		
		List<DemandeApprenant> demandesApprenants = demandeApprenantRepository.findByStatus("Non traitée");
		
		return demandesApprenants;
	}
	
	//Obtenir toutes les nouvelles demandes des apprenants qui ne sont pas encore traitées
		public List<DemandeApprenant> findAllDemandesApprenants(){
			
			List<DemandeApprenant> demandesApprenants = demandeApprenantRepository.findAll();
			
			return demandesApprenants;
		}
		
	//Suppression d'une demande
		public DemandeApprenant deleteDemandeApprenant(String id) {
			 
			DemandeApprenant demandeDelete = demandeApprenantRepository.findById(id)
		            .orElseThrow(() -> new FileNotFoundException("Identifiant introuvable"));

		    demandeApprenantRepository.delete(demandeDelete);
		    
		    return demandeDelete;
		}
		
	//Obtenir la demande de l'apprenant par son Id
	public Optional<DemandeApprenant> findById(String id) {
		
		Optional<DemandeApprenant> infoDemande = demandeApprenantRepository.findById(id);
		
		return infoDemande;
	}
	
	/*
	 * LES METHODES LIEES AUX DEMANDES DES REPETITEURS 
	 */
	
	//Obtenir le nombre total de toutes les nouvelles demandes des apprenants
		public long countDemandesRepetiteursNotTreated() {
			long count = demandeRepetiteurRepository.findByStatus("Non traitée").size();
			return count;
		}
		
		//Obtenir toutes les nouvelles demandes des apprenants qui ne sont pas encore traitées
		public List<DemandeRepetiteurBD> findAllDemandesRepetiteursNotTreated(){
			
			List<DemandeRepetiteurBD> demandesRepetiteurs = demandeRepetiteurRepository.findByStatus("Non traitée");
			
			return demandesRepetiteurs;
		}
		
		//Obtenir toutes les nouvelles demandes des apprenants qui ont été traitées
				public List<DemandeRepetiteurBD> findAllDemandesRepetiteurs(){
					
					List<DemandeRepetiteurBD> demandesRepetiteurs = demandeRepetiteurRepository.findByStatus("Non traitée");
					
					return demandesRepetiteurs;
				}
}
