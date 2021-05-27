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

/**
 *
 * @author B3202-B3205
 */
public class NoterConsultationAction extends Action {

    private final Service service = new Service();
    
    @Override
    public void executer(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long sessionId = (Long) session.getAttribute("idUser");

        if (sessionId == null) {
            request.setAttribute("ok", false);
        }
        else {
            Long consultation = Long.parseLong(request.getParameter("id"));
            int note = Integer.parseInt(request.getParameter("star"));
            
            
            Consultation consult = service.noterConsultation(
                    service.trouverConsultationParId(consultation), note );

            request.setAttribute("ok", true);
            request.setAttribute("consultation", consult);
        }
    }
}
