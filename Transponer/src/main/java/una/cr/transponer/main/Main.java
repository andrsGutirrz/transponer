/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.main;


import java.sql.SQLException;
import java.util.ArrayList;
import una.cr.transponer.dao.Dao;
import una.cr.transponer.dao.RelDatabase;
import una.cr.transponer.model.ColsFijas;
import una.cr.transponer.model.Respuesta;

/**
 *
 * @author Andrés Gutiérrez POSGR-03 ECIDEA18 EDDECEG3 EDDLAB18 EDDMVLC5
 * EDDMVCC5 EVDBIM18 IGENER18 
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        String nombreTabla = "IGENER18";
        String instrumento = "IGENER18";
        ColsFijas cf = null;
        try {
            RelDatabase db;
            db = new RelDatabase();

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
}
