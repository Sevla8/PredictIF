/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.ConsultationDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.dao.ProfilAstralDao;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.util.AstroTest;
import fr.insalyon.dasi.util.Message;
import java.io.IOException;

/**
 *
 * @author B3202-B3205
 */
public class Service {
    private final ClientDao clientDao = new ClientDao();
    private final MediumDao mediumDao = new MediumDao();
    private final EmployeDao employeDao = new EmployeDao();
    private final ProfilAstralDao profilAstralDao = new ProfilAstralDao();
    private final ConsultationDao consultationDao = new ConsultationDao();
    private final AstroTest astroApi = new AstroTest();
    
    public void init() {
        Medium medium1 = new Spirite("support1", "denomination1", "genre1", "presentation1");
        Medium medium2 = new Spirite("support2", "denomination2", "genre2", "presentation2");
        Medium medium3 = new Spirite("support3", "denomination3", "genre3", "presentation3");
        Medium medium4 = new Astrologue("formation4", "promotion4", "denomination4", "genre4", "presentation4");
        Medium medium5 = new Astrologue("formation5", "promotion5", "denomination5", "genre5", "presentation5");
        Medium medium6 = new Astrologue("formation6", "promotion6", "denomination6", "genre6", "presentation6");
        Medium medium7 = new Cartomancien("denomination7", "genre7", "presentation7");
        Medium medium8 = new Cartomancien("denomination8", "genre8", "presentation8");
        Medium medium9 = new Cartomancien("denomination9", "genre9", "presentation9");

        Employe employe1 = new Employe("nom1", "prenom1", "genre1", "motDePasse1", "numeroDeTelephone1", "mail1");
        Employe employe2 = new Employe("nom2", "prenom2", "genre2", "motDePasse2", "numeroDeTelephone2", "mail2");
        Employe employe3 = new Employe("nom3", "prenom3", "genre3", "motDePasse3", "numeroDeTelephone3", "mail3");
        Employe employe4 = new Employe("nom4", "prenom4", "genre4", "motDePasse4", "numeroDeTelephone4", "mail4");
        Employe employe5 = new Employe("nom5", "prenom5", "genre5", "motDePasse5", "numeroDeTelephone5", "mail5");
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            mediumDao.creer(medium1);
            mediumDao.creer(medium2);
            mediumDao.creer(medium3);
            mediumDao.creer(medium4);
            mediumDao.creer(medium5);
            mediumDao.creer(medium6);
            mediumDao.creer(medium7);
            mediumDao.creer(medium8);
            mediumDao.creer(medium9);
            
            employeDao.creer(employe1);
            employeDao.creer(employe2);
            employeDao.creer(employe3);
            employeDao.creer(employe4);
            employeDao.creer(employe5);

            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès");
        }
        catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
            JpaUtil.annulerTransaction();
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public Long inscrireClient(Client client) {
        Long id;
        List<String> list;
        try {
            JpaUtil.creerContextePersistance();
            
            list = astroApi.getProfil(client.getPrenom(), client.getDateDeNaissance());
            client.setProfilAstral(new ProfilAstral(list.get(0), list.get(1), list.get(2), list.get(3)));

            JpaUtil.ouvrirTransaction();
           
            profilAstralDao.creer(client.getProfilAstral());
            clientDao.creer(client);
            
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès");
                        
            StringWriter corps = new StringWriter();
            PrintWriter mailWriter = new PrintWriter(corps);            // faire classe util mail
            mailWriter.print("Bonjour ");
            mailWriter.print(client.getPrenom());
            mailWriter.println(", nous vous confirmons votre inscription au service PREDICT'IF.");
            mailWriter.println("Rendez-vous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos mediums.");
            Message.envoyerMail(
                "contact@predict.if",
                client.getMail(),
                "Bienvenue chez PREDICT'IF",
                corps.toString()
            );
            
            id = client.getId();
        }
        catch (Exception ex) {
            StringWriter corps = new StringWriter();
            PrintWriter mailWriter = new PrintWriter(corps);
            mailWriter.print("Bonjour ");
            mailWriter.print(client.getPrenom());
            mailWriter.println(",  votre inscription au service PREDICT’IF a malencontreusement échoué...");
            mailWriter.println("Merci de recommencer ultérieurement.");
            Message.envoyerMail(
                "contact@predict.if",
                client.getMail(),
                "Echec de l’inscription chez PREDICT’IF",
                corps.toString()
            );
            
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
            JpaUtil.annulerTransaction();
            
            id = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return id;
    }
    
    public Client authentifierClient(String mail, String mdp) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                if (client.getMotDePasse().equals(mdp)) {
                    resultat = client;
                }
            }
            Logger.getAnonymousLogger().log(Level.INFO, "succès");
        }
        catch (Exception ex) {
            resultat = null;
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Client trouverClientParId(Long id) {
        Client client;
        try {
            JpaUtil.creerContextePersistance();
            client = clientDao.chercherParId(id);
            Logger.getAnonymousLogger().log(Level.INFO, "succès");
        }
        catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
            client = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return client;
    }
    
    public List<Client> listerClients() {
        List<Client> clients;
        ClientDao clientDAO = new ClientDao();
        try {
            JpaUtil.creerContextePersistance();
            clients = clientDAO.chercherTousOrdonnee();
            Logger.getAnonymousLogger().log(Level.INFO, "succès");
        }
        catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
            clients = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return clients;
    }
    
//    public List<Medium> listerMedium() {
//        
//    }
    
//    public Consultation obtenirConsultation(Client client, Medium medium) {
//        Employe employe = this.obtenirEmploye(medium);
//        Consultation consultation = new Consultation(medium, client, employe);
//        try {
//            JpaUtil.creerContextePersistance();
//            consultationDao.creer(consultation);
//            client.ajouterConsultation(consultation);
//            Logger.getAnonymousLogger().log(Level.INFO, "succès");
//        }
//        catch (Exception ex) {
//            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
//            consultation = null;
//        }
//        finally {
//            JpaUtil.fermerContextePersistance();
//        }
//        return consultation;
//    }
//
//    private Employe obtenirEmploye(Medium medium) {
//        Employe resultat;
//        
//        return resultat;
//    }
}
