/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Medium;

/**
 *
 * @author B3202-B3205
 */
public class MediumDao {
    
    public void creer(Medium medium) {
        JpaUtil.obtenirContextePersistance().persist(medium);
    }
    
}
