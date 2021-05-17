/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jadep
 */
public class ListerMediumsAction extends Action{
    
    @Override
    public void executer(HttpServletRequest request){
        
        Service service = new Service();
        
        List<Medium> listeMediums = service.listerMediums();
        
        //System.out.println("liste des mediums = " + mediums);
        request.setAttribute("mediums", listeMediums);
    }
}
