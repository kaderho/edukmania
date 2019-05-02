package com.eduKmania.site.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eduKmania.site.model.DemandeApprenant;
import com.eduKmania.site.model.DemandeRepetiteurBD;
import com.eduKmania.site.model.appdata.TutoratForm;
import com.eduKmania.site.model.appuser.Users;
import com.eduKmania.site.repository.DemandeApprenantRepository;
import com.eduKmania.site.repository.UserRepository;
import com.eduKmania.site.service.DemandesService;
import com.eduKmania.site.service.SendingMailService;
import com.eduKmania.site.service.UserService;

@Controller
public class TutoratController {
	
	@Autowired
	DemandesService demandesService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DemandeApprenantRepository demandeApprenantRepository;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	UserService userService;
	
	private SendingMailService SendingEmailService;
	
	@GetMapping("/admin/nos-tutorats/nouveau/{id}")
	public String choixRepetiteur(Model model, 
								@PathVariable(value = "id") String idApprenant) {
		
		List <DemandeRepetiteurBD> repetiteur = demandesService.findBySpecialite(idApprenant);
		
		model.addAttribute("tutoratForm", new TutoratForm());
		model.addAttribute("repetiteurInfo", repetiteur);
		model.addAttribute("idApprenant", idApprenant);
		
		return "/utilisateurs/admin/creation_tutorat2.html";
	}
	
	@PostMapping("/admin/nos-tutorats/nouveau/{idApprenant}")
	public String tutoratPost(Model model, 
			@ModelAttribute("tutoratForm") TutoratForm tutoratForm,
			@Valid @PathVariable(value = "idApprenant") String idApprenant,
			BindingResult result,
			ModelAndView modelAndView) {
		
		if (result.hasErrors()) {
			
			return "/utilisateurs/admin/creation_tutorat2"; 
		}
		
		try {
			Optional<DemandeApprenant> demandeApprenant = demandeApprenantRepository.findById(idApprenant);
			
			if (demandesService.emailExist(demandeApprenant.get().getEmail()));
			{
				model.addAttribute("emailError", "Cette adresse est déja utilisée.");
			}	
			
			Users user = userService.registerNewUserAccountApprenant(demandeApprenant);
			userService.createVerification(demandeApprenant.get().getEmail());
		
			 SimpleMailMessage mailMessage = new SimpleMailMessage();
	            mailMessage.setTo(user.getUserName());
	            mailMessage.setSubject("Confirmation d'inscrimtion");
	            mailMessage.setText("Pour l'activation de votre compte, s'il  vous plaît cliquer sur ce lien : "
	            +"http://localhost:8080/confirm-account?token="+user.getVerificationToken());
	            mailMessage.setFrom("noreply@edukmania.sn");
	            
	            SendingEmailService.sendEmail(mailMessage);
	            
	            modelAndView.addObject("email", user.getUserName());

	            modelAndView.setViewName("successfulRegisteration");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return "/utilisateurs/admin/creation_tutorat2";
		}
		
		return "/utilisateurs/admin/creation_tutorat2";
	}
	
	// Process confirmation link
		@GetMapping("/admin/nos-tutorats/confirm")
		public String showConfirmationPage(Model model, @RequestParam("token") String token) {
				
			String response = userService.verifyEmail(token);
				
			model.addAttribute("responseCheck", response);
		
			return "redirect: /admin/nos-tutorats/enregistrement";		
		}
		
		@GetMapping("/admin/nos-tutorats/enregistrement")
		public String showEnregistrement(Model model) {
			
			return "";
		}
}
