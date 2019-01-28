/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import una.cr.transponer.utils.ConexionLoader;

public class RelDatabase {

    Connection cnx;
    ConexionLoader conn = ConexionLoader.getInstance();

    public RelDatabase() {
        cnx = this.getConnection();
    }

    public Connection getConnection() {
        try {

            String driver = "com.mysql.jdbc.Driver";
            //#jdbc:mysql://localhost:3306/transponer?user=root&password=root";
            String aux = String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s", 
                    conn.host,conn.puerto,conn.baseDatos,conn.user,conn.password);
            String URL_conexion = aux;
            Class.forName(driver).newInstance();
            return DriverManager.getConnection(URL_conexion);
        } catch (Exception e) {
            System.err.println(e.getMessage() + " error");
            System.exit(-1);
        }
        return null;
    }

    public int executeUpdate(String statement) {
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(statement);
            return stm.getUpdateCount();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            return 0;
        }
    }

    public ResultSet executeQuery(String statement) {
        try {
            Statement stm = cnx.createStatement();
            return stm.executeQuery(statement);
        } catch (SQLException ex) {
        }
        return null;
    }

    public int executeUpdateWithKeys(String statement) {
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(statement, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stm.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
        } catch (SQLException ex) {
            return -1;
        }
    }

    public boolean executeCrearTabla(String statement) {
        try {
            Statement stm = cnx.createStatement();
            return stm.execute(statement);
        } catch (SQLException ex) {
        }
        return false;
    }

    public boolean executeBorrarTabla(String statement) {
        try {
            Statement stm = cnx.createStatement();
            return !stm.execute(statement);
        } catch (SQLException ex) {
        }
        return false;
    }

}
