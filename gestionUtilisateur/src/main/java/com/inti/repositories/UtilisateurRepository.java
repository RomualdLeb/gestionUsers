package com.inti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.entities.Utilisateur;

@Repository // pour injection de dépendance
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
//JpaRepository : methode de base CRUT : findAll, deleteById, findById
	
	Utilisateur findByUsername(String username);
	
	
	
	
}
