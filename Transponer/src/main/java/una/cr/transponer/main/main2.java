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
public class main2 {

    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        RelDatabase db;
        db = new RelDatabase();

        Dao dao = new Dao();

        ArrayList<String> columnas = new ArrayList<>();
        ArrayList<Respuesta> resp = new ArrayList<>();

        String sql = "select SVBTESD_QCOD_CODE,SVBTESD_OPEN_ANSWER,SVBTESD_PVAC_QPOINTS,SVBTESD_ACOD_CODE"
                + " from saturn_svbtesd where SVBTESD_ESAS_TEMP_PIDM = 1437831;";
        ResultSet rs = db.executeQuery(sql);
        while (rs.next()) {
            String fila = rs.getString("SVBTESD_QCOD_CODE");
            // System.out.println(fila);
            columnas.add(fila);
            Respuesta rt = dao.respuestaBuilder(rs);
            resp.add(rt);
        }

        // CONSULTA PARA LOS CAMPOS FIJOS!
        String sql2 = "select distinct SVBTESD_ESAS_TEMP_PIDM,SVBTESD_TERM_CODE,SVBTESD_CRN,SVBTESD_FACULTY_PIDM,SVBTESD_TSSC_CODE"
                + " from saturn_svbtesd where SVBTESD_ESAS_TEMP_PIDM = 1437831;";
        ResultSet rs2 = db.executeQuery(sql2);
        ColsFijas cf = null;
        while (rs2.next()) {
            cf = dao.ColsFijasBuilder(rs2);
        }

        /*System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        System.out.println(cf);
**/
        System.out.println("=================");
        StringBuilder consulta = new StringBuilder();
        consulta.append("create table IF NOT EXISTS test1( ");

        consulta.append("encuesta varchar(10), ");
        consulta.append("periodo varchar(10), ");
        consulta.append("CRN varchar(10), ");
        consulta.append("Profesor varchar(10), ");
        consulta.append("curso varchar(10), ");

        for (String s : columnas) {
            consulta.append(s);
            String tipo = " varchar(5)";
            if (s.equals("INF01") || s.equals("INF03") || s.equals("EGL33") || s.equals("EGL34")) {
                tipo = " varchar (1000)";// 1000
            }
            consulta.append(tipo + " , ");
        }
        consulta.append(" ultimo int");
        consulta.append(");");

        //System.out.println(consulta);
        //System.out.println(db.executeCrearTabla(consulta.toString()));
        System.out.println("*******************************");

        //traer todos los resultados
        ArrayList<Integer> cursos = new ArrayList<>();
        String sqlCursos = "SELECT DISTINCT SVBTESD_CRN FROM saturn_svbtesd  LIMIT 10;";
        ResultSet rs6 = db.executeQuery(sqlCursos);
        while (rs6.next()) {
            int enc = rs6.getInt("SVBTESD_CRN");
            cursos.add(enc);
        }
        for (int e : cursos) {

            // vamos a llenar la tabla con las respuestas de un curso 6000
            String encuestas = "SELECT DISTINCT SVBTESD_ESAS_TEMP_PIDM FROM saturn_svbtesd where SVBTESD_CRN = 60003;";
            ResultSet rs3 = db.executeQuery(encuestas);
            ArrayList<Integer> numEncuestas = new ArrayList<>();
            while (rs3.next()) {
                int enc = rs3.getInt("SVBTESD_ESAS_TEMP_PIDM");
                numEncuestas.add(enc);
            }

            String INSERTS = "";
            for (Integer i : numEncuestas) {
                StringBuilder insercion2 = new StringBuilder();
                String sqlpoblar = "select "
                        + "SVBTESD_QCOD_CODE,SVBTESD_OPEN_ANSWER,SVBTESD_PVAC_QPOINTS,SVBTESD_ACOD_CODE,"
                        + "SVBTESD_ESAS_TEMP_PIDM,SVBTESD_TERM_CODE,SVBTESD_CRN,SVBTESD_FACULTY_PIDM,"
                        + "SVBTESD_TSSC_CODE from saturn_svbtesd where SVBTESD_ESAS_TEMP_PIDM = " + i + ";";

                ResultSet rs4 = db.executeQuery(sqlpoblar);
                ArrayList<Respuesta> resp2 = new ArrayList<>(); //almacena respuestas

                while (rs4.next()) {
                    cf = dao.ColsFijasBuilder(rs4);
                    Respuesta rt2 = dao.respuestaBuilder(rs4);//hacer otro queryset
                    resp2.add(rt2);
                }// fin while

                insercion2.append("insert into test1 ( encuesta, periodo, CRN, Profesor, curso) ");
                insercion2.append(" values ( ");
                insercion2.append("'" + cf.getEncuesta() + "' , ");
                insercion2.append("'" + cf.getCiclo() + "' , ");
                insercion2.append("'" + cf.getCrn() + "' , ");
                insercion2.append("'" + cf.getPidm() + "' , ");
                insercion2.append("'" + cf.getTssc() + "' ");
                insercion2.append(");");

                db.executeUpdate(insercion2.toString()); //this execute

                for (Respuesta s : resp2) {
                    StringBuilder insercion3 = new StringBuilder();
                    insercion3.append(" UPDATE test1 set ");
                    insercion3.append(s.getCodigo());
                    insercion3.append(" = ");
                    insercion3.append("'" + s.getRespuesta() + "'");
                    insercion3.append(" where encuesta = ");
                    insercion3.append(i);
                    insercion3.append("; ");
                    db.executeUpdate(insercion3.toString());
                }
                StringBuilder insercion4 = new StringBuilder(); // this execute
                insercion4.append("update test1 set ultimo = -1 where encuesta = ");
                insercion4.append(i);
                insercion4.append(";"); //salto de linea
                // INSERTS += insercion2.toString();
                //System.out.println(insercion3.toString());
                db.executeUpdate(insercion4.toString());

            }// for de encuestas
/*
        try {
            String ruta = "inserts.sql";
            String contenido = INSERTS.toString();
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
             */
            db.executeUpdate(INSERTS.toString());

        } // fin for cursos

    }

}
