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
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jadep
 */
public class ListerMediumsDisposSerialisation extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
        JsonObject container = new JsonObject();
    
        List<Medium> listeMediumsDispos = (List<Medium>)request.getAttribute("mediums-dispos");
                
        JsonArray jsonListeMediumsDispos = new JsonArray();
        
        for (Medium medium : listeMediumsDispos){

            JsonObject jsonMediumDispo = new JsonObject();

            jsonMediumDispo.addProperty("id", medium.getId());
            jsonMediumDispo.addProperty("genre", medium.getGenre());
            jsonMediumDispo.addProperty("denomination", medium.getDenomination());
            jsonMediumDispo.addProperty("nbConsultations", medium.getNbConsultations());
            jsonMediumDispo.addProperty("presentation", medium.getPresentation());
            
            jsonListeMediumsDispos.add(jsonMediumDispo);
        }

        container.add("mediumsDispos", jsonListeMediumsDispos);

        try (PrintWriter out = this.getWriter(response)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container, out);
        }
    }
}
