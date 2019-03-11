package com.eduKmania.site.DAO;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.eduKmania.site.exception.FileStorageException;
import com.eduKmania.site.model.DemandeRepetiteur;
import com.eduKmania.site.repository.DemandeRepetiteurRepository;


@Service
public class DemandeRepetiteurDAO {

	@Autowired
	DemandeRepetiteurRepository demandeRepetiteurRepository;
	
	public DemandeRepetiteur saveRepetiteur(DemandeRepetiteur demandeRepetiteur,  MultipartFile file) throws IOException {
		
		DemandeRepetiteur newDemande = new DemandeRepetiteur();
		 // Normalize file name
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(fileName.contains("..")) {
			throw new FileStorageException("Le fichier contient des séquences de chemins invalides");
		}
		
		return demandeRepetiteurRepository.save(newDemande);
	}
}
