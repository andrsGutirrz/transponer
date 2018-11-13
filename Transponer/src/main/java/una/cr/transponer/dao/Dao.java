/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import una.cr.transponer.model.ColsFijas;
import una.cr.transponer.model.Respuesta;

/**
 *
 * @author Andres
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
            pr.setCodigo(rs.getString("SVBTESD_QCOD_CODE"));
            String tipoRespuesta = rs.getString("SVBTESD_ACOD_CODE"); //tipo de respuesta
            if (tipoRespuesta.equals("ESCAL-AB")) {
                //Entonces es de respuesta libre!
                String temp = rs.getString("SVBTESD_OPEN_ANSWER");
                if (temp == null) {
                    temp = "-1";
                }
                pr.setRespuesta(temp);
            } else {
                String temp = rs.getString("SVBTESD_PVAC_QPOINTS");
                if (temp == null) {
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
        consulta.append("create table IF NOT EXISTS "+nombreTabla+" ( ");
        consulta.append("encuesta varchar(10), ");
        consulta.append("periodo varchar(10), ");
        consulta.append("CRN varchar(10), ");
        consulta.append("Profesor varchar(10), ");
        consulta.append("curso varchar(10), ");

        for (String s : columnas) {
            consulta.append(s);
            String tipo = " varchar(5)";
            if (s.equals("INF01") || s.equals("INF23") || s.equals("IGR05") || s.equals("IGR06")) {
                tipo = " varchar (1000)";// 1000
            }
            consulta.append(tipo).append(" , ");
        }
        consulta.append(" ultimo int");
        consulta.append(");");

        return db.executeCrearTabla(consulta.toString());
    }
}
