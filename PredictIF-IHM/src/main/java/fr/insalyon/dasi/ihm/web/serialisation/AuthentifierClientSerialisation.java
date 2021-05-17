/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Benoit
 */
public class AuthentifierClientSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container=new JsonObject();
        
        Boolean connexion=(Boolean) request.getAttribute("connexion");
        Long id=(Long) request.getAttribute("id");
        String nom= (String) request.getAttribute("nom");
        String prenom= (String) request.getAttribute("prenom");
        String mail= (String) request.getAttribute("mail");
        
        container.addProperty("connexion",connexion);       
        JsonObject proprieteClient=new JsonObject();
        proprieteClient.addProperty("id",id);
        proprieteClient.addProperty("nom",nom);
        proprieteClient.addProperty("prenom",prenom);
        proprieteClient.addProperty("mail",mail);
        container.add("client",proprieteClient);
        
        response.setContentType("application/json;charset=UTF -8");
        PrintWriter  out = this.getWriter (response);
        Gson  gson = new  GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container , out);
        out.close();
    }
    
}
