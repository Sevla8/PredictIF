/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ChargerProfilAction extends Action{
    @Override
    public void executer(HttpServletRequest request){

        HttpSession session = request.getSession(true); // Initialisation Session
        Long sessionUser=(Long) session.getAttribute("idUser");
        if(sessionUser!= null)
        {
            Service service = new Service();
            Client client = service.trouverClientParId(sessionUser);
            List<Consultation> historique = service.obtenirHistorique(client);
            request.setAttribute("client", client);
            request.setAttribute("historique", historique);
        }else{
            request.setAttribute("client", null);
        }
    }
}
