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
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jadep
 */
public class ListerMediumsSerialisation extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
        JsonObject container = new JsonObject();
        boolean connecte = (boolean) request.getAttribute("connecte");
        List<Medium> listeMediums = (List<Medium>)request.getAttribute("mediums");
        
        container.addProperty("connecte", connecte);
        
        if(connecte)
        {
            Client client=(Client) request.getAttribute("client");
            JsonObject infosClients=new JsonObject();//Création d'un objet json pour un client
            //Ajout de propriété à ce nouvel objet json
            infosClients.addProperty("nom",client.getNom());
            infosClients.addProperty("prenom",client.getPrenom());
            SimpleDateFormat formater=new SimpleDateFormat("dd/MM/yy");
            infosClients.addProperty("date",formater.format(client.getDateDeNaissance()));

            container.add("client", infosClients);
        }
        
        
        container.addProperty("nb-mediums", listeMediums.size());
        JsonArray jsonListeMediums = new JsonArray();
        for (Medium medium : listeMediums){

            JsonObject jsonMedium = new JsonObject();

            jsonMedium.addProperty("id", medium.getId());
            jsonMedium.addProperty("genre", medium.getGenre());
            jsonMedium.addProperty("denomination", medium.getDenomination());
            jsonMedium.addProperty("nb-consultations", medium.getNbConsultations());
            jsonMedium.addProperty("presentation", medium.getPresentation());
            
            jsonListeMediums.add(jsonMedium);
        }

        container.add("mediums", jsonListeMediums);

        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container, out);
        }
    }
    
}
