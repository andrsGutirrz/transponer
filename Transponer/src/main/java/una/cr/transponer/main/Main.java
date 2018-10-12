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
import una.cr.transponer.model.Respuesta;

/**
 *
 * @author Andrés Gutiérrez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
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
            System.out.println(fila);
            columnas.add(fila);
            Respuesta rt = dao.respuestaBuilder(rs);
            resp.add(rt);
        }

        System.out.println("=================");
        StringBuilder consulta = new StringBuilder();
        consulta.append("create table IF NOT EXISTS test1( ");

        for (String s : columnas) {
            consulta.append(s);
            consulta.append(" varchar(20),");
        }
        consulta.append(" ultimo int");
        consulta.append(");");

        System.out.println(consulta);

        System.out.println(db.executeCrearTabla(consulta.toString()));

        // Tabla creada
        // ahora vamos a insertar los valores correspondientes
        System.out.println("IMPRIMO LOS OBJETOS A VER QUE TAL");
        for (Respuesta s : resp) {
            System.out.println(s);
        }

    }

}
