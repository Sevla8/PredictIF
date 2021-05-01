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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author B3202-B3205
 */
public class Main {
	private static final Service service = new Service();
	private static final Random rand = new Random();

	public static void main(String[] args) {
		JpaUtil.init();
		service.init();
		// testerInscriptionClient();
		// testerAuthentificationClient();
		// testerObtenirConsultation();
		// testerDebuterConsultation();
		// testerFinirConsultation();
		// testerCommenterConsultation();
		// testerNoterConsultation();
		// testerListerMedium();
		// testerAuthentificationEmploye();
		// testerObtenirPredictions();
		// testerTrouverConsultationParId();
		// testerObtenirConsultationAffectee();
		// testerObtenirTop5Mediums();
		// testerObtenirNombreDeConsultationsParMedium();
		// testerObtenirHistoriqueClient();
		// testerObtenirTop5Employes();
		// testerObtenirNombreDeConsultationsParEmploye();
		// testerObtenirHistoriqueEmploye();
		testScenario();
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
		service.commenterConsultation(consult, commentaire);
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
		Consultation consultEnCours = service.obtenirConsultationAffectee(consultation3.getEmploye());

		System.out.println(consultation);
		System.out.println();
		System.out.println(consultation2);
		System.out.println();
		System.out.println(consultation3);
		System.out.println();
		System.out.println(consultEnCours);
	}

	public static void testerObtenirTop5Mediums() {
		List<Medium> mediums = service.obtenirTop5Mediums();
		mediums.forEach((medium) ->
			System.out.println(medium)
		);
	}

	public static void testerObtenirNombreDeConsultationsParMedium(){
		String [][] listeMediumNbConsultations = service.obtenirNombreDeConsultationsParMedium();
		for(int i = 0; i < listeMediumNbConsultations.length; ++i) {
			System.out.println(
				"Medium de type : " + listeMediumNbConsultations[i][0] +
				", Denomination : " + listeMediumNbConsultations[i][1] +
				", Nb Consultations : " + listeMediumNbConsultations[i][2]
			);
		}
	}

	public static void testerObtenirHistoriqueClient() {
		Client client = service.authentifierClient("marie.curie@email.com", "12345");
		List<Consultation> hitorique = service.obtenirHistorique(client);
		hitorique.forEach((consultation) ->
			System.out.println(consultation)
		);
	}

	public static void testerObtenirTop5Employes() {
		List<Employe> employes = service.obtenirTop5Employes();
		employes.forEach((employe) ->
			System.out.println(employe)
		);
	}

	public static void testerObtenirNombreDeConsultationsParEmploye() {
		String [][] listeEmployeNbConsultations = service.obtenirNombreDeConsultationsParEmploye();
		for (int i = 0; i < listeEmployeNbConsultations.length; ++i) {
			System.out.println(
				"Nom : " + listeEmployeNbConsultations[i][0] +
				", Prenom : " + listeEmployeNbConsultations[i][1] +
				", Nb Consultations : " + listeEmployeNbConsultations[i][2]
			);
		}
	}

	public static void testerObtenirHistoriqueEmploye() {
		Employe employe = service.authentifierEmploye("nolmeadamarais1551@gmail.com", "12345");
		List<Consultation> hitorique = service.obtenirHistorique(employe);
		hitorique.forEach((consultation) ->
			System.out.println(consultation)
		);
	}

	public static void testScenario() {
		Client client;
		try {
			client = new Client(
				"12345",
				"Albert",
				"Einstein",
				"1 rue Albert Einstein, 75006 Paris, France",
				"0123456789",
				Boolean.TRUE,
				"albert.einstein@email.com",
				(new SimpleDateFormat("dd/MM/yyyy").parse("14/03/1879"))
			);
		} catch (ParseException e) {
			client = null;
		}

		System.out.println("Le client s'inscrit");
		service.inscrireClient(client);	// Le client s'inscrit

		System.out.println("Le client s'authentifie");
		client = service.authentifierClient("albert.einstein@email.com", "12345");	// Le client s'authentifie

		System.out.println("Affichage de la liste des mediums");
		List<Medium> mediums = service.listerMediums();	// Affichage de la liste des mediums

		System.out.println("Le client choisit un medium dans la liste");
		Medium medium = mediums.get(rand.nextInt(mediums.size()));	// Le client choisit un medium dans la liste

		System.out.println("Le client réserve une consultation");
		Consultation consultation = service.obtenirConsultation(client, medium);	// Le client réserve une consultation
		System.out.println();
		System.out.println(consultation);
		System.out.println();

		System.out.println("L'employé s'authentifie");
		Employe employe = consultation.getEmploye();
		employe = service.authentifierEmploye(employe.getMail(), employe.getMotDePasse());	// L'employé s'authentifie

		System.out.println("L'employé consulte sa consultation future");
		consultation = service.obtenirConsultationAffectee(employe);	// L'employé consulte sa consultation future
		System.out.println();
		System.out.println(consultation);
		System.out.println();

		System.out.println("L'employé consulte l'historique des consultations du client");
		List<Consultation> hitorique = service.obtenirHistorique(client);	// L'employé consulte l'historique des consultations du client
		System.out.println();
		hitorique.forEach((hist) ->
			System.out.println(hist)
		);
		System.out.println();

		System.out.println("L'employé mentionne qu'il est prêt à débuter la consulation");
		consultation = service.debuterConsultation(consultation);	// L'employé mentionne qu'il est prêt à débuter la consulation
		System.out.println();
		System.out.println(consultation);
		System.out.println();

		System.out.println("L'employé demande une prédiction à AstroNet");
		List<String> predictions = service.obtenirPredictions(client, 2, 1, 3); // L'employé demande une prédiction à AstroNet
		System.out.println();
		predictions.forEach((prediction) ->
			System.out.println(prediction)
		);
		System.out.println();

		System.out.println("L'employé met fin à la consultation");
		consultation = service.finirConsultation(consultation);	// L'employé met fin à la consultation
		System.out.println();
		System.out.println(consultation);
		System.out.println();

		System.out.println("L'employé ajoute un commentaire à la consultation");
		service.commenterConsultation(consultation, "Ce client capte R mdr c une chevre");	// L'employé ajoute un commentaire à la consultation
		System.out.println();
		System.out.println(consultation);
		System.out.println();

		System.out.println("Le client note sa consultation");
		service.noterConsultation(consultation, 3); // Le client note sa consultation
		System.out.println();
		System.out.println(consultation);
		System.out.println();

		// L'employé consulte les statistiques
		System.out.println("L'employé consulte les statistiques");

		String [][] listeMediumNbConsultations = service.obtenirNombreDeConsultationsParMedium();
		System.out.println();
		for(int i = 0; i < listeMediumNbConsultations.length; ++i) {
			System.out.println(
				"Medium de type : " + listeMediumNbConsultations[i][0] +
				", Denomination : " + listeMediumNbConsultations[i][1] +
				", Nb Consultations : " + listeMediumNbConsultations[i][2]
			);
		}
		System.out.println();

		List<Medium> listeMediums = service.obtenirTop5Mediums();
		System.out.println();
		listeMediums.forEach((med) ->
			System.out.println(med)
		);
		System.out.println();

		String [][] listeEmployeNbConsultations = service.obtenirNombreDeConsultationsParEmploye();
		System.out.println();
		for (int i = 0; i < listeEmployeNbConsultations.length; ++i) {
			System.out.println(
				"Nom : " + listeEmployeNbConsultations[i][0] +
				", Prenom : " + listeEmployeNbConsultations[i][1] +
				", Nb Consultations : " + listeEmployeNbConsultations[i][2]
			);
		}
		System.out.println();

		List<Employe> listeEmployes = service.obtenirTop5Employes();
		System.out.println();
		listeEmployes.forEach((emp) ->
			System.out.println(emp)
		);
		System.out.println();

		System.out.println("L'employé consulte l'historique de ses consultations");
		List<Consultation> hitoriqueEmploye = service.obtenirHistorique(employe);	// L'employé consulte son historique
		System.out.println();
		hitoriqueEmploye.forEach((hist) ->
			System.out.println(hist)
		);
		System.out.println();
	}
}
