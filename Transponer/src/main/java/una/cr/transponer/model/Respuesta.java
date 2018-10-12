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


public class Respuesta {
    
    //variables
    private String codigo;
    private String respuesta;

    public Respuesta() {
    }

    public Respuesta(String codigo, String respuesta) {
        this.codigo = codigo;
        this.respuesta = respuesta;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "Respuesta{" + "codigo=" + codigo + ", respuesta=" + respuesta + '}';
    }

    
}
