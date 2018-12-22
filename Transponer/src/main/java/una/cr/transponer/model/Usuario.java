/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.model;

/**
 *
 * @author Andrés Gutiérrez
 */

public class Usuario {

    public int id;
    public String username;
    public String clave;
    public boolean activo;

    public Usuario() {
    }

    public Usuario(int id, String username, String clave, boolean activo) {
        this.id = id;
        this.username = username;
        this.clave = clave;
        this.activo = activo;
    }

}
