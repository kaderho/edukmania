package com.eduKmania.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduKmania.site.model.DemandeRepetiteurBD;

public interface DemandeRepetiteurRepository extends JpaRepository<DemandeRepetiteurBD, String> {

	List<DemandeRepetiteurBD> findBySpecialite(String specialite);
	
	List<DemandeRepetiteurBD> findByStatus(String status);
	
	List<DemandeRepetiteurBD> findByEmail(String email);
}
