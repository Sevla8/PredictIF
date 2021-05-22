package fr.insalyon.dasi.ihm.web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.ihm.web.action.ListerMediumsAction;
import fr.insalyon.dasi.ihm.web.serialisation.ListerMediumsSerialisation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.insalyon.dasi.ihm.web.action.Action;
import fr.insalyon.dasi.ihm.web.action.AuthentifierClientAction;
import fr.insalyon.dasi.ihm.web.action.AuthentifierEmployeAction;
import fr.insalyon.dasi.ihm.web.action.ChargerProfilAction;
import fr.insalyon.dasi.ihm.web.action.CommenterConsultationAction;
import fr.insalyon.dasi.ihm.web.action.DebuterConsultationAction;
import fr.insalyon.dasi.ihm.web.action.DeconnexionAction;
import fr.insalyon.dasi.ihm.web.action.FinirConsultationAction;
import fr.insalyon.dasi.ihm.web.action.InscrireClientAction;
import fr.insalyon.dasi.ihm.web.action.InfosClientAction;
import fr.insalyon.dasi.ihm.web.action.ListerMediumsDisposAction;
import fr.insalyon.dasi.ihm.web.action.NoterConsultationAction;
import fr.insalyon.dasi.ihm.web.action.ObtenirHistoriqueEmployeAction;
import fr.insalyon.dasi.ihm.web.action.ObtenirPredictionsAction;
import fr.insalyon.dasi.ihm.web.action.PreparerConsultationAction;
import fr.insalyon.dasi.ihm.web.action.StatistiquesAgenceAction;
import fr.insalyon.dasi.ihm.web.serialisation.AuthentifierClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.AuthentifierEmployeSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ChargerProfilSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.CommenterConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.DebuterConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.DeconnexionSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.FinirConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.InscrireClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.InfosClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ListerMediumsDisposSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.NoterConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ObtenirHistoriqueEmployeSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ObtenirPredictionsSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.PreparerConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.Serialisation;
import fr.insalyon.dasi.ihm.web.serialisation.StatistiquesAgenceSerialisation;

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


                System.out.println(todo);
                System.out.println(todo);
                System.out.println(todo);

        switch (todo) {
            case "authentificationEmploye": {
                action = new AuthentifierEmployeAction();
                serialisation = new AuthentifierEmployeSerialisation();
            }
            break;

            case"ChargerProfil":{
                action = new ChargerProfilAction();
                serialisation = new ChargerProfilSerialisation();
            }
            break;

            case"PreparerConsultation":{
                action = new PreparerConsultationAction();
                serialisation = new PreparerConsultationSerialisation();
            }
            break;

            case"DebuterConsultation":{
                action = new DebuterConsultationAction();
                serialisation = new DebuterConsultationSerialisation();
            }
            break;

            case"FinDeConsultation":{
                action = new FinirConsultationAction();
                serialisation = new FinirConsultationSerialisation();
            }
            break;

            case "obtenirHistoriqueEmploye": {
                action = new ObtenirHistoriqueEmployeAction();
                serialisation = new ObtenirHistoriqueEmployeSerialisation();
            }
            break;

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
            }
            break;

            case "nos-mediums": {
                action = new ListerMediumsAction();
                serialisation = new ListerMediumsSerialisation();
            }
            break;

            case "connexion":{
                action=new AuthentifierClientAction();
                serialisation=new AuthentifierClientSerialisation();
            }
            break;

            case "inscription":{
                action=new InscrireClientAction();
                serialisation=new InscrireClientSerialisation();
            }
            break;

            case "consultationsClient":{
//                action=new ConsultationsClientAction();
//                serialisation=new ConsultationsClientSerialisation();
            }
            break;

            case "statistiquesAgence":{
                action=new StatistiquesAgenceAction();
                serialisation=new StatistiquesAgenceSerialisation();
            }
            break;

            case"nos-mediums-dispos":{ //pour la box qui s'affiche en bas à droite, c'est pas vraiment un cas vu qu'il s'affiche sur plusieurs pages
                action = new ListerMediumsDisposAction();
                serialisation = new ListerMediumsDisposSerialisation();
            }
            break;
            
            case "deconnexion":{
                action=new DeconnexionAction();
                serialisation=new DeconnexionSerialisation();
            }
            break;

            case "astro":{
                action=new InfosClientAction();
                serialisation=new InfosClientSerialisation();
            }
            break;

            case "contact":{
                action=new InfosClientAction();
                serialisation=new InfosClientSerialisation();
            }
            break;

            case "partenaires":{
                action=new InfosClientAction();
                serialisation=new InfosClientSerialisation();
            }
            break;

            case "quiSommesNous":{
                action=new InfosClientAction();
                serialisation=new InfosClientSerialisation();
            }
            break;

        }

        if (action != null && serialisation != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        }

        else {
            response.sendError(400, "Bad Request");
        }
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
