/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

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
        Long sessionId = (Long) session.getAttribute("id");

        if (sessionId == null) {
            request.setAttribute("ok", false);
        }
        else {
            String consultation = request.getParameter("consultation");
            String note = request.getParameter("note");
            
            Consultation consult = service.noterConsultation(
                    service.trouverConsultationParId(Long.parseLong(consultation)),
                    Integer.parseInt(note)
            );

            request.setAttribute("ok", true);
            request.setAttribute("consultation", consult);
        }
    }
}