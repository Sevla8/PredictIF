package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author DASI Team
 */
public class Saisie {

    public static void main(String[] args) {
        JpaUtil.init();

//        Service serviceClient = new Service();
//
//        Integer option = -1;
//
//        while (option != 0) {
//            System.out.println("<0> quitter");
//            System.out.println("<1> inscrire client");
//            System.out.println("<2> rechercher client");
//            System.out.println("<3> lister clients");
//            System.out.println("<4> authentifier client");
//
//            option = Saisie.lireInteger("Option: ", Arrays.asList(0,1,2,3,4));
//
//            switch (option) {
//                case 1 -> {
//                    String nom = Saisie.lireChaine("nom: ");
//                    String prenom = Saisie.lireChaine("prenom: ");
//                    String mail = Saisie.lireChaine("mail: ");
//                    String mdp = Saisie.lireChaine("mot de passe: ");
//
//                    Client client = new Client(nom, prenom, mail, mdp);
//                    Client c = serviceClient.inscrireClient(client);
//
//                    if (c != null) {
//                        System.out.println("> Succès inscription");
//                        System.out.println("-> Client: id=" + c.getId() +
//                                ";nom=" + c.getNom() + ";prenom=" +
//                                c.getPrenom() + ";mail=" + c.getMail() +
//                                ";motDePasse=" + c.getMotDePasse());
//                    }
//                    else {
//                        System.out.println("> Echec inscription");
//                        System.out.println("-> Client: id=" + "0" + ";nom=" +
//                                client.getNom() + ";prenom=" + client.getPrenom()
//                                + ";mail=" + client.getMail() + ";motDePasse=" +
//                                client.getMotDePasse());
//                    }
//                    break;
//                }
//                case 2 -> {
//                    Long id = Long.valueOf(Saisie.lireInteger("id: "));
//
//                    Client client = serviceClient.trouverClientParId(id);
//
//                    if (client != null) {
//                        System.out.println("> Client trouve !");
//                        System.out.println("-> Client: id=" + client.getId() +
//                                ";nom=" + client.getNom() + ";prenom=" +
//                                client.getPrenom() + ";mail=" + client.getMail() +
//                                ";motDePasse=" + client.getMotDePasse());
//                    }
//                    else {
//                        System.out.println("> Client non trouve !");
//                    }
//                    break;
//                }
//                case 3 -> {
//                    List<Client> clients = serviceClient.listerTousClients();
//
//                    clients.forEach(c -> {
//                        System.out.println("-> Client: id=" + c.getId() +
//                                ";nom=" + c.getNom() + ";prenom=" +
//                                c.getPrenom() + ";mail=" + c.getMail() +
//                                ";motDePasse=" + c.getMotDePasse());
//                    });
//                    break;
//                }
//                case 4 -> {
//                    String mail = Saisie.lireChaine("mail: ");
//                    String mdp = Saisie.lireChaine("mot de passe: ");
//
//                    Boolean authentification = serviceClient.authentifierClient(mail, mdp);
//                    if (authentification) {
//                        System.out.println("> Authentification reussie !");
//                    }
//                    else {
//                        System.out.println("> Authentification faillie !");
//                    }
//                    break;
//                }
//            }
//        }
        JpaUtil.destroy();
    }

    public static String lireChaine(String invite) {
        String chaineLue = null;
        System.out.println(invite);
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            chaineLue = br.readLine();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
        return chaineLue;

    }

    public static Integer lireInteger(String invite) {
        Integer valeurLue = null;
        while (valeurLue == null) {
            try {
                valeurLue = Integer.parseInt(lireChaine(invite));
            } catch (NumberFormatException ex) {
                System.out.println("/!\\ Erreur de saisie - Nombre entier attendu /!\\");
            }
        }
        return valeurLue;
    }

    public static Integer lireInteger(String invite, List<Integer> valeursPossibles) {
        Integer valeurLue = null;
        while (valeurLue == null) {
            try {
                valeurLue = Integer.parseInt(lireChaine(invite));
            } catch (NumberFormatException ex) {
                System.out.println("/!\\ Erreur de saisie - Nombre entier attendu /!\\");
            }
            if (!(valeursPossibles.contains(valeurLue))) {
                System.out.println("/!\\ Erreur de saisie - Valeur non-autorisée /!\\");
                valeurLue = null;
            }
        }
        return valeurLue;
    }

    public static void pause() {
        lireChaine("--PAUSE--");
    }
}
