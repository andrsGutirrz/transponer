/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import una.cr.transponer.model.Mensaje;

/**
 *
 * @author Andrés Gutiérrez
 */
@WebServlet(name = "Direccionamiento", urlPatterns = {"/consultar", "/transponer", "/index"})
@MultipartConfig
public class Direccionamiento extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch (request.getServletPath()) {
            case "/index":
                this.doIndex(request, response);
                break;
            case "/transponer":
                this.doTransponer(request, response);
                break;
            case "/consultar":
                this.doConsultar(request, response);
                break;
        }
    }

    public void doIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("view/index.jsp").forward(request, response);//*
        } catch (Exception e) {
            response.setStatus(401); //Bad request
        }
    }

    public void doTransponer(HttpServletRequest request, HttpServletResponse response) {
        try {
            
            Mensaje msj = (Mensaje)request.getAttribute("mensaje");
            System.out.println("Msj: "+msj);
            if (msj == null) {
                msj = new Mensaje("-1","","");
            }
            request.setAttribute("mensaje", msj);
            
            request.getRequestDispatcher("view/transponer.jsp").forward(request, response);//*
        } catch (Exception e) {
            response.setStatus(401); //Bad request
            System.out.println("Error: " + e);
            
        }
    }

    public void doConsultar(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("view/consultar.jsp").forward(request, response);//*
        } catch (Exception e) {
            response.setStatus(401); //Bad request
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
