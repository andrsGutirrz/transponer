/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import una.cr.transponer.dao.Dao;
import una.cr.transponer.model.ColsFijas;
import una.cr.transponer.model.Mensaje;
import una.cr.transponer.model.Respuesta;
import una.cr.transponer.model.TablaGenerada;

/**
 *
 * @author Andrés Gutiérrez
 */
@WebServlet(name = "Logica", urlPatterns = {"/make-transponer", "buscar-tabla"})
@MultipartConfig
public class Logica extends HttpServlet {

    Dao dao = Dao.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        switch (request.getServletPath()) {
            case "/make-transponer":
                this.doMakeTransponer(request, response);
                break;
            case "/buscar-tabla":
                this.doBuscarTabla(request, response);
                break;
            case "/eliminar-tabla":
                this.doEliminarTabla(request, response);
                break;
        }
    }

    
    /*
        Obtiene del usuario el nombre de la tabla a generar y el instrumento que quiere procesar
    */
    public void doMakeTransponer(HttpServletRequest request, HttpServletResponse response) {
        String mensaje = "";
        try {

            String nombreTabla = request.getParameter("nombreTabla");
            String instrumento = request.getParameter("instrumentos");

            mensaje = this.transponerEvaluaciones(nombreTabla, instrumento);

            Mensaje msj = new Mensaje(mensaje, nombreTabla, instrumento);
            request.setAttribute("mensaje", msj);

            request.getRequestDispatcher("/transponer").forward(request, response);
        } catch (Exception e) {
            response.setStatus(401); //Bad request
            System.out.println("Error make transponer: " + e);
        }
    }

    
    /*
        Logica principal, este hace un recorrido por toda la data para encontrar
        las evaluaciones que corresponden al instrumeto seleccionado por el 
        usuario
    */
    public String transponerEvaluaciones(String _nombreTabla, String _instrumento) {

        String nombreTabla = _nombreTabla;
        String instrumento = _instrumento;
        String mensaje = "Éxito tabla generada!";
        ColsFijas cf = null;
        try {

            if (this.existeNombreTabla(nombreTabla)) {
                return "Existe tabla con el mismo nombre -> " + nombreTabla;
            }

            String encuestaPrimera = dao.obtenerPrimeraEncuestaPorInstrumento(instrumento);

            // ArrayList<String> columnas = dao.obtenerColumnasPorInstrumento(encuestaPrimera);
            ArrayList<String> columnas = dao.obtenerTodasColumnas();

            dao.crearTabla(nombreTabla, columnas);

            //los cursos
            ArrayList<Integer> cursos = dao.obtenerCursosPorInstrumento(instrumento);

            //Los numeros de encuestas por curso e
            for (int e : cursos) {
                ArrayList<Integer> numEncuestas = dao.obtenerEncuestasPorCurso(e);
                // por cada encuesta, le saco las preguntas
                for (Integer i : numEncuestas) {

                    cf = dao.obtenerColumnasFijas(i);
                    cf = dao.insertarColumnasFaltantes(cf);
                    ArrayList<Respuesta> resp2 = dao.obtenerRespuestasPorEncuesta(i);
                    dao.insertarColumnasFijas(nombreTabla, cf);

                    for (Respuesta s : resp2) {
                        dao.insertarRespuestas(nombreTabla, s, i);
                    }

                    dao.insertarUltimo(nombreTabla, i);

                }// for de encuestas

            } // fin for cursos

        } catch (Exception e) {
            mensaje = "Error al procesar las encuestas! " + e.getMessage();
            return mensaje;
        }
        return mensaje;
    }

    
    /*
     Busca en la base de datos la tabla con el nombre dado por el usuario para luego
     ser mostrado
    */
    public void doBuscarTabla(HttpServletRequest request, HttpServletResponse response) {
        try {

            String combo = request.getParameter("tablas");

            String[] parts = combo.split(" | ", 2);
            String nombreTabla = parts[0];  // Obtengo el nombre de la consulta

            ArrayList<String> cols = dao.columnasTabla(nombreTabla);
            ArrayList<String> ls = dao.obtenerDatosTabla(nombreTabla,cols);

            request.setAttribute("columnas", cols);
            request.setAttribute("datos", ls);

            request.getRequestDispatcher("/consultar").forward(request, response);
        } catch (Exception e) {
            response.setStatus(401); //Bad request
            System.out.println("Error: en buscar tabla " + e);
        }
    }

    /*
        Obtiene el nombre de una tabla por parte del usuario para luego ser eliminada
        de la base de datos
    */
    public void doEliminarTabla(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {

            String combo = request.getParameter("tablas");

            String[] parts = combo.split(" | ", 2);
            String nombreTabla = parts[0];  // Obtengo el nombre de la consulta

            dao.eliminarTabla(nombreTabla);
            request.setAttribute("errorBorrar", "Tabla borrada con éxito!");

            request.getRequestDispatcher("/consultar").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorBorrar", e.getMessage());
            request.getRequestDispatcher("/consultar").forward(request, response);
        }
    }

    /*
        Busca en la base de datos si existe otra tabla con el mismo nombre
        al momento de crear una nueva tabla
    */
    public boolean existeNombreTabla(String nombre) throws Exception {
        ArrayList<TablaGenerada> ls = dao.listaNombreTablas();
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }
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
