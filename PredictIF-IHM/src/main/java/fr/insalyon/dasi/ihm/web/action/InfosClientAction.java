/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Benoit
 */
public class InfosClientAction extends Action{


    @Override
    public void executer(HttpServletRequest request) {
        //Initialisation de la session
        HttpSession session=request.getSession(true);
        
        //Récupération des paramètres
        Long sessionUser = (Long) session.getAttribute ("idUser");
        
        //Appel au service
        Service service=new Service();
        
        if(sessionUser!=null){
            //Récupération du client
            Client client=service.trouverClientParId(sessionUser);

            //Enregistrement des paramètres dans l'url
            request.setAttribute("connexion",true);
            request.setAttribute("client",client);
        }else{
            request.setAttribute("connexion",false);
        }
    }
    
}
