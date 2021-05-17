/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Benoit
 */
public class StatistiquesAgenceAction extends Action{


    @Override
    public void executer(HttpServletRequest request) {
        //Initialisation de la session
        HttpSession session=request.getSession(true);
        
        //Récupération des paramètres
        Long sessionUser = (Long) session.getAttribute("id");
        //sessionUser=12L;
        System.out.println("sessionUser="+sessionUser);

        
        //Appel au service
        Service service=new Service();
        
        List<Medium> listeTop5Medium=service.obtenirTop5Mediums();
        String [] top5Mediums=new String[5];
        for(int i=0;i<listeTop5Medium.size();i++){
            top5Mediums[i]=listeTop5Medium.get(i).getDenomination();
            System.out.println("medium "+i+": "+top5Mediums[i]);
        }
        List<Employe> listeTop5Employe=service.obtenirTop5Employes();
        String [] top5Employes=new String[5];
        for(int i=0;i<listeTop5Employe.size();i++){
            top5Employes[i]=listeTop5Employe.get(i).getPrenom();
            System.out.println("employe "+i+": "+top5Employes[i]);
        }
        
        String [][] consultationsParMedium=service.obtenirNombreDeConsultationsParMedium();
        for(int i=0;i<consultationsParMedium.length;i++){
            System.out.println("medium "+consultationsParMedium[i][1]+": "+consultationsParMedium[i][2]);
        }
        String [][] consultationsParEmploye=service.obtenirNombreDeConsultationsParEmploye();
        
        if(sessionUser!=null){
            //Enregistrement des paramètres dans l'url
            request.setAttribute("connexion",true);
            request.setAttribute("top5Mediums",top5Mediums);
            request.setAttribute("top5Employes",top5Employes);
            request.setAttribute("consultationsParMedium",consultationsParMedium);
            request.setAttribute("consultationsParEmploye",consultationsParEmploye);
        }else{
            request.setAttribute("connexion",false);
        }
    }
    
}
