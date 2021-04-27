/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author B3202-B3205
 */
public class EmployeDao {

	public void creer(Employe employe) {
		JpaUtil.obtenirContextePersistance().persist(employe);
	}

	public Employe modifier(Employe employe){
		return JpaUtil.obtenirContextePersistance().merge(employe);
	}

	public Employe chercherParGenreEtDisponibilite(Boolean genre) {
		String s = "SELECT e FROM Employe e WHERE e.genre = :genre AND e.estDisponible = :dispo ORDER BY e.nbConsultations ASC";
		TypedQuery<Employe> query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
		query.setParameter("genre", genre);
		query.setParameter("dispo", true);

		Employe employe = null;
		List<Employe> employes = query.getResultList();
		if (employes.size() > 0) {
			employe = employes.get(0);
		}
		return employe;
	}

	public Employe chercherParMail(String mail) {
		String s = "SELECT e FROM Employe e WHERE e.mail = :mail";
		TypedQuery<Employe> query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
		query.setParameter("mail", mail);
		List<Employe> employes = query.getResultList();
		Employe resultat = null;
		if (!employes.isEmpty()) {
			resultat = employes.get(0);
		}
		return resultat;
	}
}
