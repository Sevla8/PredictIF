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
import static java.lang.System.out;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gdela
 */
public class FinirConsultationSerialisation extends Serialisation{
   @Override
   public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

       boolean debut = (boolean) request.getAttribute("fin");

        JsonObject container = new JsonObject () ; // Objet JSON " conteneur "
        container.addProperty("fin", debut);

        try (PrintWriter sortie = response.getWriter()){
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create() ;
            gson.toJson(container,sortie );
            out.close();
        }
    }
}