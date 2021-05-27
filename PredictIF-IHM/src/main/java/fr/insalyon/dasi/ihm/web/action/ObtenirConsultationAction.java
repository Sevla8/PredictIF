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
        
        Long sessionId = (Long) session.getAttribute("idUser");
        Long idMedium = Long.parseLong(request.getParameter("idMedium"));
        
        Service service = new Service();
      
        if (sessionId == null) {
            request.setAttribute("ok", false);
        }
        
        else {
            Client client = service.trouverClientParId(sessionId);
            Medium medium = service.trouverMediumParId(idMedium);
            Consultation consultation = service.obtenirConsultation(client, medium);
            
            request.setAttribute("ok", true);
            request.setAttribute("consultation", consultation);
        }
    }
}
