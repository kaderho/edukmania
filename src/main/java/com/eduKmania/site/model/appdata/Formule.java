package com.eduKmania.site.model.appdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "formules")
public class Formule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_formule;
	
	private String nameFormule;
}
