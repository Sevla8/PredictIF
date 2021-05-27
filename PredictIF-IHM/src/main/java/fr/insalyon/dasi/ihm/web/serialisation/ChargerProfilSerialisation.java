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
public class ChargerProfilSerialisation extends Serialisation{
   @Override
   public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonObject container = new JsonObject () ; // Objet JSON " conteneur "
                  
       if(request.getAttribute("client") != null){
            Client client = (Client)request.getAttribute("client");
            List<Consultation> historique = (List<Consultation>) request.getAttribute("historique");
            
            container.addProperty ("connecte", true );
            
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String dateStr = dateFormat.format(client.getDateDeNaissance());

             JsonObject jsonClient = new JsonObject () ;
             jsonClient.addProperty ("id", client.getId () );
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
                 String dateconsult;
                 if(consult.getDateDebut () != null)
                 {
                     dateconsult = dateFormat.format(consult.getDateDebut ()) ;
                 }else{
                     dateconsult = "null";
                 }
                 
                 JsonObject jsonConsult = new JsonObject();
                 jsonConsult.addProperty ("medium", consult.getMedium().getDenomination() );
                 jsonConsult.addProperty ("date", dateconsult);
                 jsonConsult.addProperty ("note", consult.getNote () );
                 jsonConsult.addProperty ("idConsult", consult.getId () );
                 consultations.add(jsonConsult);
             }
             container.add("consultations", consultations);
        }else{
           container.addProperty ("connecte", false );
       }
           
        try (PrintWriter sortie = this.getWriter(response)){
              Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create() ;
              gson.toJson(container,sortie );
              out.close();
          }
    }
}