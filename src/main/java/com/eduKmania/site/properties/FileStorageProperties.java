package com.eduKmania.site.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * Spring Boot a une fonction géniale appelée à l' @ConfigurationPropertiesaide qui vous 
 * permet de lier automatiquement les propriétés définies dans le application.propertiesfichier 
 * à une classe POJO.
 * 
 * L' @ConfigurationProperties(prefix = "file")annotation effectue son travail au démarrage
 * de l'application et lie toutes les propriétés avec le préfixe fileaux champs correspondants 
 * de la classe POJO.
 */
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
}
