package com.eduKmania.site.model.appdata;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.eduKmania.site.model.DemandeApprenant;
import com.eduKmania.site.model.DemandeRepetiteurBD;

@Entity
@Table(name = "Tutorats", uniqueConstraints = {
		@UniqueConstraint(name = "DEMANDES_UK", columnNames = { "id_dmde_apprenant", 
																"id_dmde_repetiteur" })
})
public class Tutorat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dmde_apprenant", nullable = false)
	private DemandeApprenant demandeApprenant;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dmde_repetiteur", nullable = false)
	private DemandeRepetiteurBD demandeRepetiteurBD;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_formule", nullable = false)
	private Formule formule;
	
	private String status;
	
	private Date dateDebut;
	
	private Date DateCreation;
	
	private Date created;
	
	
}
