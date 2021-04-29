/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Client;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

/**
 *
 * @author B3202-B3205
 */
public class ConsultationDao {

	public void creer(Consultation consultation) {
		JpaUtil.obtenirContextePersistance().persist(consultation);
	}

	public void modifier(Consultation consultation){
		JpaUtil.obtenirContextePersistance().merge(consultation);
	}

	public Consultation chercherParId(Long id) {
		return JpaUtil.obtenirContextePersistance().find(Consultation.class, id);
	}

	public List<Consultation> chercherParIdEmploye(Long idEmploye) {
		String s = "SELECT c FROM Consultation c WHERE c.employe.id = :idEmploye AND c.dateDebut IS NOT NULL ORDER BY c.dateDebut DESC";
		TypedQuery<Consultation> query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
		query.setParameter("idEmploye", idEmploye);
		List<Consultation> consultations = query.getResultList();
		return consultations;
	}

	// public List<Consultation> chercherRepartitionClientsParEmploye() {
	// 	String s = "SELECT DISTINCT c.Client, c.Employe FROM Consultation c GROUP BY c.Employe";
	// 	TypedQuery<Consultation> query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
	// 	List<Consultation> repartition = query.getResultList();
	// 	return repartition;
	// }

	public List<Consultation> chercherConsultationsParClient(Long id){
		String s = "SELECT c FROM Consultation c WHERE c.client.id = :idClient";
		TypedQuery<Consultation> query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
		query.setParameter("idClient", id);
		List<Consultation> consultations = query.getResultList();
		return consultations;
	}
}