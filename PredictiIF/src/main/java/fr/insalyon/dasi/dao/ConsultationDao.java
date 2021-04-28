/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;

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
}