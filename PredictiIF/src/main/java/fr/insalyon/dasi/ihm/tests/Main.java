/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.tests;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author B3202-B3205
 */
public class Main {
    private static final Service serviceClient = new Service();
    
    public static void main(String[] args) {
        JpaUtil.init();
        serviceClient.init();
        testerInscriptionClient();
        testerAuthentificationClient();
        JpaUtil.destroy();
    }
    
    public static void testerInscriptionClient() {
        Long c;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d;
        Client client1;
        try {
            d = sdf.parse("7/11/1867");
            client1 = new Client("marie.curie@email.com", 
                                "12345", 
                                d, 
                                "Marie", "Curie", 
                                "1 rue Pierre et Marie Curie, 75005 Paris, France", 
                                "0123456789");
            
            c = serviceClient.inscrireClient(client1);
            if (c != null) {
                System.out.println("> Succès inscription");
            }
            else {
                System.out.println("> Echec inscription");
            }
        
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("> Echec inscription");
        }
    }
    
    public static void testerAuthentificationClient() {
        Client client;
        
        client = serviceClient.authentifierClient("marie.curie@email.com", "12345");
        if (client != null) {
            System.out.println("> authentification reussie");
        }
        else {
            System.out.println("> authentification faillie");
        }
        
        client = serviceClient.authentifierClient("pierre.dupont@mail.com", "grrrr");
        if (client != null) {
            System.out.println("> authentification reussie"); 
        }
        else {
            System.out.println("> authentification faillie");
        }
    }
}
