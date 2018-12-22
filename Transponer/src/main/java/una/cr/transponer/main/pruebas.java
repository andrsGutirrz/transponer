/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.main;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
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

        Dao dao = Dao.getInstance();
       
        System.out.println("El sha1 : " + Hashing.sha1().hashString("andres", Charsets.UTF_8).toString());
    }

}
