/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Consultation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jadep
 */
public class ObtenirConsultationSerialisation extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
        JsonObject container = new JsonObject();
    
        Consultation consultation = (Consultation)request.getAttribute("consultation");
        boolean connecte = (boolean) request.getAttribute("ok");
        
        container.addProperty("connecte", connecte);
        
        if(consultation != null){
            container.addProperty("consultation", true);
        }
        else{
            container.addProperty("consultation", false);
        }

        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container, out);
        }
    }
    
}
