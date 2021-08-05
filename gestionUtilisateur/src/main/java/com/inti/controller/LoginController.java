package com.inti.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.entities.Utilisateur;
import com.inti.services.interfaces.utilisateurService;



@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	utilisateurService utilisateurService;
	
	//pour faire le redirection qd on rentre les données d'authentification 
	
	public Utilisateur login(Principal principal) {//principal pour recuperer les données stocker dans la mémoire que l'on viens de taper du programme et verifier l'username via la methode findByUsername
		return utilisateurService.findByUsername(principal.getName());
	}
	
	
	
}
