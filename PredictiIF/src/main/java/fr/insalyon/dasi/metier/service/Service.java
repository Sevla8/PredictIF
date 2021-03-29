/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.dao.ProfilAstralDao;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.util.AstroTest;
import fr.insalyon.dasi.util.Message;

/**
 *
 * @author B3202-B3205
 */
public class Service {
    private final ClientDao clientDao = new ClientDao();
    private final MediumDao mediumDao = new MediumDao();
    private final ProfilAstralDao profilAstralDao = new ProfilAstralDao();
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
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            clientDao.creer(client);
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d = sdf.parse(client.getDateDeNaissance());
            List<String> list = astroApi.getProfil(client.getPrenom(), d);
            client.setProfilAstral(new ProfilAstral(list.get(0), list.get(1), list.get(2), list.get(3)));
           
            profilAstralDao.creer(client.getProfilAstral());
            
            StringWriter corps = new StringWriter();
            PrintWriter mailWriter = new PrintWriter(corps);
            mailWriter.println("Bonjour,");
            mailWriter.println();
            mailWriter.println("Validation acceptée !");
            mailWriter.println();
            mailWriter.println("À bientôt sur Predict'IF,");
            mailWriter.println();
            mailWriter.println("L'équipe de Predict'IF");
            Message.envoyerMail(
                    "noreply@predictif.fr",
                    client.getMail(),
                    "Bienvenue sur Predict'IF",
                    corps.toString()
                );

            StringWriter message = new StringWriter();
            PrintWriter notificationWriter = new PrintWriter(message);
            notificationWriter.println("Ceci est une notification pour prévenir de 2 choses:");
            notificationWriter.println("1) NE PAS oublier le poly");
            notificationWriter.println("2) TESTER au fur et à mesure du développement");
            Message.envoyerNotification(
                    "0988776655",
                    message.toString()
                );
            
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès");
            
            id = client.getId();
        }
        catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
            JpaUtil.annulerTransaction();
            id = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return id;
    }
    
    public Boolean authentifierClient(String mail, String mdp) {
        Client client;
        Boolean authentificationReussie = false;
        try {
            JpaUtil.creerContextePersistance();
            client = clientDao.chercherParMail(mail);
            if (client != null && client.getMotDePasse().equals(mdp)) {
                authentificationReussie = true;
            }
            Logger.getAnonymousLogger().log(Level.INFO, "succès");
        }
        catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return authentificationReussie;
    }
    
    public Client trouverClientParId(Long id) {
        Client client;
        ClientDao clientDAO = new ClientDao();
        try {
            JpaUtil.creerContextePersistance();
            client = clientDAO.chercherParId(id);
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
    
    public List<Client> listerTousClients() {
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
}
