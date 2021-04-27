/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.tests;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author B3202-B3205
 */
public class Main {
	private static final Service sevice = new Service();

	public static void main(String[] args) {
		JpaUtil.init();
		sevice.init();
		testerInscriptionClient();
		testerAuthentificationClient();
		testerObtenirConsultation();
		testerDebuterConsultation();
		testerFinirConsultation();
		testerCommenterConsultation();
		testerNoterConsultation();
		testerListerMedium();
		testerAuthentificationEmploye();
		testerObtenirPredictions();
		JpaUtil.destroy();
	}

	public static void testerInscriptionClient() {
		Long c;
		try {
			Client client1 = new Client("12345",
					"Marie",
					"Curie",
					"1 rue Pierre et Marie Curie, 75005 Paris, France",
					"0123456789",
					Boolean.FALSE,
					"marie.curie@email.com",
					(new SimpleDateFormat("dd/MM/yyyy").parse("7/11/1867")));

			c = sevice.inscrireClient(client1);

			if (c != null) {
				System.out.println("> Succès inscription");
			}
			else {
				System.out.println("> Echec inscription");
			}

			Client client2 = new Client("12345",
					"Marie",
					"Curie",
					"1 rue Pierre et Marie Curie, 75005 Paris, France",
					"0123456789",
					Boolean.FALSE,
					"marie.curie@email.com",
					(new SimpleDateFormat("dd/MM/yyyy").parse("7/11/1867")));

			c = sevice.inscrireClient(client2); // probleme

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

		client = sevice.authentifierClient("marie.curie@email.com", "12345");
		if (client != null) {
			System.out.println("> authentification reussie");
			System.out.println(client);
		}
		else {
			System.out.println("> authentification faillie");
		}

		client = sevice.authentifierClient("marie.curie@email.com", "grrrr");
		if (client != null) {
			System.out.println("> authentification reussie");
		}
		else {
			System.out.println("> authentification faillie");
		}
	}

	public static void testerAuthentificationEmploye() {
		Employe employe;

		employe = sevice.authentifierEmploye("rborrotimatiasdantas4171@free.fr", "12345");
		if (employe != null) {
			System.out.println("> authentification reussie");
			System.out.println(employe);
		}
		else {
			System.out.println("> authentification faillie");
		}

		employe = sevice.authentifierEmploye("marie.curie@email.com", "grrrr");
		if (employe != null) {
			System.out.println("> authentification reussie");
		}
		else {
			System.out.println("> authentification faillie");
		}
	}

	private static void testerObtenirConsultation() {
		Client client = sevice.authentifierClient("marie.curie@email.com", "12345");
		List<Medium> mediums = (List<Medium>) sevice.listerMediums();
		Medium medium = mediums.get(0);

		Consultation consultation = sevice.obtenirConsultation(client, medium);

		System.out.println(consultation);
	}

	public static void testerDebuterConsultation() {
		Consultation consult = new Consultation();
		sevice.debuterConsultation(consult);
		System.out.println(consult);
	}

	private static void testerFinirConsultation() {
		Client client = sevice.authentifierClient("marie.curie@email.com", "12345");
		List<Medium> mediums = (List<Medium>) sevice.listerMediums();
		Medium medium = mediums.get(0);

		Consultation consultation = sevice.obtenirConsultation(client, medium);
		consultation = sevice.debuterConsultation(consultation);
		consultation = sevice.finirConsultation(consultation);

		System.out.println(consultation);
	}

	public static void testerCommenterConsultation() {
		Consultation consult = new Consultation();
		String commentaire = "Ce client capte R mdr c une chevre";
		sevice.commenterConsultation(consult,commentaire);
		System.out.println(consult);
	}

	public static void testerNoterConsultation() {
		Consultation consult = new Consultation();
		int note = 2;
		sevice.noterConsultation(consult, note);
		System.out.println(consult);
	}

	public static void testerListerMedium() {
		List<Medium> mediums = (List<Medium>) sevice.listerMediums();
		mediums.forEach((medium) ->
			System.out.println(medium)
		);
	}

	public static void testerObtenirPredictions() {
		Client client = sevice.authentifierClient("marie.curie@email.com", "12345");
		int amour = 2;
		int sante = 1;
		int travail = 3;
		List<String> predictions = sevice.obtenirPredictions( client,  amour,  sante, travail);
		predictions.forEach((prediction) ->
			System.out.println(prediction)
		);
	}

	public static void testScenario() {
		// TODO faire test scenario
	}
}
