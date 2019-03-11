/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.dao;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import una.cr.transponer.model.ColsFijas;
import una.cr.transponer.model.Respuesta;
import una.cr.transponer.model.TablaGenerada;
import una.cr.transponer.model.Usuario;
import una.cr.transponer.model.Validate;
import una.cr.transponer.utils.ConexionLoader;

/**
 *
 * @author Andres
 *
 */
public class Dao {

    RelDatabase db;

    private static Dao singleton = new Dao();

    ConexionLoader conn = ConexionLoader.getInstance();

    String tablaEvaluaciones = conn.tablaEvaluaciones;
    String tablaProfesores = conn.tablaProfesores;
    String tablaCursos = conn.tablaCursos;
    String tablaPeriodo = conn.tablaPeriodo;

    //SIGLETON
    private Dao() {
        db = new RelDatabase();
    }

    public static Dao getInstance() {
        return singleton;
    }

    //BUILDERS
    //Respuesta Builder
    public Respuesta respuestaBuilder(ResultSet rs) {
        //aca debo poner la logica de SEQ_NUM
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
            } 
            if( tipoRespuesta.equals("ESCASINO") || tipoRespuesta.equals("GNRO_INC") || tipoRespuesta.equals("GNRO_NR") || tipoRespuesta.equals("SI-NOVAL") || tipoRespuesta.equals("ESCAL-NS")){
                String temp = rs.getString("SVBTESD_PVAC_SEQ_NUM");
                if (temp == null || temp.isEmpty()) {
                    temp = "-1";
                }
                pr.setRespuesta(temp);
                
            }
            if( !tipoRespuesta.equals("ESCASINO") && !tipoRespuesta.equals("GNRO_INC") && !tipoRespuesta.equals("GNRO_NR") && !tipoRespuesta.equals("ESCAL-AB") && !tipoRespuesta.equals("SI-NOVAL") && !tipoRespuesta.equals("ESCAL-NS")) {
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
    
    
    /*
        Crea la tabla donde se pondrá la información generada
    */
    public boolean crearTabla(String nombreTabla, ArrayList<String> columnas) {
        StringBuilder consulta = new StringBuilder();
        consulta.append("create table IF NOT EXISTS " + nombreTabla + " ( ");
        consulta.append("encuesta varchar(10), ");
        consulta.append("periodo varchar(10), ");
        consulta.append("CRN varchar(10), ");
        consulta.append("Profesor varchar(10), ");
        consulta.append("curso varchar(10), ");
        //nuevos
        consulta.append("nombreProfesor varchar(100), ");
        consulta.append("cedulaProfesor varchar(15),");
        consulta.append("nombreCurso varchar(100), ");
        consulta.append("escuela varchar(100), ");
        consulta.append("facultad varchar(100), ");
        consulta.append("campus varchar(100), ");
        consulta.append("cupo varchar(100), ");
        consulta.append("matricula varchar(100),");
        consulta.append("codigoCurso varchar(100),");

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
                    + "SVBTESD_CRN FROM %s where SVBTESD_TSSC_CODE= '%s';";
            sqlCursos = String.format(sqlCursos, tablaEvaluaciones, instrumento);
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
                    + "from %s where SVBTESD_TSSC_CODE = '%s' ;"; // *********  ERROR **********
            sql = String.format(sql, tablaEvaluaciones, instrumento);
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
                    + " from %s where SVBTESD_ESAS_TEMP_PIDM = %s;";
            sqlColumnas = String.format(sqlColumnas, tablaEvaluaciones, encuesta);
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
            String sqlEncuestas = "SELECT DISTINCT SVBTESD_ESAS_TEMP_PIDM "
                    + "FROM %s where SVBTESD_CRN = %d ;";
            sqlEncuestas = String.format(sqlEncuestas, tablaEvaluaciones, curso);
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
                    + " from %s where SVBTESD_ESAS_TEMP_PIDM = %d limit 1;";
            sql = String.format(sql, tablaEvaluaciones, curso);
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
            // ACA DEBO PONER LA OTRA COLUMNA ( SEQ_NUM )
            String sqlRespuestas = "select "
                    + "SVBTESD_QCOD_CODE,"
                    + "SVBTESD_OPEN_ANSWER,"
                    + "SVBTESD_PVAC_QPOINTS,"
                    + "SVBTESD_ACOD_CODE, "
                    + "SVBTESD_PVAC_SEQ_NUM "
                    + "from %s where SVBTESD_ESAS_TEMP_PIDM = %d;";
            sqlRespuestas = String.format(sqlRespuestas, tablaEvaluaciones, encuesta);
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
        String sqlInsertar = "insert into %s ( "
                + "encuesta, periodo, "
                + "CRN, Profesor, curso, "
                + "nombreProfesor,cedulaProfesor,"
                + "nombreCurso,escuela,"
                + "facultad,cupo,"
                + "matricula,codigoCurso,campus "
                + ") "
                + "values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');";
        sqlInsertar = String.format(sqlInsertar, nombreTabla, cf.getEncuesta(), cf.getCiclo(),
                cf.getCrn(), cf.getPidm(), cf.getTssc(), cf.getNombreProfesor(), cf.getCedulaProfesor(),
                cf.getNombreCurso(), cf.getEscuela(), cf.getFacultad(), cf.getCupo(), cf.getMatricula(),
                cf.getCodigoCurso(), cf.getCampus()
        );

        if (db.executeUpdate(sqlInsertar) == 0) {
            throw new Exception("Error: Al insertar columnas Fijas ");
        }
    }

    public ColsFijas insertarColumnasFaltantes(ColsFijas cf) throws Exception {
        try {

            String sql = "select SSBSECT_SUBJ_CODE cod1,SSBSECT_CRSE_NUMB cod2, "
                    + "SSBSECT_MAX_ENRL cupo,SSBSECT_ENRL matricula, "
                    + "SSBSECT_CAMP_CODE campus, "
                    + "SCBCRSE_COLL_CODE escuela,SCBCRSE_DIVS_CODE facultad, "
                    + "SCBCRSE_TITLE nombreCurso, "
                    + "SPRIDEN_ID cedula,SPRIDEN_LAST_NAME apellido,SPRIDEN_FIRST_NAME nombre "
                    + "from %s ,%s ,%s "
                    + "where SSBSECT_TERM_CODE = %s and  SSBSECT_CRN = %s "
                    + "and SSBSECT_SUBJ_CODE = SCBCRSE_SUBJ_CODE and SSBSECT_CRSE_NUMB = SCBCRSE_CRSE_NUMB "
                    + "and SPRIDEN_PIDM = %s;";
            sql = String.format(sql, tablaPeriodo, tablaCursos, tablaProfesores, cf.getCiclo(),
                    cf.getCrn(), cf.getPidm());

            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                cf.setCodigoCurso(rs.getString("cod1") + rs.getString("cod2"));
                cf.setCedulaProfesor(rs.getString("cedula"));
                cf.setCupo(rs.getString("cupo"));
                cf.setMatricula(rs.getString("matricula"));
                cf.setNombreProfesor(rs.getString("apellido") + " " +rs.getString("nombre"));
                cf.setEscuela(rs.getString("escuela"));
                cf.setFacultad(rs.getString("facultad"));
                cf.setCampus(rs.getString("campus"));
                cf.setNombreCurso(rs.getString("nombreCurso"));
            }
            return cf;
        } catch (SQLException e) {
            throw new Exception("Error " + e.getMessage());
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

    public ArrayList<TablaGenerada> listaNombreTablas() throws Exception {
        ArrayList<TablaGenerada> ls = new ArrayList<>();
        String nombre = "";
        String fecha = "";
        try {
            String sql = "SELECT table_name,create_time FROM information_schema.tables where table_schema='transponer';";

            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                nombre = rs.getString("table_name");
                fecha = rs.getString("create_time");
                /*if (!nombre.equals("saturn_svbtesd") && !nombre.equals("usuarios") && !nombre.contains("saturn")) {
                    ls.add(new TablaGenerada(nombre, fecha));
                }*/ // Dejo que muestre las tablas de donde vienen los datos para que pueda borrarlas
                if (!nombre.equals("usuarios")) {//Unicamente no muestro usuarios, porque esa es necesaria para ingresar al sistema
                    ls.add(new TablaGenerada(nombre, fecha));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error consultando los nombres de las tablas");
        }
        return ls;
    }

    public ArrayList<String> obtenerDatosTabla(String tabla, ArrayList<String> cols) throws Exception {
        ArrayList<String> datos = new ArrayList<>();
        ArrayList<String> columnas = cols;
        String dato = "";
        try {
            String consulta = "select * from %s;";
            consulta = String.format(consulta, tabla);
            ResultSet rs = db.executeQuery(consulta);
            while (rs.next()) {
                for (int i = 0; i < columnas.size(); i++) {
                    dato = rs.getString(columnas.get(i));
                    datos.add(dato);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error consultando los nombres de las tablas");
        }
        return datos;
    }

    public ArrayList<String> columnasTabla(String tabla) throws Exception {
        /*Esta consulta estaria genial, si se hace una sola vez, al inicio de cargar el sistema, mejoriaria el tiempo*/
        ArrayList<String> ls = new ArrayList<>();
        String dato = "";
        try {
            String consulta = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'transponer' AND TABLE_NAME = '%s';";
            consulta = String.format(consulta, tabla);
            ResultSet rs = db.executeQuery(consulta);
            while (rs.next()) {
                dato = rs.getString("COLUMN_NAME");
                ls.add(dato);
            }
        } catch (SQLException e) {
            throw new Exception("Error consultando los nombres de las tablas");
        }
        return ls;
    }

    public Usuario login(String usr, String clv) throws SQLException {
        Usuario usuario = null;
        String consulta = "select  * from usuarios where username = '%s' ";
        consulta = String.format(consulta, usr);

        ResultSet rs = db.executeQuery(consulta);
        while (rs.next()) {
            int id = rs.getInt("id"); // no tan requerido
            String user = rs.getString("username");
            String pass = rs.getString("clave");
            boolean act = rs.getBoolean("activo");
            usuario = new Usuario(id, user, pass, act);
        }
        return usuario;
    }

    public void eliminarTabla(String nombreTabla) throws Exception {
        String sql = "DROP TABLE %s";
        sql = String.format(sql, nombreTabla);

        boolean resultado = db.executeBorrarTabla(sql);
        if (!resultado) {
            throw new Exception("Error al borrar la tabla");
        }
    }
}
