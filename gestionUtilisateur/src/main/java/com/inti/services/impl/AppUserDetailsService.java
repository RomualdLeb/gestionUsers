package com.inti.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inti.entities.Role;
import com.inti.entities.Utilisateur;
import com.inti.model.UtilisateurDetail;
import com.inti.services.interfaces.utilisateurService;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	utilisateurService utilisateurService;
	
	
	/**
	 * charger l'utilisateur avec son userName
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Utilisateur utilisateur = utilisateurService.findByUsername(username);
		Set<Role> roles = utilisateur.getRoles();//recuperations des roles
		
		//recuperation des profils = GrantedAuthority
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getLibelle()));
		}
		
		//recupere l'utilisateur et lui associes les infos pour la co
		UtilisateurDetail utilisateurDetail = new UtilisateurDetail();
		utilisateurDetail.setUtilisateur(utilisateur);
		utilisateurDetail.setAuthorities(authorities);
		
		return utilisateurDetail;
	}

	
	
	
	
	
	
	
}
