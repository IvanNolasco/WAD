/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MMV;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_
 */
public class Serv1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cant = request.getParameter("cantidad");
        float numCant = Float.parseFloat(cant);
        String d;
        float media = 0, varianza = 0;
        ArrayList<Float> datos = new ArrayList(),mediana = new ArrayList();
        for (int i = 0; i < numCant; i++) {
            d = request.getParameter("d"+(i+1));
            datos.add(Float.parseFloat(d));
        }
        
        Collections.sort(datos);
        
        //Calculando la media
        for (Float dato : datos) {
            media += dato;
        }       
        media /= datos.size();
        
        //Calculando la mediana
        if(datos.size()%2 == 0){
            mediana.add(datos.get(datos.size()/2-1));
            mediana.add(datos.get(datos.size()/2));
        }
        else
            mediana.add(datos.get(datos.size()/2-1));        

        //Calculando la varianza
        for (Float dato : datos) 
            varianza += Math.pow(dato - media, 2);
        varianza /= datos.size();
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Serv1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Media = "+media+"</h1><br>");
            out.println("<h1>Mediana = "+mediana+"</h1><br>");
            out.println("<h1>Varianza = "+varianza+"</h1><br>");
            out.println("</body>");
            out.println("</html>");
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
