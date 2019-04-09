/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author luis_
 */
public class ActionLogin extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String INPUT = "input";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionFormLogin forma =(ActionFormLogin)form;
        LoginBean lb = new LoginBean();
        System.out.println(forma.getNombre()+","+forma.getPassword());
        // check to see if this user/password combination are valid
        if(lb.validateUser(forma.getNombre(),forma.getPassword()))
            return mapping.findForward(SUCCESS);
        return mapping.findForward(INPUT);
    }
}
