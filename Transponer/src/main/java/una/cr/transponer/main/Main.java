/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.main;

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
        
        System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        System.out.println(cf);
        
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
                tipo = " varchar (300)";
            }
            consulta.append(tipo + " , ");
        }
        consulta.append(" ultimo int");
        consulta.append(");");

        System.out.println(consulta);

        System.out.println(db.executeCrearTabla(consulta.toString()));

        // Tabla creada
        // ahora vamos a insertar los valores correspondientes
        /*
        System.out.println("IMPRIMO LOS OBJETOS A VER QUE TAL");
        for (Respuesta s : resp) {
            System.out.println(s);
        }
        */

        // Lo que hay que hacer, es poblar la base de datos con la tabla creada
        // Debo recorrer por curso
        // para el insert, necesito usar el respuesta builder
        //insert into test1 (s.pregunta) values (s.respuesta);
        System.out.println("*******************************");
        StringBuilder insercion = new StringBuilder();
        insercion.append("insert into test1 values ( ");
        
        insercion.append("'" + cf.getEncuesta()+"' , ");
        insercion.append("'" + cf.getCiclo()+"' , ");
        insercion.append("'" + cf.getCrn()+"' , ");
        insercion.append("'" + cf.getPidm()+"' , ");
        insercion.append("'" + cf.getTssc()+"' , ");
        
        for (Respuesta s : resp) {
            insercion.append("'" + s.getRespuesta() + "'");
            insercion.append(", ");
        }
        insercion.append(-1);
        insercion.append(");");
        System.out.println(insercion.toString());
        db.executeUpdate(insercion.toString());

    }

}
