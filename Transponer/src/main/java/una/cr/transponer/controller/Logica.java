/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import una.cr.transponer.dao.Dao;
import una.cr.transponer.dao.RelDatabase;
import una.cr.transponer.model.ColsFijas;
import una.cr.transponer.model.Mensaje;
import una.cr.transponer.model.Respuesta;

/**
 *
 * @author Andrés Gutiérrez
 */
@WebServlet(name = "Logica", urlPatterns = {"/make-transponer"})
@MultipartConfig
public class Logica extends HttpServlet {
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch (request.getServletPath()) {
            case "/make-transponer":
                this.doMakeTransponer(request, response);
                break;
        }
    }

    public void doMakeTransponer(HttpServletRequest request, HttpServletResponse response) {
        String mensaje = "";
        try {
            
            String nombreTabla = request.getParameter("nombreTabla");
            String instrumento = request.getParameter("instrumentos");
               
            mensaje = "Tabla generada!";            
            Mensaje msj = new Mensaje(mensaje,nombreTabla,instrumento);            
            request.setAttribute("mensaje", msj);
            
            //this.transponerEvaluaciones(nombreTabla, instrumento);
            
            request.getRequestDispatcher("/transponer").forward(request, response);
        } catch (Exception e) {
            response.setStatus(401); //Bad request
        }
    }

    public void transponerEvaluaciones(String _nombreTabla, String _instrumento) {

        String nombreTabla = _nombreTabla;
        String instrumento = _instrumento;
        ColsFijas cf = null;
        try {

            Dao dao = new Dao();

            String encuestaPrimera = dao.obtenerPrimeraEncuestaPorInstrumento(instrumento);

            System.out.println(encuestaPrimera);

            ArrayList<String> columnas = dao.obtenerColumnasPorInstrumento(encuestaPrimera);

            dao.crearTabla(nombreTabla, columnas);

            //los cursos
            ArrayList<Integer> cursos = dao.obtenerCursosPorInstrumento(instrumento);

            //Los numeros de encuestas por curso e
            for (int e : cursos) {
                ArrayList<Integer> numEncuestas = dao.obtenerEncuestasPorCurso(e);
                // por cada encuesta, le saco las preguntas
                for (Integer i : numEncuestas) {

                    cf = dao.obtenerColumnasFijas(i);
                    ArrayList<Respuesta> resp2 = dao.obtenerRespuestasPorEncuesta(i);
                    dao.insertarColumnasFijas(nombreTabla, cf);

                    for (Respuesta s : resp2) {
                        //System.out.println(s);
                        dao.insertarRespuestas(nombreTabla, s, i);
                    }

                    dao.insertarUltimo(nombreTabla, i);

                }// for de encuestas

            } // fin for cursos

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
