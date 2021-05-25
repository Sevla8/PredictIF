/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Benoit
 */
public class DeconnexionAction extends Action{
    @Override
    public void executer(HttpServletRequest request) {
        //Initialisation de la session
        HttpSession session=request.getSession(true);
        session.invalidate();
        request.setAttribute("connexion",false);
    }

}
