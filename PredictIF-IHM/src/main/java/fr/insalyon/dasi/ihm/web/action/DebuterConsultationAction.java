/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class DebuterConsultationAction extends Action{
    @Override
    public void executer(HttpServletRequest request){

        HttpSession session = request.getSession(true); // Initialisation Session
        if(session.getAttribute("id")!= null)
        {
            Service service = new Service();
            Long idConsult = Long.parseLong(request.getParameter("idConsultation"));
            Consultation consultation = service.trouverConsultationParId(idConsult);
            consultation = service.debuterConsultation(consultation);

            if(consultation != null){
                request.setAttribute("debut", true);
            }else{
                request.setAttribute("debut", false);
            }
        }
    }
}
