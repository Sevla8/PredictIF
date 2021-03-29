/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.ProfilAstral;

/**
 *
 * @author B3202-B3205
 */
public class ProfilAstralDao {
    
    public void creer(ProfilAstral profilAstral) {
        JpaUtil.obtenirContextePersistance().persist(profilAstral);
    }
    
    public ProfilAstral chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(ProfilAstral.class, id);
    }
}
