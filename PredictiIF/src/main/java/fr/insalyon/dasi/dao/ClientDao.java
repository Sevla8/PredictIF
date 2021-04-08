/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import java.util.List;
import fr.insalyon.dasi.metier.modele.Client;
import javax.persistence.TypedQuery;

/**
 *
 * @author B3202-B3205
 */
public class ClientDao {
    
    public void creer(Client client) {
        JpaUtil.obtenirContextePersistance().persist(client);
    }
    
    public Client chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Client.class, id);
    }
    
    public List<Client> chercherTousOrdonnee() {
        String s = "SELECT c FROM Client c ORDER BY c.nom, c.prenom ASC";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        return query.getResultList();
    }
    
    public Client chercherParMail(String mail) {
        String s = "SELECT c FROM Client c WHERE c.mail = :mail";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        query.setParameter("mail", mail);
        List<Client> clients = query.getResultList();
        Client resultat = null;
        if (!clients.isEmpty()) {
            resultat = clients.get(0);
        }
        return resultat;
    }
}
