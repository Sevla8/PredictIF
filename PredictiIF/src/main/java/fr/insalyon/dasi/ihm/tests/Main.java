/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.tests;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
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
	private static final Service service = new Service();

	public static void main(String[] args) {
		JpaUtil.init();
		service.init();
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
		testerTrouverConsultationParId();
		testerObtenirConsultationAffectee();
		testerObtenirTop5Medium();
		testerObtenirNombreDeConsultationsParMedium();
		testerObtenirHistorique();
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

			c = service.inscrireClient(client1);

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

			c = service.inscrireClient(client2); // probleme

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

		client = service.authentifierClient("marie.curie@email.com", "12345");
		if (client != null) {
			System.out.println("> authentification reussie");
			System.out.println(client);
		}
		else {
			System.out.println("> authentification faillie");
		}

		client = service.authentifierClient("marie.curie@email.com", "grrrr");
		if (client != null) {
			System.out.println("> authentification reussie");
		}
		else {
			System.out.println("> authentification faillie");
		}
	}

	public static void testerAuthentificationEmploye() {
		Employe employe;

		employe = service.authentifierEmploye("rborrotimatiasdantas4171@free.fr", "12345");
		if (employe != null) {
			System.out.println("> authentification reussie");
			System.out.println(employe);
		}
		else {
			System.out.println("> authentification faillie");
		}

		employe = service.authentifierEmploye("marie.curie@email.com", "grrrr");
		if (employe != null) {
			System.out.println("> authentification reussie");
		}
		else {
			System.out.println("> authentification faillie");
		}
	}

	private static void testerObtenirConsultation() {
		Client client = service.authentifierClient("marie.curie@email.com", "12345");
		List<Medium> mediums = (List<Medium>) service.listerMediums();
		Medium medium = mediums.get(0);

		Consultation consultation = service.obtenirConsultation(client, medium);

		System.out.println(consultation);
	}

	public static void testerDebuterConsultation() {
		Consultation consult = new Consultation();
		service.debuterConsultation(consult);
		System.out.println(consult);
	}

	private static void testerFinirConsultation() {
		Client client = service.authentifierClient("marie.curie@email.com", "12345");
		List<Medium> mediums = (List<Medium>) service.listerMediums();
		Medium medium = mediums.get(0);

		Consultation consultation = service.obtenirConsultation(client, medium);
		consultation = service.debuterConsultation(consultation);
		consultation = service.finirConsultation(consultation);

		System.out.println(consultation);
	}

	public static void testerCommenterConsultation() {
		Consultation consult = new Consultation();
		String commentaire = "Ce client capte R mdr c une chevre";
		service.commenterConsultation(consult,commentaire);
		System.out.println(consult);
	}

	public static void testerNoterConsultation() {
		Consultation consult = new Consultation();
		int note = 2;
		service.noterConsultation(consult, note);
		System.out.println(consult);
	}

	public static void testerListerMedium() {
		List<Medium> mediums = (List<Medium>) service.listerMediums();
		mediums.forEach((medium) ->
			System.out.println(medium)
		);
	}

	public static void testerObtenirPredictions() {
		Client client = service.authentifierClient("marie.curie@email.com", "12345");
		int amour = 2;
		int sante = 1;
		int travail = 3;
		List<String> predictions = service.obtenirPredictions( client,  amour,  sante, travail);
		predictions.forEach((prediction) ->
			System.out.println(prediction)
		);
	}

	public static void testerTrouverConsultationParId() {
		Client client = service.authentifierClient("marie.curie@email.com", "12345");
		List<Medium> mediums = (List<Medium>) service.listerMediums();
		Medium medium = mediums.get(0);

		Consultation consultation = service.obtenirConsultation(client, medium);

		Long id = consultation.getId();

		Consultation c = service.trouverConsultationParId(id);

		System.out.println(c);
	}

	public static void testerObtenirConsultationAffectee() {
		Client client = service.authentifierClient("marie.curie@email.com", "12345");
		List<Medium> mediums = (List<Medium>) service.listerMediums();
		Medium medium = mediums.get(0);

		Consultation consultation = service.obtenirConsultation(client, medium);
		consultation = service.debuterConsultation(consultation);
		consultation = service.finirConsultation(consultation);


		Consultation consultation2 = service.obtenirConsultation(client, medium);
		consultation2 = service.debuterConsultation(consultation2);
		consultation2 = service.finirConsultation(consultation2);

		Consultation consultation3 = service.obtenirConsultation(client, medium);
		consultation3 = service.debuterConsultation(consultation3);
		Consultation consultEnCours = service.obtenirConsultationAffectee(service.trouverEmployeParId(10L));

		System.out.println(consultation);
		System.out.println();
		System.out.println(consultation2);
		System.out.println();
		System.out.println(consultation3);
		System.out.println();
		System.out.println(consultEnCours);
	}

	public static void testerObtenirTop5Medium() {
		List<Medium> mediums = service.obtenirTop5Medium();
		mediums.forEach((medium) ->
			System.out.println(medium)
		);
	}

	public static void testerObtenirNombreDeConsultationsParMedium(){
		String [][] listeMediumNbConsultations=service.obtenirNombreDeConsultationsParMedium();
		for(int i=0;i<listeMediumNbConsultations.length;i++){
			System.out.println("Medium de type : "+listeMediumNbConsultations[i][0]+", Denomination :"+listeMediumNbConsultations[i][1]
								+", Nb Consultations :"+listeMediumNbConsultations[i][2]);
		}
	}

	public static void testerObtenirHistorique() {
		Client client = service.authentifierClient("marie.curie@email.com", "12345");
		List<Consultation> hitorique = service.obtenirHistorique(client);
		hitorique.forEach((consultation) ->
			System.out.println(consultation)
		);
	}

	public static void testScenario() {
		// TODO faire test scenario

	}
}
