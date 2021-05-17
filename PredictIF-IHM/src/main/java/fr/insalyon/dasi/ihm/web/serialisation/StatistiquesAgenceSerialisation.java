package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Benoit
 */
public class StatistiquesAgenceSerialisation extends Serialisation {

   
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container=new JsonObject();

        Boolean connexion=(Boolean) request.getAttribute("connexion");
        
        String[] top5Mediums=(String[]) request.getAttribute("top5Mediums");
        String [] top5Employes= (String[]) request.getAttribute("top5Employes");
        String [][] consultationsParMedium=  (String[][]) request.getAttribute("consultationsParMedium");
        String [][] consultationsParEmploye= (String[][]) request.getAttribute("consultationsParEmploye");
        
        container.addProperty("connecte",connexion);  
        
        //Création d'un tableau pour le top 5 des médiums
        JsonArray jsonTop5Mediums=new JsonArray();
        
        for(int i=0;i<top5Mediums.length;i++){
            JsonObject jsonMedium=new JsonObject();//Création d'un objet json pour un nom de médium
            //Ajout de propriété à ce nouvel objet json
            jsonMedium.addProperty("medium",top5Mediums[i]);
            jsonTop5Mediums.add(jsonMedium);
        }
        container.add("top5Mediums", jsonTop5Mediums);
        
        //Création d'un tableau pour le top 5 des employés
        JsonArray jsonTop5Employes=new JsonArray();
        
        for(int i=0;i<top5Employes.length;i++){
            JsonObject jsonEmploye=new JsonObject();//Création d'un objet json pour un nom d'employé
            //Ajout de propriété à ce nouvel objet json
            jsonEmploye.addProperty("employe",top5Employes[i]);
            jsonTop5Employes.add(jsonEmploye);
        }
        container.add("top5Employes", jsonTop5Employes);
        
        //Création d'un tableau pour le nombre de consultations par médiums
        JsonArray jsonConsultationsParMedium=new JsonArray();
        
        for(int i=0;i<consultationsParMedium.length;i++){
            JsonObject jsonConsultationMedium=new JsonObject();//Création d'un objet json pour un medium
            //Ajout de propriété à ce nouvel objet json
            jsonConsultationMedium.addProperty("medium",consultationsParMedium[i][1]);
            jsonConsultationMedium.addProperty("nombreConsultations",Integer.parseInt(consultationsParMedium[i][2]));
            jsonConsultationsParMedium.add(jsonConsultationMedium);
        }
        container.add("nombreConsultationsParMedium", jsonConsultationsParMedium);
        
        //Création d'un tableau pour le nombre de consultations par employes
        JsonArray jsonConsultationsParEmploye=new JsonArray();
        
        for(int i=0;i<consultationsParEmploye.length;i++){
            JsonObject jsonConsultationEmploye=new JsonObject();//Création d'un objet json pour un employe
            //Ajout de propriété à ce nouvel objet json
            jsonConsultationEmploye.addProperty("employe",consultationsParEmploye[i][1]);
            jsonConsultationEmploye.addProperty("nombreConsultations",Integer.parseInt(consultationsParEmploye[i][2]));
            jsonConsultationsParEmploye.add(jsonConsultationEmploye);
        }
        container.add("nombreConsultationsParEmploye", jsonConsultationsParEmploye);
        
        response.setContentType("application/json;charset=UTF -8");
        PrintWriter  out = this.getWriter (response);
        Gson  gson = new  GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container , out);
        out.close();
    }
    
}