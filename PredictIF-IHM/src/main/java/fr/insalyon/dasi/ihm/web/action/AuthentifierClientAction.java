/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;

/**
 *
 * @author Benoit
 */
public class AuthentifierClientAction extends Action{


    @Override
    public void executer(HttpServletRequest request) {
        //Initialisation de la session
        HttpSession session=request.getSession(true);
        
        //Récupération des paramètres
        String login = request.getParameter ("login");
        System.out.println("login="+login);

        String password = request.getParameter ("password") ;
        System.out.println("password="+password);
        
        //Appel au service
        Service service=new Service();
        
        //Authentification du client
        Client client=service.authentifierClient(login,password);
        System.out.println("Affichage du client : "+client);
        
        if(client!=null){
            //Enregistrement des paramètres dans l'url
            request.setAttribute("connexion",true);
            
            //Enregistrement dans la session
            session.setAttribute("idUser",client.getId());
            System.out.println("session initialisée");
        }else{
            request.setAttribute("connexion",false);
        }
    }
    
}
