/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Medium;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author B3202-B3205
 */
public class MediumDao {

	public void creer(Medium medium) {
		JpaUtil.obtenirContextePersistance().persist(medium);
	}

	public Medium modifier(Medium medium){
		return JpaUtil.obtenirContextePersistance().merge(medium);
	}

	public List<Medium> chercherParGenre(Boolean genre){
		String s = "select m from Medium m where m.genre = :genre";
		TypedQuery<Medium> query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
		query.setParameter("genre", genre);
		return query.getResultList();
	}

	public List<Medium> chercherTous(){
		String s ="select m from Medium m order by m.denomination asc";
		TypedQuery<Medium> query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
		return query.getResultList();
	}
}
