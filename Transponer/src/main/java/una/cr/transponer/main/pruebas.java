/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.main;

import java.util.ArrayList;
import una.cr.transponer.dao.Dao;
import una.cr.transponer.model.TablaGenerada;

/**
 *
 * @author Guti
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Dao dao =  Dao.getInstance();
        
        ArrayList<String> ls = dao.obtenerDatosTabla("ricardo");    
        for (int i = 0; i < 40; i++) {
            System.out.print(ls.get(i)+" ");
        }

    }
    
}
