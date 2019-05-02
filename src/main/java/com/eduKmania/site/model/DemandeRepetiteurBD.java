package com.eduKmania.site.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


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

	@Entity
	@Table(name = "demandesRepetiteurs")
	@EntityListeners(AuditingEntityListener.class)
	@JsonIgnoreProperties(value = {"dateCreation"}, allowGetters = true)
	public class DemandeRepetiteurBD {

		/*
		 *  @Id L'annotation est utilisée pour définir la clé primaire.
		 *    
		 *  @GeneratedValue L'annotation est utilisée pour définir la stratégie de génération 
		 *  de clé primaire. Dans le cas ci-dessus, nous avons déclaré que la clé primaire 
		 *  était un Auto Incrementchamp.  
		 */
		@Id
		@GeneratedValue(generator = "uuid")
	    @GenericGenerator(name = "uuid", strategy = "uuid2")
		@Column(name = "id_dmde_repetiteur")
		private String id;
		
		private String genre;
		
		private String prenom;
	
		private String nom;
		
		private String telephone;
		
		private String adresse;
		
		private String email;
		
		private String specialite;
		
		private String fileName;
		
		private String status;
		
		/*
		 *  @Column Une annotation est utilisée pour définir les propriétés de la colonne qui
		 *  sera mappée sur le champ annoté. Vous pouvez définir plusieurs propriétés telles
		 *  que nom, longueur, nullable, modifiable, etc.
		 *  
		 *  @Temporal l'annotation est utilisée avec java.util.Dateet java.util.Calendarclasses. 
		 *  Il convertit les valeurs de date et d'heure de Java Object en type de base de données 
		 *  compatible, et inversement.
		 */
		@Column(nullable = false, updatable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    @CreatedDate
		private Date dateCreation;

		public DemandeRepetiteurBD() {
			// TODO Auto-generated constructor stub
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
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
		
		public Date getDateCreation() {
			return dateCreation;
		}

		public void setDateCreation(Date dateCreation) {
			this.dateCreation = dateCreation;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}	
	}

