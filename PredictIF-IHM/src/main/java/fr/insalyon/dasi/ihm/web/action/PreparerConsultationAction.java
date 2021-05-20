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


public class PreparerConsultationAction extends Action{
    @Override
    public void executer(HttpServletRequest request){
        HttpSession session = request.getSession(true); // Initialisation Session

        Long employeID = (Long)session.getAttribute("id");

        if(employeID != null)
        {
            Service service = new Service();
            Employe employe = service.trouverEmployeParId(employeID);
            Consultation consultation = service.obtenirConsultationAffectee(employe);

            Long b = 64L;
            consultation = service.trouverConsultationParId(b);


            if(consultation != null)
            {
                List<Consultation> historique = service.obtenirHistorique(consultation.getClient());
                request.setAttribute("ConsultationEnCour", true);
                request.setAttribute("consultation", consultation);
                request.setAttribute("historique", historique);
            }else{
                request.setAttribute("ConsultationEnCour", false);
            }

        }
    }
}


