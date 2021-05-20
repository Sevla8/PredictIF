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
 * @author B3202-B3205
 */
public class NoterConsultationSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        JsonObject container = new JsonObject();

        Consultation consultation = (Consultation) request.getAttribute("consultation");

        container.addProperty("mediumDenomination", consultation.getMedium().getDenomination());
        container.addProperty("date", consultation.getDateDebut().toString());
        container.addProperty("note", consultation.getNote());

		PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
