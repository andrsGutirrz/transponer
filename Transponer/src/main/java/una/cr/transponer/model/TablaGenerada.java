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
public class TablaGenerada {

    String fecha;
    String nombre;

    public TablaGenerada(String nombre, String fecha) {
        this.fecha = fecha;
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TablaGenerada{" + "fecha=" + fecha + ", nombre=" + nombre + '}';
    }

}
