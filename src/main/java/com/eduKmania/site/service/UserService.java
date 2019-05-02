package com.eduKmania.site.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduKmania.site.exception.EmailExistsException;
import com.eduKmania.site.model.DemandeApprenant;
import com.eduKmania.site.model.appuser.Role;
import com.eduKmania.site.model.appuser.UserRole;
import com.eduKmania.site.model.appuser.Users;
import com.eduKmania.site.model.appuser.VerificationToken;
import com.eduKmania.site.repository.DemandeRepetiteurRepository;
import com.eduKmania.site.repository.RoleRepository;
import com.eduKmania.site.repository.UserRepository;
import com.eduKmania.site.repository.UserRoleRepository;
import com.eduKmania.site.repository.VerificationTokenRepository;

@Service
@Transactional
public class UserService {
  
    @Autowired
    private VerificationTokenRepository tokenRepository;
	
	@Autowired
	DemandeRepetiteurRepository demandeRepetiteurRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	VerificationToken verificationToken;
	Users user;
	
	@Autowired
    private VerificationTokenRepository verificationTokenRepository;
	
	//Cryptage de mot de passe
	public static String encrytePassword(String password) {
		        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		        return encoder.encode(password);
	} 
	
	//Enregistrement d'un nouveau un compte
    public Users registerNewUserAccountApprenant(Optional<DemandeApprenant> demandeApprenant) throws EmailExistsException {
		  
	    if (emailExist(demandeApprenant.get().getEmail())) {
	        throw new EmailExistsException
	          ("Cette adresse email est deja utilisée par un autre compte : " + demandeApprenant.get().getEmail());
	    }
	    Users user = new Users();
	    
	    String password = "password";
	    user.setUserName(demandeApprenant.get().getEmail());
	    user.setDemandeApprenant(demandeApprenant);
	    user.setPassword(encrytePassword(password));
	    
	    Role role = roleRepository.findByRoleName("ROLE_APPRENANT");
	    
	    UserRole userRole = new UserRole();
	    
	    userRole.setRole(role);
	    userRole.setUser(user);
	    userRoleRepository.save(userRole);
	    
	    return userRepository.save(user);
	}

    //Creation d'un nouveau Token de verification
    public void createVerification(String email){
        Users users = userRepository.findByUserName(email);
        
        if (users != null) {
        	user = users;
        }         
        
        List<VerificationToken> verificationTokens = verificationTokenRepository.findByUsers(user);
    
        if (verificationTokens.isEmpty()) {
            verificationToken = new VerificationToken();
            verificationToken.setUser(user);
            verificationTokenRepository.save(verificationToken);
        } else {
            verificationToken = verificationTokens.get(0);
        }
    }
    
    public String verifyEmail(String token){
        
		List<VerificationToken> verificationTokens = verificationTokenRepository.findByToken(token);
	    
		if (verificationTokens.isEmpty()) {
	            return "Token invalide.";
	        }

	    VerificationToken verificationToken = verificationTokens.get(0);
	    
	    if (verificationToken.getExpiredDateTime().isBefore(LocalDateTime.now())) {
	            return "Token expiré.";
	        }

	        verificationToken.setConfirmedDateTime(LocalDateTime.now());
	        verificationToken.setStatus(VerificationToken.STATUS_VERIFIED);
	        verificationToken.getUser().setEnabled(true);;
	        verificationTokenRepository.save(verificationToken);

	        return "Votre adresse email a été vérifiée avec succès";
	    }

    //Verification d'adresse email existante
    private boolean emailExist(String email) {
        Users user = userRepository.findByUserName(email);
        if (user != null) {
            return true;
        }
        return false;
    }
     
   //Obtenir un utilisateur par Token
    public Users getUser(String verificationToken) {
        Users user = tokenRepository.findByToken(verificationToken).get(0).getUser();
        return user;
    }
     
    //obtenir une clé de verification
    public List<VerificationToken> getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
}