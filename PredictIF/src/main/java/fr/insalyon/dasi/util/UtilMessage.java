/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.util;

/**
 *
 * @author B3202-B3205
 */
public class UtilMessage {

    private static final String MAILEXPEDITEUR = "contact@predict.if";

    public static String getMailExpediteur() {
        return MAILEXPEDITEUR;
    }

    public static String getMailObjetSucces() {
        return "Bienvenue chez PREDICT'IF";
    }

    public static String getMailObjetEchec() {
        return "Echec de l’inscription chez PREDICT’IF";
    }

    public static String getMailCorpsSucces(String prenom) {
        return "Bonjour " + prenom + ", nous vous confirmons votre inscription "
                + "au service PREDICT'IF. Rendez-vous vite sur notre site pour "
                + "consulter votre profil astrologique et profiter des dons "
                + "incroyables de nos mediums.";
    }

    public static String getMailCorpsEchec(String prenom) {
        return "Bonjour " + prenom + ",  votre inscription au service "
                + "PREDICT’IF a malencontreusement échoué... Merci de "
                + "recommencer ultérieurement.";
    }

    public static String getNotification(String employePrenom, String employeNom, String employeNumeroDeTelephone, Boolean clientGenre, String clientPrenom, String clientNom, String mediumDenomination) {
        String genre = clientGenre ? "Mr" : "Mme";
        return "Pour : " + employePrenom + " " + employeNom.toUpperCase() + ", "
                + "Tel : " + employeNumeroDeTelephone + "\nMessage : Bonjour "
                + employePrenom + ". Consultation requise pour " + genre + " "
                + clientPrenom + " " + clientNom.toUpperCase() + ". Médium à "
                + "incarner : " + mediumDenomination;
    }
}
