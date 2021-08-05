package com.inti.model;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.inti.entities.Utilisateur;

public class UtilisateurDetail implements UserDetails {

	/**
	 * Classe intermediaire qui permet de s'adapter avec le projet déja conçu, faire le lien avec spring security
	 */
	private static final long serialVersionUID = 1L;

	private Utilisateur utilisateur;
	Set<GrantedAuthority> authorities = null;

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;//methode get attention, donc rajoute la methode set
	}

	@Override
	public String getPassword() {
		return utilisateur.getPassword();//recuperer le password de l'utilisateur
	}

	@Override
	public String getUsername() {
		return utilisateur.getUsername();//recuperer le Username de l'utilisateur
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;//mettre en true de base
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;//mettre en true de base
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;//mettre en true de base
	}

	@Override
	public boolean isEnabled() {
		return true;//mettre en true de base ou utilisateur.enalble pour avoir accer à l'attribut
	}

}
