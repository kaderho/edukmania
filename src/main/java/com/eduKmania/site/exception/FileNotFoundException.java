package com.eduKmania.site.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Notez l'utilisation d'annotation @ResponseStatus dans la classe d'exception ci-dessus. 
 * Cela entraînera Spring Boot à répondre avec le code d'état HTTP spécifié chaque fois
 *  que cette exception est levée à partir de votre contrôleur.
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {
	
	public FileNotFoundException(String message) {
	        super(message);
	}

	public FileNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	}

}
