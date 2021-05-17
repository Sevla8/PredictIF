
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Benoit
 */
public class InscrireClientSerialisation extends Serialisation {

   
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container=new JsonObject();

        Boolean inscription=(Boolean) request.getAttribute("inscription");
        
        container.addProperty("inscription",inscription);  
        
        response.setContentType("application/json;charset=UTF -8");
        PrintWriter  out = this.getWriter (response);
        Gson  gson = new  GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container , out);
        out.close();
    }
    
}
