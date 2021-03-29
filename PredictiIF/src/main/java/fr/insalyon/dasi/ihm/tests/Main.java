/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.tests;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;

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

        Client client1 = new Client("marie.curie@email.com", 
                                    "12345", 
                                    "7/11/1867", 
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
    }
    
    public static void testerAuthentificationClient() {
        Boolean authentificationReussie;
        
        authentificationReussie = serviceClient.authentifierClient("marie.curie@email.com", "12345");
        if (authentificationReussie) {
            System.out.println("> authentification reussie");
        }
        else {
            System.out.println("> authentification faillie");
        }
        
        authentificationReussie = serviceClient.authentifierClient("pierre.dupont@mail.com", "grrrr");
        if (authentificationReussie) {
            System.out.println("> authentification reussie"); 
        }
        else {
            System.out.println("> authentification faillie");
        }
    }
}
