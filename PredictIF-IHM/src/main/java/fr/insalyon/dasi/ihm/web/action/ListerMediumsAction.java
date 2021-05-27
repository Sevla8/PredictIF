/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jadep
 */
public class ListerMediumsAction extends Action{
    
    @Override
    public void executer(HttpServletRequest request){
        
        
        HttpSession session=request.getSession(true);
        Service service = new Service();
        Long idUser = (Long) session.getAttribute("idUser");
        
        if(idUser != null)
        {
            request.setAttribute("connecte",true);
            Client client=service.trouverClientParId(idUser);
            request.setAttribute("client",client);
            
        }else{
            request.setAttribute("connecte",false);            
        }
        List<Medium> listeMediums = service.listerMediums();
        
        //System.out.println("liste des mediums = " + mediums);
        request.setAttribute("mediums", listeMediums);
    }
}
