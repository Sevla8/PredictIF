/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.service.Service;

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
            request.setAttribute("id",client.getId());
            request.setAttribute("nom",client.getNom());
            request.setAttribute("prenom",client.getPrenom());
            request.setAttribute("mail",client.getMail());
            
            //Enregistrement dans la session
            session.setAttribute("idUser",client.getId());
            System.out.println("session initialisée");
        }else{
            request.setAttribute("connexion",false);
        }
    }
    
}
