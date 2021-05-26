/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jadep
 */
public class ObtenirConsultationAction extends Action{
    @Override
    public void executer(HttpServletRequest request){
        
        HttpSession session = request.getSession(true);
        
//        Long sessionId = (Long) session.getAttribute("idUser");
        Long sessionId = 13L;

        Long idMedium = Long.parseLong(request.getParameter("idMedium"));
        System.out.println("idMedium = " + idMedium);
        System.out.println();
        
        Service service = new Service();
//        
        if (sessionId == null) {
            request.setAttribute("ok", false);
            System.out.println("ça ne marche pas");
        }
        
        else {
            Client client = service.trouverClientParId(sessionId);
            System.out.println();
            System.out.println("client = " + client);
            System.out.println();
            
            Medium medium = service.trouverMediumParId(idMedium);
            System.out.println("medium = " + medium);
            Consultation consultation = service.obtenirConsultation(client, medium);
            System.out.println("consultation = " + consultation);
            System.out.println();
            
            request.setAttribute("consultation", consultation);
        }
    }
}
