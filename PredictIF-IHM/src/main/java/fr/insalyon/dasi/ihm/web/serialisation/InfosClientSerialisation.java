/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Benoit
 */
public class InfosClientSerialisation extends Serialisation {

   
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container=new JsonObject();

        Boolean connexion=(Boolean) request.getAttribute("connexion");
        
        if(connexion)
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
        
        
        container.addProperty("connecte",connexion);        
        
        response.setContentType("application/json;charset=UTF -8");
        try (PrintWriter out = this.getWriter (response)) {
            Gson  gson = new  GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container , out);
        }
    }
    
}