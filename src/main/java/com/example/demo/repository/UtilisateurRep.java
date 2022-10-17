package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Utilisateur;
import com.example.demo.model.Ville;

public interface UtilisateurRep extends JpaRepository<Utilisateur, Integer> {

	@Query(value = "SELECT  * from utilisateur", nativeQuery = true)
	public Page<Utilisateur> listUtilisateur(Pageable paging);
	
	@Query(value = "SELECT  * from utilisateur where nom like :input", nativeQuery = true)
	public ArrayList<Utilisateur> listUtilisateurRec(String input);
	
	@Query(value = "SELECT  * from utilisateur where matricule = :matricule and  motdepasse= :motdepasse", nativeQuery = true)
	public Utilisateur login(String matricule , String motdepasse);
	
	
	
}
