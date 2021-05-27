/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gdela
 */
public class PreparerConsultationSerialisation extends Serialisation{
   @Override
   public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

       boolean ConsultAFaire = (boolean) request.getAttribute("ConsultationEnCour");
       if(ConsultAFaire){

            Consultation consultation = (Consultation) request.getAttribute("consultation");
            List<Consultation> historique = (List<Consultation>) request.getAttribute("historique");

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            
            Client client = consultation.getClient();
            String dateStr = dateFormat.format(client.getDateDeNaissance());

            JsonObject container = new JsonObject () ; // Objet JSON " conteneur "
            container.addProperty ("consultationEnCours", true);
            container.addProperty ("idConsultation", consultation.getId());
            container.addProperty ("ClientId", client.getId());

            JsonObject jsonClient = new JsonObject () ;
            jsonClient.addProperty ("nom", client.getNom () );
            jsonClient.addProperty ("prenom", client.getPrenom () );
            jsonClient.addProperty ("mail", client.getMail () );
            jsonClient.addProperty ("adressePostale", client.getAdressePostale () );
            jsonClient.addProperty ("dateNaissance", dateStr );
            jsonClient.addProperty ("signeZodiaque", client.getProfilAstral().getSigneZoodiaque());
            jsonClient.addProperty ("signeAstroChinois", client.getProfilAstral().getSigneAstrologiqueChinois());
            jsonClient.addProperty ("couleurPB",  client.getProfilAstral().getCouleurPorteBonheur());
            jsonClient.addProperty ("animalTotem", client.getProfilAstral().getAnimalTotem() );
            container.add ("client", jsonClient );

            JsonArray consultations = new JsonArray();
            for(Consultation consult : historique ){
                JsonObject jsonConsult = new JsonObject();
                jsonConsult.addProperty ("medium", consult.getMedium().getDenomination() );
                if (consult.getDateDebut () == null) {
                    jsonConsult.addProperty ("date", "null");
                }
                else {
                    jsonConsult.addProperty ("date", dateFormat.format(consult.getDateDebut ()) );
                }
                jsonConsult.addProperty ("note", consult.getCommentaire () );
                consultations.add(jsonConsult);
            }
            container.add ("historique", consultations);

            Medium medium = consultation.getMedium();
            String specialite = medium.getClass().getName();
            specialite = specialite.substring(specialite.lastIndexOf('.')+1);

            JsonObject jsonMedium = new JsonObject () ;
            jsonMedium.addProperty ("denomination", medium.getDenomination () );
            jsonMedium.addProperty ("presentation", medium.getPresentation () );
            jsonMedium.addProperty ("specialite",specialite );
            jsonMedium.addProperty ("note","5" );
            container.add ("medium", jsonMedium );

            try (PrintWriter sortie = response.getWriter()){
                Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create() ;
                gson.toJson(container,sortie );
                out.close();
            }
       }else{
           JsonObject container = new JsonObject () ; // Objet JSON " conteneur "
           container.addProperty ("consultationEnCours", false);

           try (PrintWriter sortie = this.getWriter(response)){
                Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create() ;
                gson.toJson(container,sortie );
                out.close();
            }
       }

    }
}