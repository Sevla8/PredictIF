/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author B3202-B3205
 */
public class ObtenirPredictionsAction extends Action {

    private final Service service = new Service();

    @Override
    public void executer(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long sessionId = (Long) session.getAttribute("id");
        Long clientId = (Long) request.getAttribute("clientId");
        System.out.println("id du client "+clientId);
        if (sessionId == null) {
            request.setAttribute("ok", false);
        }
        else {
            String amour = request.getParameter("amour");
            String sante = request.getParameter("sante");
            String travail = request.getParameter("travail");

            List<String> prediction = service.obtenirPredictions(
                    service.trouverClientParId(clientId),
                    Integer.parseInt(amour),
                    Integer.parseInt(sante),
                    Integer.parseInt(travail)
            );

            request.setAttribute("ok", true);
            request.setAttribute("prediction", prediction);
        }
    }
}
