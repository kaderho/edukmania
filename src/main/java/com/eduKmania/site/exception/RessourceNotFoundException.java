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
public class RessourceNotFoundException extends RuntimeException {
	
	private String ressourceName;
    private String fieldName;
    private Object fieldValue;
    
    public RessourceNotFoundException( String ressourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", ressourceName, fieldName, fieldValue));
        this.ressourceName = ressourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

	public String getRessourceName() {
		return ressourceName;
	}

	public void setRessourceName(String ressourceName) {
		this.ressourceName = ressourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
    
    

}
