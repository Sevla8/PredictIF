/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author B3202-B3205
 */
public class AuthentifierEmployeAction extends Action {
    
    private final Service service = new Service();
    
    @Override
    public void executer(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Employe employe = service.authentifierEmploye(login, password);
        
        if (employe != null) {
            request.setAttribute("ok", true);
      
            HttpSession session = request.getSession();
            session.setAttribute("id", employe.getId());
        }
        else {
            request.setAttribute("ok", false);
        }
    }
}
