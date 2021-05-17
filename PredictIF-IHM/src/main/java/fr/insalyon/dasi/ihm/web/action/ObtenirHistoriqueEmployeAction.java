/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author B3202-B3205
 */
public class ObtenirHistoriqueEmployeAction extends Action {

    private final Service service = new Service();
    
    @Override
    public void executer(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long sessionId = (Long) session.getAttribute("id");

        if (sessionId == null) {
            request.setAttribute("ok", false);
        } 
        else {
            Employe employe = service.trouverEmployeParId(sessionId);
            List<Consultation> historique = service.obtenirHistorique(employe);

            request.setAttribute("ok", true);
            request.setAttribute("historique", historique);
        }
    }
}
