package com.eduKmania.site.model.appuser;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eduKmania.site.model.DemandeApprenant;
import com.eduKmania.site.util.PasswordMatches;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@EntityListeners(AuditingEntityListener.class)
@PasswordMatches
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
@Table(name = "user",
		uniqueConstraints = {@UniqueConstraint(name = "APP_USER_UK", columnNames = "user_name")})
public class Users {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;
 
    @Column(name = "user_name", length = 36, nullable = false)
    private String userName;
 
    @Column(name = "password", length = 128, nullable = false)
    private String password;
    
    @Column(name = "confirm_password", length = 128, nullable = false)
    private String confirmPassword;
    
    @Column(name = "enabled", length = 1, nullable = false)
    private boolean enabled;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created;
    
    @Column(nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date modified;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dmde_apprenant", nullable = false)
    private Optional<DemandeApprenant> demandeApprenant;
    
    @OneToOne(mappedBy = "users")
    private VerificationToken verificationToken;
    
    public VerificationToken getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(VerificationToken verificationToken) {
		this.verificationToken = verificationToken;
	}

    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    
    public Users() {
    	
    	String password = "password";
    	this.enabled = false;
    	this.confirmPassword = encrytePassword(password);
    }
    
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public Set<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Optional<DemandeApprenant> getDemandeApprenant() {
		return demandeApprenant;
	}

	public void setDemandeApprenant(Optional<DemandeApprenant> demandeApprenant) {
		this.demandeApprenant = demandeApprenant;
	}
}
