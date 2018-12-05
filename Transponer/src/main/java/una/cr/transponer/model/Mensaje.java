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

public class Mensaje implements java.io.Serializable {
    
    String mensaje;
    String nombreTabla;
    String instrumento;

    public Mensaje(String mensaje, String nombreTabla, String instrumento) {
        this.mensaje = mensaje;
        this.nombreTabla = nombreTabla;
        this.instrumento = instrumento;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "mensaje=" + mensaje + ", nombreTabla=" + nombreTabla + ", instrumento=" + instrumento + '}';
    }
       
}
