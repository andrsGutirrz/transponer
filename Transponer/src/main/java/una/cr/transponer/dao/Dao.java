/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import una.cr.transponer.model.ColsFijas;
import una.cr.transponer.model.Respuesta;

/**
 *
 * @author Andres
 *
 */
public class Dao {

    RelDatabase db;

    public Dao() {
        db = new RelDatabase();
    }

    //BUILDERS
    //Respuesta Builder
    public Respuesta respuestaBuilder(ResultSet rs) {
        try {

            Respuesta pr = new Respuesta();
            String cod = rs.getString("SVBTESD_QCOD_CODE");
            if (cod.contains("-")) {
                //podemos encontrarnos con columnas que tienen -, y da error de syntax
                cod = cod.replace("-", "");
            }
            pr.setCodigo(cod);
            pr.setRespuesta("-1");
            String tipoRespuesta = rs.getString("SVBTESD_ACOD_CODE"); //tipo de respuesta
            if (tipoRespuesta.equals("ESCAL-AB")) {
                String temp = rs.getString("SVBTESD_OPEN_ANSWER");
                if (temp == null || temp.isEmpty()) {
                    temp = "-1";
                }
                pr.setRespuesta(temp);
            } else {
                String temp = rs.getString("SVBTESD_PVAC_QPOINTS");
                if (temp == null || temp.isEmpty()) {
                    temp = "-1";
                }
                pr.setRespuesta(temp);

            }
            return pr;
        } catch (SQLException e) {
            return null;
        }
    }

    public ColsFijas ColsFijasBuilder(ResultSet rs) {
        try {

            ColsFijas cf = new ColsFijas();
            cf.setCiclo(rs.getString("SVBTESD_TERM_CODE"));
            cf.setCrn(rs.getString("SVBTESD_CRN"));
            cf.setEncuesta(rs.getString("SVBTESD_ESAS_TEMP_PIDM"));
            cf.setPidm(rs.getString("SVBTESD_FACULTY_PIDM"));
            cf.setTssc(rs.getString("SVBTESD_TSSC_CODE"));
            return cf;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean crearTabla(String nombreTabla, ArrayList<String> columnas) {
        StringBuilder consulta = new StringBuilder();
        consulta.append("create table IF NOT EXISTS " + nombreTabla + " ( ");
        consulta.append("encuesta varchar(10), ");
        consulta.append("periodo varchar(10), ");
        consulta.append("CRN varchar(10), ");
        consulta.append("Profesor varchar(10), ");
        consulta.append("curso varchar(10), ");

        for (String s : columnas) {
            consulta.append(s);
            String tipo = " varchar(5)";
            /*
            EGL33
            EGL34
            IGR05
            IGR06
             */
            if (s.equals("INF03") || s.equals("INF01")
                    || s.equals("INF23") || s.equals("IGR05")
                    || s.equals("IGR06") || s.equals("EGL33") || s.equals("EGL34")) {
                tipo = " varchar (1000)";// 1000
            }
            consulta.append(tipo).append(" , ");
        }

        consulta.append(" ultimo int");
        consulta.append(");");
        return db.executeCrearTabla(consulta.toString());
    }

    public ArrayList<Integer> obtenerCursosPorInstrumento(String instrumento) throws Exception {
        // si se quiere n resultados, usar limit n en la consulta sql, donde n es la cantidad de resultados que se quieren
        ArrayList<Integer> cursos = new ArrayList<>();
        int curso = 0;
        try {
            String sqlCursos = "SELECT DISTINCT "
                    + "SVBTESD_CRN "
                    + "FROM saturn_svbtesd where SVBTESD_TSSC_CODE= '%s';"; // limit 30
            sqlCursos = String.format(sqlCursos, instrumento);
            ResultSet rs6 = db.executeQuery(sqlCursos);
            while (rs6.next()) {
                curso = rs6.getInt("SVBTESD_CRN");
                cursos.add(curso);
            }
        } catch (SQLException e) {
            throw new Exception("Error " + e.getMessage());
        }
        return cursos;
    }

    public String obtenerPrimeraEncuestaPorInstrumento(String instrumento) throws Exception {
        String encuesta = "";
        try {
            String sql = "select "
                    + "SVBTESD_ESAS_TEMP_PIDM "
                    + "from saturn_svbtesd where SVBTESD_TSSC_CODE = '%s' ;"; // *********  ERROR **********
            sql = String.format(sql, instrumento);
            ResultSet rs = db.executeQuery(sql);
            rs.next();
            encuesta = rs.getString("SVBTESD_ESAS_TEMP_PIDM");
        } catch (SQLException e) {
            throw new Exception("Error AL obtener la primera encuesta");
        }
        return encuesta;
    }

    public ArrayList<String> obtenerColumnasPorInstrumento(String encuesta) throws Exception {
        ArrayList<String> columnas = new ArrayList<>();
        String fila = "";
        try {
            String sqlColumnas = "select "
                    + "SVBTESD_QCOD_CODE,"
                    + "SVBTESD_OPEN_ANSWER,"
                    + "SVBTESD_PVAC_QPOINTS,"
                    + "SVBTESD_ACOD_CODE"
                    + " from saturn_svbtesd where SVBTESD_ESAS_TEMP_PIDM = %s;";
            sqlColumnas = String.format(sqlColumnas, encuesta);
            ResultSet rs6 = db.executeQuery(sqlColumnas);
            while (rs6.next()) {
                fila = rs6.getString("SVBTESD_QCOD_CODE");
                if (fila.contains("-")) {
                    //podemos encontrarnos con columnas que tienen -, y da error de syntax
                    fila = fila.replace("-", "");
                }
                columnas.add(fila);
            }
        } catch (SQLException e) {
            throw new Exception("Error " + e.getMessage());
        }
        return columnas;
    }

    public ArrayList<Integer> obtenerEncuestasPorCurso(int curso) throws Exception {
        ArrayList<Integer> numEncuestas = new ArrayList<>();
        int encuesta = 0;
        try {
            String sqlEncuestas = "SELECT DISTINCT SVBTESD_ESAS_TEMP_PIDM FROM saturn_svbtesd where SVBTESD_CRN = %d ;";
            sqlEncuestas = String.format(sqlEncuestas, curso);
            ResultSet rs3 = db.executeQuery(sqlEncuestas);
            while (rs3.next()) {
                encuesta = rs3.getInt("SVBTESD_ESAS_TEMP_PIDM");
                numEncuestas.add(encuesta);
            }
        } catch (SQLException e) {
            throw new Exception("Error " + e.getMessage());
        }
        return numEncuestas;
    }

    public ColsFijas obtenerColumnasFijas(Integer curso) throws Exception {
        ColsFijas cf = new ColsFijas();
        try {
            String sql = "select distinct "
                    + "SVBTESD_ESAS_TEMP_PIDM,"
                    + "SVBTESD_TERM_CODE,"
                    + "SVBTESD_CRN,"
                    + "SVBTESD_FACULTY_PIDM,"
                    + "SVBTESD_TSSC_CODE"
                    + " from saturn_svbtesd where SVBTESD_ESAS_TEMP_PIDM = %d limit 1;";
            sql = String.format(sql, curso);
            ResultSet rs = db.executeQuery(sql);
            rs.next();
            cf = this.ColsFijasBuilder(rs);
        } catch (SQLException e) {
            throw new Exception("Error " + e.getMessage());
        }
        return cf;
    }

    public ArrayList<Respuesta> obtenerRespuestasPorEncuesta(int encuesta) throws Exception {
        ArrayList<Respuesta> respuestas = new ArrayList<>();
        try {
            String sqlRespuestas = "select "
                    + "SVBTESD_QCOD_CODE,"
                    + "SVBTESD_OPEN_ANSWER,"
                    + "SVBTESD_PVAC_QPOINTS,"
                    + "SVBTESD_ACOD_CODE "
                    + "from saturn_svbtesd where SVBTESD_ESAS_TEMP_PIDM = %d;";
            sqlRespuestas = String.format(sqlRespuestas, encuesta);
            ResultSet rs4 = db.executeQuery(sqlRespuestas);
            while (rs4.next()) {
                Respuesta rt2 = this.respuestaBuilder(rs4);
                respuestas.add(rt2);
            }
        } catch (SQLException e) {
            throw new Exception("Error " + e.getMessage());
        }
        return respuestas;
    }

    public void insertarColumnasFijas(String nombreTabla, ColsFijas cf) throws Exception {
        String sqlInsertar = "insert into %s ( encuesta, periodo, CRN, Profesor, curso) values ('%s','%s','%s','%s','%s');";
        sqlInsertar = String.format(sqlInsertar, nombreTabla, cf.getEncuesta(), cf.getCiclo(), cf.getCrn(), cf.getPidm(), cf.getTssc());
        if (db.executeUpdate(sqlInsertar) == 0) {
            throw new Exception("Error: Al insertar columnas Fijas ");
        }

    }

    public void insertarRespuestas(String nombreTabla, Respuesta s, Integer encuesta) throws Exception {
        String sqlInsertarRespuestas = "update %s set %s = '%s' where encuesta = %d ;";
        sqlInsertarRespuestas = String.format(sqlInsertarRespuestas, nombreTabla, s.getCodigo(), s.getRespuesta(), encuesta);
        if (db.executeUpdate(sqlInsertarRespuestas) == 0) {
            throw new Exception("Error: Al insertar respuestas ");
        }
    }

    public void insertarUltimo(String nombreTabla, Integer encuesta) throws Exception {
        String sqlInsertarUltimo = "update %s set ultimo = -1 where encuesta = %d ;";
        sqlInsertarUltimo = String.format(sqlInsertarUltimo, nombreTabla, encuesta);
        if (db.executeUpdate(sqlInsertarUltimo) == 0) {
            throw new Exception("Error: Al insertar ultimo (-1) ");
        }
    }

    public ArrayList<String> listaNombreTablas() throws Exception {
        ArrayList<String> ls = new ArrayList<>();
        String nombre = "";
        try {
            String sql = "SELECT table_name FROM information_schema.tables where table_schema='transponer';";

            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                nombre = rs.getString("table_name");
                if (!nombre.equals("saturn_svbtesd") && !nombre.equals("login")) {
                    ls.add(nombre);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error consultando los nombres de las tablas");
        }
        return ls;
    }
}
