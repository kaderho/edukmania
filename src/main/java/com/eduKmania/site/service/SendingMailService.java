package com.eduKmania.site.service;

/*
 * Une fois que la création de compte de l'utilisateur terminée, nous devons envoyer
 *  un courrier électronique à l'adresse de messagerie de l'utilisateur. 
 *  Nous utiliserons l’ API Spring Mail pour réaliser cette fonctionnalité.
 *  -------------------------------------------------------------------------------
 *  Nous avons ajouté les propriétés de configuration pour cela dans le fichier
 *  de propriétés indiqué précédemment, afin de pouvoir définir un service de 
 *  messagerie
 *  -------------------------------------------------------------------------------
 *  Nous avons annoté la classe avec @Servicelaquelle est une variante de 
 *  l' @Componentannotation. Cela permet à Spring Boot de découvrir le 
 *  service et de l'enregistrer pour utilisation.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

public class SendingMailService {

	 private JavaMailSender javaMailSender;

	    @Autowired
	    public SendingMailService(JavaMailSender javaMailSender) {
	        this.javaMailSender = javaMailSender;
	    }

	    @Async
	    public void sendEmail(SimpleMailMessage email) {
	        javaMailSender.send(email);
	    }
}
