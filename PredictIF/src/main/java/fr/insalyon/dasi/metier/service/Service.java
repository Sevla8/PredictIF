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
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.util.AstroTest;
import fr.insalyon.dasi.util.Message;
import fr.insalyon.dasi.util.UtilMessage;
import java.io.IOException;
import java.util.Date;
//import java.text.SimpleDateFormat;

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

	public void init() { // des mediums avec plusieurs supports... que fait on ? string ou list<string>
		Medium medium1 = new Spirite("Boule de cristal", "Gwenaelle", false, "Spécialiste des grandes conversations au-delà de TOUTES les frontières.");
		Medium medium2 = new Spirite("Marc de café, boule de cristal, oreilles de lapin", "Professeur Tran", true, "Votre avenir est devant vous : regardons-le ensemble !");
		Medium medium3 = new Astrologue("École Normale Supérieure d’Astrologie (ENS-Astro)", "2006", "Serena", false, "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.");
		Medium medium4 = new Astrologue("Institut des Nouveaux Savoirs Astrologiques", "2010", "Mr M", true, "Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!");
		Medium medium5 = new Cartomancien("Mme Irma", false, "Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");
		Medium medium6 = new Cartomancien("Endora", false, "Mes cartes répondront à toutes vos questions personnelles.");

		Employe employe1 = new Employe("BORROTI MATIAS DANTAS", "Raphaël", true, "12345", "0328178508", "rborrotimatiasdantas4171@free.fr");
		Employe employe2 = new Employe("OLMEADA MARAIS", "Nor", false, "12345", "0418932546", "nolmeadamarais1551@gmail.com");
		Employe employe3 = new Employe("RAYES GEMEZ", "Olena", false, "12345", "0532731620", "orayesgemez5313@outlook.com");
		Employe employe4 = new Employe("SING", "Ainhoa", false, "12345", "0705224200", "asing8183@free.fr");
		Employe employe5 = new Employe("ABDIULLINA", "David Alexander", true, "12345", "0590232772", "david-alexander.abdiullina@laposte.net");

		JpaUtil.creerContextePersistance();

		try {
			JpaUtil.ouvrirTransaction();

			mediumDao.creer(medium1);
			mediumDao.creer(medium2);
			mediumDao.creer(medium3);
			mediumDao.creer(medium4);
			mediumDao.creer(medium5);
			mediumDao.creer(medium6);

			employeDao.creer(employe1);
			employeDao.creer(employe2);
			employeDao.creer(employe3);
			employeDao.creer(employe4);
			employeDao.creer(employe5);

			JpaUtil.validerTransaction();
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
		JpaUtil.creerContextePersistance();
		try {
			System.out.println(client.getMail());
			Employe employe = employeDao.chercherParMail(client.getMail());
			System.out.println(employe);
			if (employe == null){
				list = astroApi.getProfil(client.getPrenom(), client.getDateDeNaissance());
				client.setProfilAstral(new ProfilAstral(list.get(0), list.get(1), list.get(2), list.get(3)));

				JpaUtil.ouvrirTransaction();

				profilAstralDao.creer(client.getProfilAstral());
				clientDao.creer(client);

				JpaUtil.validerTransaction();

				Message.envoyerMail(
					UtilMessage.getMailExpediteur(),
					client.getMail(),
					UtilMessage.getMailObjetSucces(),
					UtilMessage.getMailCorpsSucces(client.getPrenom())
				);

				id = client.getId();
			}else{
				throw new Exception("Impossible d'inscrire le client");
			}
		}
		catch (Exception ex) {
			Message.envoyerMail(
				UtilMessage.getMailExpediteur(),
				client.getMail(),
				UtilMessage.getMailObjetEchec(),
				UtilMessage.getMailCorpsEchec(client.getPrenom())
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

	public Employe authentifierEmploye(String mail, String mdp) {
		Employe resultat = null;
		JpaUtil.creerContextePersistance();
		try {
			Employe employe = employeDao.chercherParMail(mail);
			if (employe != null) {
				if (employe.getMotDePasse().equals(mdp)) {
					resultat = employe;
				}
			}
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
		JpaUtil.creerContextePersistance();
		try {
			client = clientDao.chercherParId(id);
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
		JpaUtil.creerContextePersistance();
		try {
			clients = clientDAO.chercherTousOrdonnee();
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

	public Medium trouverMediumParId(Long id) {
		Medium medium;
		JpaUtil.creerContextePersistance();
		try {
			medium = mediumDao.chercherParId(id);
		}
		catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
			medium = null;
		}
		finally {
			JpaUtil.fermerContextePersistance();
		}
		return medium;
	}

	public List<Medium> listerMediums() {
		List<Medium> mediums;
		JpaUtil.creerContextePersistance();
		try{
			mediums = mediumDao.chercherTous();
		}
		catch(Exception ex){
			mediums = null;
		}
		finally{
			JpaUtil.fermerContextePersistance();
		}
		return mediums;
	}

	public List<Employe> listerEmployes() {
		List<Employe> employes;
		JpaUtil.creerContextePersistance();
		try{
			employes = employeDao.chercherTous();
		}
		catch(Exception ex){
			employes = null;
		}
		finally{
			JpaUtil.fermerContextePersistance();
		}
		return employes;
	}

	public List<Medium> listerMediumsParGenre(Boolean genre) {
		List<Medium> mediums;
		JpaUtil.creerContextePersistance();
		try{
			mediums = mediumDao.chercherParGenre(genre);
		}
		catch(Exception ex){
			mediums = null;
		}
		finally{
			JpaUtil.fermerContextePersistance();
		}
		return mediums;
	}

	public Consultation obtenirConsultation(Client client, Medium medium) {
		JpaUtil.creerContextePersistance();

		StringWriter message = new StringWriter();
		PrintWriter notificationWriter = new PrintWriter(message);
		Consultation consultation;

		try {
			Employe employe = employeDao.chercherParGenreEtDisponibilite(medium.getGenre());
			// Employe employe = employeDao.chercherParId(10L);

			consultation = new Consultation(medium, client, employe);
			employe.setEstDisponible(false);

			notificationWriter.print("Pour : " + employe.getPrenom() + " " + employe.getNom().toUpperCase());
			notificationWriter.println(", Tel : " + employe.getNumeroDeTelephone());
			notificationWriter.print("Message : Bonjour " + employe.getPrenom() + ".");
			notificationWriter.print("Consultation requise pour " + client.getPrenom() + " ");
			notificationWriter.println(client.getNom().toUpperCase() + ". Médium à incarner : " + medium.getDenomination());

			JpaUtil.ouvrirTransaction();
			consultationDao.creer(consultation);
			client.ajouterConsultation(consultation);

			employeDao.modifier(employe);
			JpaUtil.validerTransaction();

			Message.envoyerNotification(
				employe.getNumeroDeTelephone(),
				UtilMessage.getNotification(employe.getPrenom(),
						employe.getNom(),
						employe.getNumeroDeTelephone(),
						client.getGenre(),
						client.getPrenom(),
						client.getNom(),
						medium.getDenomination())
			);
		}
		catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
			consultation = null;
			JpaUtil.annulerTransaction();
		}
		finally {
			JpaUtil.fermerContextePersistance();
		}
		return consultation;
	}

	public Consultation debuterConsultation(Consultation consultation) {
		JpaUtil.creerContextePersistance();
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//simpleDateFormat.parse(dateDebut);
		consultation.setDateDebut(new Date());
		try {
			JpaUtil.ouvrirTransaction();
			consultationDao.modifier(consultation);
			JpaUtil.validerTransaction();
		}
		catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
			consultation = null;
			JpaUtil.annulerTransaction();
		}
		finally {
			JpaUtil.fermerContextePersistance();
		}
		return consultation;
	}

	public Consultation finirConsultation(Consultation consultation) {
		JpaUtil.creerContextePersistance();
		consultation.getClient().getConsultations().add(consultation);
		consultation.getEmploye().setNbConsultations(consultation.getEmploye().getNbConsultations()+1);
		consultation.getMedium().setNbConsultations(consultation.getMedium().getNbConsultations()+1);
		consultation.setDuree((new Date()).getTime() - consultation.getDateDebut().getTime());
		consultation.getEmploye().setEstDisponible(true);
		try {
			JpaUtil.ouvrirTransaction();
			employeDao.modifier(consultation.getEmploye());
			clientDao.modifier(consultation.getClient());
			mediumDao.modifier(consultation.getMedium());
			consultationDao.modifier(consultation);

			JpaUtil.validerTransaction();
		}
		catch (Exception ex) {
			JpaUtil.annulerTransaction();
			consultation = null;
		}
		finally {
			JpaUtil.fermerContextePersistance();
		}
		return consultation;
	}

	public Consultation commenterConsultation(Consultation consultation, String commentaire) {
		//Commenter la consultation (par un employé apres la fin d'une consultation)
		JpaUtil.creerContextePersistance();
		consultation.setCommentaire(commentaire);
		try {
			JpaUtil.ouvrirTransaction();
			consultationDao.modifier(consultation);
			JpaUtil.validerTransaction();
		}
		catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
			consultation = null;
			JpaUtil.annulerTransaction();
		}
		finally {
			JpaUtil.fermerContextePersistance();
		}
		return consultation;
	}

	public Consultation noterConsultation(Consultation consultation, int note) {
		JpaUtil.creerContextePersistance();
		consultation.setNote(note);
		try {
			JpaUtil.ouvrirTransaction();
			consultationDao.modifier(consultation);
			JpaUtil.validerTransaction();
		}
		catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
			consultation = null;
			JpaUtil.annulerTransaction();
		}
		finally {
			JpaUtil.fermerContextePersistance();
		}
		return consultation;
	}

	public List<String> obtenirPredictions(Client client, int amour, int sante, int travail) {

		List<String> predictions;
		try{
			predictions = astroApi.getPredictions(client.getProfilAstral().getCouleurPorteBonheur(),
												client.getProfilAstral().getAnimalTotem(),
												amour,
												sante,
												travail);
		}
		catch (IOException ex){
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
			predictions = null;
		}
		return predictions;
	}

	public Consultation trouverConsultationParId(Long id) {
		Consultation consultation;
		JpaUtil.creerContextePersistance();
		try {
			consultation = consultationDao.chercherParId(id);
		}
		catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
			consultation = null;
		}
		finally {
			JpaUtil.fermerContextePersistance();
		}
		return consultation;
	}

	public Employe trouverEmployeParId(Long id) {
		Employe employe;
		JpaUtil.creerContextePersistance();
		try {
			employe = employeDao.chercherParId(id);
		}
		catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
			employe = null;
		}
		finally {
			JpaUtil.fermerContextePersistance();
		}
		return employe;
	}

	public Consultation obtenirConsultationAffectee(Employe employe) {
		Consultation consultation = null;
		if (!employe.getEstDisponible()) {

			JpaUtil.creerContextePersistance();
			try {
				List<Consultation> listeDesConsultations = consultationDao.chercherParIdEmployeEnCours(employe.getId());
				consultation = listeDesConsultations.get(0);
			}
			catch (Exception ex) {
				Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
				consultation = null;
			}
			finally {
				JpaUtil.fermerContextePersistance();
			}
		}
		return consultation;
	}

	public List<Medium> obtenirTop5Mediums() {
		List<Medium> liste;
		JpaUtil.creerContextePersistance();
		try {
			liste = mediumDao.chercherTopParNbConsultations(5);
		}
		catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
			liste = null;
		}
		finally {
			JpaUtil.fermerContextePersistance();
		}
		return liste;
	}

	public List<Employe> obtenirTop5Employes() {
		List<Employe> liste;
		JpaUtil.creerContextePersistance();
		try {
			liste = employeDao.chercherTopParNbConsultations(5);
		}
		catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
			liste = null;
		}
		finally {
			JpaUtil.fermerContextePersistance();
		}
		return liste;
	}

	public String[][] obtenirNombreDeConsultationsParMedium(){
		List<Medium> mediums = listerMediums();
		String[][] nbConsultationsParMedium = new String[mediums.size()][3];
		for (int i = 0; i < mediums.size(); ++i) {
			String classNameMedium = mediums.get(i).getClass().getName();
			nbConsultationsParMedium[i][0] = classNameMedium.substring(classNameMedium.lastIndexOf('.') + 1);
			nbConsultationsParMedium[i][1] = mediums.get(i).getDenomination();
			nbConsultationsParMedium[i][2] = String.valueOf(mediums.get(i).getNbConsultations());
		}
		return nbConsultationsParMedium;
	}

	public String[][] obtenirNombreDeConsultationsParEmploye(){
		List<Employe> employes = listerEmployes();
		String[][] nbConsultationsParEmploye = new String[employes.size()][3];
		for (int i = 0; i < employes.size(); ++i) {
			nbConsultationsParEmploye[i][0] = employes.get(i).getNom();
			nbConsultationsParEmploye[i][1] = employes.get(i).getPrenom();
			nbConsultationsParEmploye[i][2] = String.valueOf(employes.get(i).getNbConsultations());
		}
		return nbConsultationsParEmploye;
	}

	public List<Consultation> obtenirHistorique(Client client) {
		List<Consultation> historique;
		JpaUtil.creerContextePersistance();
		try {
			historique = consultationDao.chercherConsultationsParClient(client.getId());
		}
		catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
			historique = null;
		}
		finally {
			JpaUtil.fermerContextePersistance();
		}
		return historique;
	}

	public List<Consultation> obtenirHistorique(Employe employe) {
		List<Consultation> historique;
		JpaUtil.creerContextePersistance();
		try {
			historique = consultationDao.chercherParIdEmploye(employe.getId());
		}
		catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
			historique = null;
		}
		finally {
			JpaUtil.fermerContextePersistance();
		}
		return historique;
	}

	public List<Medium> listerMediumsDisponibles(Integer quantite) {
		List<Medium> mediums;
		JpaUtil.creerContextePersistance();
		try {
			mediums = mediumDao.chercherMediums(quantite);
		}
		catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !", ex);
			mediums = null;
		}
		finally {
			JpaUtil.fermerContextePersistance();
		}
		return mediums;
	}
}
