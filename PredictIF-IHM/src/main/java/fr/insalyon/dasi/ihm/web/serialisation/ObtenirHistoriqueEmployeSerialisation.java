/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Consultation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author B3202-B3205
 */
public class ObtenirHistoriqueEmployeSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        JsonObject container = new JsonObject(); 
        
        List<Consultation> historique = (List<Consultation>) request.getAttribute("historique");
        
        JsonArray jsonListeConsultations = new JsonArray();
        for (Consultation consultation : historique) {
            JsonObject jsonConsultation = new JsonObject();
            jsonConsultation.addProperty("mediumDenomination", consultation.getMedium().getDenomination());
            jsonConsultation.addProperty("date", consultation.getDateDebut().toString());
            jsonConsultation.addProperty("commentaire", consultation.getCommentaire());
            jsonListeConsultations.add(jsonConsultation);
        }
        container.add("consultations", jsonListeConsultations);
        container.addProperty("ok", (Boolean) request.getAttribute("ok"));

        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
