/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import una.cr.transponer.dao.Dao;
import una.cr.transponer.model.Mensaje;
import una.cr.transponer.model.TablaGenerada;
import una.cr.transponer.model.Usuario;

/**
 *
 * @author Andrés Gutiérrez
 */
@WebServlet(name = "Direccionamiento", urlPatterns = {"/consultar", "/transponer", "/index", "/login"})
@MultipartConfig
public class Direccionamiento extends HttpServlet {

    Dao dao = Dao.getInstance();

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
            case "/login":
                this.doHome(request, response);
                break;
        }
    }

    public void doIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("view/index.jsp").forward(request, response);//*
        } catch (Exception e) {
            response.setStatus(401); //Bad request
            System.out.println("Error: " + e);
        }
    }

    public void doHome(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession s = request.getSession(true);
            Usuario usr = (Usuario) s.getAttribute("usuario");
            if (usr != null) {
                request.getRequestDispatcher("view/index.jsp").forward(request, response);//*
            }
            request.getRequestDispatcher("view/login.jsp").forward(request, response);//*
        } catch (Exception e) {
            response.setStatus(401); //Bad request
            System.out.println("Error: " + e);
        }
    }

    public void doTransponer(HttpServletRequest request, HttpServletResponse response) {
        try {

            Mensaje msj = (Mensaje) request.getAttribute("mensaje");
            if (msj == null) {
                msj = new Mensaje("-1", "", "");
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
            ArrayList<TablaGenerada> ls = dao.listaNombreTablas();
            request.setAttribute("tablas", ls);
            request.getRequestDispatcher("view/consultar.jsp").forward(request, response);//*
        } catch (Exception e) {
            response.setStatus(401); //Bad request
            System.out.println("Error: " + e);
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
