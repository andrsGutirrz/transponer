/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import una.cr.transponer.dao.Dao;

/**
 *
 * @author Andrés Gutiérrez
 */
public class ConexionLoader {

    public String puerto;
    public String host;
    public String baseDatos;
    public String user;
    public String password;
    //
    public String tablaEvaluaciones;
    public String tablaProfesores;
    public String tablaCursos;
    public String tablaPeriodo;

    private static ConexionLoader singleton = null;

    private ConexionLoader() {
        cargar();
        //this.guardar();
    }

    public static ConexionLoader getInstance() {
        if (singleton == null) {
            singleton = new ConexionLoader();
        }
        return singleton;
    }

    private void cargar() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            //input = new FileInputStream("parametros.properties");
            String filename = "/configuraciones.properties";
            //properties.load(MyClass.class.getResourceAsStream("/config.properties"));
            input = ConexionLoader.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.host = prop.getProperty("host", "localhost");
            this.puerto = prop.getProperty("puerto", "3306");
            this.baseDatos = prop.getProperty("baseDatos", "transponer");
            this.user = prop.getProperty("user", "root");
            this.password = prop.getProperty("password", "root");
            //
            this.tablaEvaluaciones = prop.getProperty("tablaEvaluaciones", "saturn_svbtesd");;
            this.tablaProfesores = prop.getProperty("tablaProfesores", "saturn_spriden1");;
            this.tablaCursos = prop.getProperty("tablaCursos", "saturn_scbcrse");;
            this.tablaPeriodo = prop.getProperty("tablaPeriodo", "saturn_ssbsect");;


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void guardar() {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("config_propiedades/configuraciones.properties");

            // set the properties value
            prop.setProperty("database", "localhost");
            prop.setProperty("dbuser", "mkyong");
            prop.setProperty("dbpassword", "password");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
