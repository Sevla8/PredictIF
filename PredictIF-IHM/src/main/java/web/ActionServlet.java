package web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fr.insalyon.dasi.dao.JpaUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.action.Action;
import web.action.AuthentifierEmployeAction;
import web.action.CommenterConsultationAction;
import web.action.NoterConsultationAction;
import web.action.ObtenirHistoriqueEmployeAction;
import web.action.ObtenirPredictionsAction;
import web.serialisation.AuthentifierEmployeSerialisation;
import web.serialisation.CommenterConsultationSerialisation;
import web.serialisation.NoterConsultationSerialisation;
import web.serialisation.ObtenirHistoriqueEmployeSerialisation;
import web.serialisation.ObtenirPredictionsSerialisation;
import web.serialisation.Serialisation;

/**
 *
 * @author B3202-B3205
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getSession(true);
        response.setCharacterEncoding("UTF-8");
        String todo = request.getParameter("todo");
        
        Action action = null;
        Serialisation serialisation = null;
        
        switch (todo) {
            case "authentificationEmploye": {
                action = new AuthentifierEmployeAction();
                serialisation = new AuthentifierEmployeSerialisation();
            }
            break;
            
            case "obtenirHistoriqueEmploye": {
                action = new ObtenirHistoriqueEmployeAction();
                serialisation = new ObtenirHistoriqueEmployeSerialisation();
            }
            break;
<<<<<<< HEAD
            
            case "obtenirPredictions": {
                action = new ObtenirPredictionsAction();
                serialisation = new ObtenirPredictionsSerialisation();
            }
            break;
            
            case "noterConsultation": {
                action = new NoterConsultationAction();
                serialisation = new NoterConsultationSerialisation();
            }
            break;
            
            case "commenterConsultation": {
                action = new CommenterConsultationAction();
                serialisation = new CommenterConsultationSerialisation();
=======

            case"nos-mediums":{ 
                action = new ListerMediumsAction();
                serialisation = new ListerMediumsSerialisation();
>>>>>>> 9a717bed87739f521327e8a3660c571e0abaa7d6
            }
            break;
        }
    
        if (action != null && serialisation != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        }
<<<<<<< HEAD
        
        else {
            response.sendError(400, "Bad Request");
        }

=======
        else{
            response.sendError(400, "Bad Request (pas d'Action ou de Serialisation pour traiter cette requete)");
        }
>>>>>>> 9a717bed87739f521327e8a3660c571e0abaa7d6
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
