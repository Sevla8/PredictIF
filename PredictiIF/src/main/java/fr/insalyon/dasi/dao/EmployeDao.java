/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import javax.persistence.TypedQuery;

/**
 *
 * @author B3202-B3205
 */
public class EmployeDao {
    
    public void creer(Employe employe) {
        JpaUtil.obtenirContextePersistance().persist(employe);
    }
    
//    public Employe chercherParGenreEtDisponibilite(Boolean genre, Boolean estDisponible) {
//        String s = "SELECT e FROM Employe e WHERE e.genre = : ORDER BY e.nbConsultations ASC";
//        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
//        Employe employe;
//        try {
//            employe = (Employe) query.getSingleResult();
//        }
//        catch (javax.persistence.NoResultException e) {
//            employe = null;
//        }
//        return employe;
//    }
}
