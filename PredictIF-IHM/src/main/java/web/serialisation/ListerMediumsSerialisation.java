/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.serialisation;

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
public class ListerMediumsSerialisation extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
        JsonObject container = new JsonObject();
    
        List<Medium> listeMediums = (List<Medium>)request.getAttribute("mediums");
        
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

        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
