/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import una.cr.transponer.dao.Dao;
import una.cr.transponer.dao.RelDatabase;
import una.cr.transponer.model.ColsFijas;
import una.cr.transponer.model.Respuesta;

/**
 *
 * @author Andrés Gutiérrez
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        String nombreTabla = "test3";
        String instrumento = "ECIDEA18";
        ColsFijas cf = null;
        try {
            RelDatabase db;
            db = new RelDatabase();

            Dao dao = new Dao();

            System.out.println("1");
            
            ArrayList<String> columnas = dao.obtenerColumnasPorInstrumento(instrumento);

            System.out.println("2");

            dao.crearTabla(nombreTabla, columnas);
            
            System.out.println("3");

            //los cursos
            ArrayList<Integer> cursos = dao.obtenerCursosPorInstrumento(instrumento);
            
            System.out.println("4");

            //Los numeros de encuestas por curso e
            for (int e : cursos) {
                ArrayList<Integer> numEncuestas = dao.obtenerEncuestasPorCurso(e);
                System.out.println("5");
                // por cada encuesta, le saco las preguntas
                for (Integer i : numEncuestas) {

                    cf = dao.obtenerColumnasFijas(i);
                    ArrayList<Respuesta> resp2 = dao.obtenerRespuestasPorEncuesta(i);
                    System.out.println("6");
                    dao.insertarColumnasFijas(nombreTabla, cf);
                    System.out.println("7");

                    for (Respuesta s : resp2) {
                        //System.out.println(s);
                        dao.insertarRespuestas(nombreTabla, s, i);
                    }

                    dao.insertarUltimo(nombreTabla, i);
                    System.out.println("8");

                }// for de encuestas

            } // fin for cursos
            System.out.println("9");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
