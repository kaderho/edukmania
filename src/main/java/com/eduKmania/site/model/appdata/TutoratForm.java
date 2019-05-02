package com.eduKmania.site.model.appdata;


import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class TutoratForm {

	@NotBlank(message = "Veuillez selectionnez un répétiteur.")
	private String repetiteur;
	
	@NotBlank(message = "Veuillez selectionnez une formule.")
	private String formule;
	
	@NotEmpty(message = "La date début du tutorat est obligatoire.")
	private Date[] date;

	public String getRepetiteur() {
		return repetiteur;
	}

	public void setRepetiteur(String repetiteur) {
		this.repetiteur = repetiteur;
	}

	public String getFormule() {
		return formule;
	}

	public void setFormule(String formule) {
		this.formule = formule;
	}

	public Date[] getDate() {
		return date;
	}

	public void setDate(Date[] date) {
		this.date = date;
	}
}
