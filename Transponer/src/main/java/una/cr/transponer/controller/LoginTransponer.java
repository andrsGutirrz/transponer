/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import una.cr.transponer.dao.Dao;
import una.cr.transponer.model.Usuario;

/**
 *
 * @author Andrés Gutiérrez
 */
@WebServlet(name = "LoginTransponer", urlPatterns = {"/home","/logout"})
@MultipartConfig
public class LoginTransponer extends HttpServlet {

    Dao dao = Dao.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch (request.getServletPath()) {
            case "/home":
                this.doLogin(request, response);
                break;
            case "/logout":
            this.doLogout(request,response);
            break;

        }
    }

    public void doLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession s = request.getSession(true);
            String username = request.getParameter("username");
            String password = request.getParameter("pass");
            String cl = Hashing.sha1().hashString(password, Charsets.UTF_8).toString();
            //if(usuario == null){return false;}
            //cl.equals(pass) && usuario.activo == true;

            Usuario usuario = dao.login(username, password);

            if (usuario != null && usuario.clave.equals(cl)) {
                s.setAttribute("usuario", usuario);//agregamos a la sesion el usuario loggeado
                request.getRequestDispatcher("view/index.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            response.setStatus(401); //Bad request
        }
    }
    
        protected void doLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.getSession().invalidate();
            request.getRequestDispatcher("/login").forward( request, response);          
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
