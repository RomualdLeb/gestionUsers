package com.inti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inti.entities.Utilisateur;
import com.inti.services.impl.UtilisateurServiceImpl;

@RestController // on peut mettre un request mapping si on veux ajouter un url en plus
@RequestMapping(value = "/gestionUsers") // pas obligatoire
public class UtilisateurController {

	@Autowired
	UtilisateurServiceImpl utilisateurService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	//@RequestMapping(value = "/users", method = RequestMethod.POST) // methode 1
	@PostMapping("/users")//methode 2
	public Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur) {// car info de req dans le body (on
			//pour la methode d'authentification
		Utilisateur user=new Utilisateur(); 
		user.setPassword(passwordEncoder.encode(utilisateur.getPassword()));//pour le format encodé du password
		user.setUsername(utilisateur.getUsername());
		user.setNomUtilisateur(utilisateur.getNomUtilisateur());
		user.setPrenomUtilisateur(utilisateur.getPrenomUtilisateur());
		user.setRoles(utilisateur.getRoles());
																					// met dans le body les infos qu'on
																					// veux stocker dans la bd, et dans
																					// le head infos qu'on veut montrer)
		return utilisateurService.saveUtilisateur(user);
	}

//	// put(maj de tous les attributs de l'entitie)/patch(maj de l'attribut désiré de
//	// l'entitie)
//	@RequestMapping(value = "/users", method = RequestMethod.PUT) // methode 1
//	public Utilisateur updateUtilisateur(@RequestBody Utilisateur utilisateur) {
//		return utilisateurService.updateUtilisateur(utilisateur);
//
//	}

	//@RequestMapping(value = "/users", method = RequestMethod.GET) // methode 1
	@GetMapping("/users") //methode 2
	public List<Utilisateur> findAll() {
		return utilisateurService.findAll();

	}

	//"finduser?id=1" OU	"finduser/1"
	//@requestParam	  OU	@pathVariable
	//@RequestMapping(value = "/users/{idUser}", method = RequestMethod.GET) // methode 1
	@GetMapping("/users/{idUser}") //methode 2
	public Utilisateur findOne(@PathVariable("idUser") Long id) {//idUser ref à value/url
		return utilisateurService.findOne(id);

	}
	
//	//avec requestparam
//	@GetMapping("/users") //methode 2
//	public Utilisateur findOne(@RequestParam("idUser") Long id) {//idUser ref à value/url
//		return utilisateurService.findOne(id);
//
//	} //il faut mettre dans l'url "/users/?idUser=3"
	
	//@RequestMapping(value = "/users/{idUser}", method = RequestMethod.DELETE) // methode 1
	@DeleteMapping("/users/{idUser}")
	public void deleteUtilisateur(@PathVariable("idUser") Long id) {
		utilisateurService.deleteUtilisateur(id);

	}

}
