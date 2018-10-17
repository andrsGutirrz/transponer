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
public class ColsFijas {
    String encuesta;
    String ciclo;
    String crn;
    String pidm;
    String tssc;

    public ColsFijas(String encuesta, String ciclo, String crn, String pidm, String tssc) {
        this.encuesta = encuesta;
        this.ciclo = ciclo;
        this.crn = crn;
        this.pidm = pidm;
        this.tssc = tssc;
    }

    public ColsFijas() {
    }

    public String getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(String encuesta) {
        this.encuesta = encuesta;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getPidm() {
        return pidm;
    }

    public void setPidm(String pidm) {
        this.pidm = pidm;
    }

    public String getTssc() {
        return tssc;
    }

    public void setTssc(String tssc) {
        this.tssc = tssc;
    }

    @Override
    public String toString() {
        return "ColsFijas{" + "encuesta=" + encuesta + ", ciclo=" + ciclo + ", crn=" + crn + ", pidm=" + pidm + ", tssc=" + tssc + '}';
    }
    
    
    
}
