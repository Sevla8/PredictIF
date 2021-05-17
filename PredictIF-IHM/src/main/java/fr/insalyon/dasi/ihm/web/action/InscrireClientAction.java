/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;

/**
 *
 * @author Benoit
 */
public class InscrireClientAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("inscription en cours");
        String nom = request.getParameter ("nom");
        System.out.println("nom="+nom);

        String prenom = request.getParameter ("prenom") ;
        System.out.println("prenom="+prenom);
        
        String adresse = request.getParameter ("adresse") ;
        System.out.println("adresse="+adresse);
        
        String tel = request.getParameter ("telephone") ;
        System.out.println("tel="+tel);

        String dateNaissanceRecue=request.getParameter ("dateDeNaissance") ;
        Date dateDeNaissance=new Date();
        try{
            dateDeNaissance =new SimpleDateFormat("yyyy-MM-dd").parse(dateNaissanceRecue);

            System.out.println("dateDeNaissance="+dateDeNaissance);
        }catch(Exception e){
            e.printStackTrace();
            dateDeNaissance=null;
        }

        String mail = request.getParameter ("mail");
        System.out.println("mail="+mail);

        String password = request.getParameter ("password");
        System.out.println("password="+password);

        Boolean sexe=Boolean.parseBoolean(request.getParameter("sexe"));
        System.out.println("sexe="+sexe);
        
        Client nouveauClient=new Client(password, prenom, nom, adresse,tel, sexe,mail, dateDeNaissance);//dateDeNaissance);
        System.out.println(nouveauClient);

        Service service=new Service();
        
        Long resInscription=service.inscrireClient(nouveauClient);
        System.out.println(resInscription);
        if(resInscription!=null){
            request.setAttribute("inscription",true);
        }else{
            request.setAttribute("inscription",false);
        }
        
    }
    
}
