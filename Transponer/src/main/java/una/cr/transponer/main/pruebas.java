/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.main;

import java.util.ArrayList;
import una.cr.transponer.dao.Dao;

/**
 *
 * @author Guti
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Dao dao = new Dao();
        
        ArrayList<String> ls = dao.listaNombreTablas();
        
        for (int i = 0; i < ls.size(); i++) {
            System.out.println(ls.get(i));
        }
        
    }
    
}
