/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author luis_
 */
public class ActionFormLogin extends org.apache.struts.action.ActionForm {
    
    private String nombre, password;
    

    /**
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param string
     */
    public void setNombre(String string) {
        nombre = string;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
