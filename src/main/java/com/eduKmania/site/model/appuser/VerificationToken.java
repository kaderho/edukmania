package com.eduKmania.site.model.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/*
 * L' entité VerificationToken doit répondre aux critères suivants:
 *
 *	Il doit renvoyer à l' utilisateur (via une relation unidirectionnelle)
 *	Il sera créé juste après l'inscription
 *	Il expirera dans les 3 jours suivant sa création
 *	A un jeton unique généré de façon aléatoire la valeur
 */

@Entity
public class VerificationToken {

	   public static final String STATUS_PENDING = "PENDING";
	    public static final String STATUS_VERIFIED = "VERIFIED";
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String token;
	    private String status;
	    private LocalDateTime expiredDateTime;
	    private LocalDateTime issuedDateTime;
	    private LocalDateTime confirmedDateTime;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "user_id")
	    private Users users;

	    public VerificationToken(){
	        this.token = UUID.randomUUID().toString();
	        this.issuedDateTime = LocalDateTime.now();
	        this.expiredDateTime = this.issuedDateTime.plusDays(1);
	        this.status = STATUS_PENDING;
	    }
	    
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public LocalDateTime getExpiredDateTime() {
	        return expiredDateTime;
	    }

	    public void setExpiredDateTime(LocalDateTime expiredDateTime) {
	        this.expiredDateTime = expiredDateTime;
	    }

	    public LocalDateTime getIssuedDateTime() {
	        return issuedDateTime;
	    }

	    public void setIssuedDateTime(LocalDateTime issuedDateTime) {
	        this.issuedDateTime = issuedDateTime;
	    }

	    public LocalDateTime getConfirmedDateTime() {
	        return confirmedDateTime;
	    }

	    public void setConfirmedDateTime(LocalDateTime confirmedDateTime) {
	        this.confirmedDateTime = confirmedDateTime;
	    }

	    public Users getUser() {
	        return users;
	    }

	    public void setUser(Users users) {
	        this.users = users;
	    }
}
