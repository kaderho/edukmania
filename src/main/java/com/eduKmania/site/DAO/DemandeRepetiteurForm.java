package com.eduKmania.site.DAO;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;


/*
 * une @Entity annotation. Il est utilisé pour marquer la classe en tant 
 * que classe Java persistante.
 * 
 * @Table Une annotation est utilisée pour fournir les détails de la table
 *  sur laquelle cette entité sera mappée.
 *   
 *  @JsonIgnoreProperties L'annotation est une annotation de Jackson. Spring Boot utilise 
 *  Jackson pour la sérialisation et la désérialisation des objets Java vers et depuis JSON.
 *  
 */ 
@SuppressWarnings("deprecation")

public class DemandeRepetiteurForm {

	/*
	 *  @Id L'annotation est utilisée pour définir la clé primaire.
	 *    
	 *  @GeneratedValue L'annotation est utilisée pour définir la stratégie de génération 
	 *  de clé primaire. Dans le cas ci-dessus, nous avons déclaré que la clé primaire 
	 *  était un Auto Incrementchamp.  
	 */
	
	private Long id;
	
	@NotEmpty(message = "Ce champ est obligatoire.")
	private String genre;
	
	@Length(min = 2, message = "Ce champ doit contenir au moins 2 caracteres.")
	@NotBlank(message = "Ce champ est obligatoire.")
	private String prenom;
	
	/*
	 *  @NotBlank L'annotation est utilisée pour valider que le 
	 *  champ annoté est not nullvide ou vide.
	 */
	@Length(min = 2, message = "Ce champ doit contenir au moins 2 caracteres.")
	@NotBlank(message = "Ce champ est obligatoire.")
	private String nom;
	
	@NotNull(message = "Ce champ est obligatoire.")
	@Digits(integer = 9, fraction = 0, message = "Numéro de téléphone inconnu.")
	private String telephone;
	
	@NotBlank(message = "Ce champ est obligatoire.")
	private String adresse;
	
	@NotBlank(message = "Ce champ est obligatoire.")
	@Email(message = "Adresse Email invalide.")
	private String email;
	
	@NotBlank(message = "Ce champ est obligatoire.")
	private String specialite;
	
    //@NotEmpty(message = "Le téléchargement de votre CV est obligatoire.")
   // @MultipartConfig(maxFileSize = 5)
    private MultipartFile files;

	
	/*
	 *  @Column Une annotation est utilisée pour définir les propriétés de la colonne qui
	 *  sera mappée sur le champ annoté. Vous pouvez définir plusieurs propriétés telles
	 *  que nom, longueur, nullable, modifiable, etc.
	 *  
	 *  @Temporal l'annotation est utilisée avec java.util.Dateet java.util.Calendarclasses. 
	 *  Il convertit les valeurs de date et d'heure de Java Object en type de base de données 
	 *  compatible, et inversement.
	 */
	private Date dateCreation;

	public DemandeRepetiteurForm() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	
	public MultipartFile getFiles() {
		return files;
	}

	public void setFiles(MultipartFile files) {
		this.files = files;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}	
}
