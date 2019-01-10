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
    //nuevas columnas
    String nombreProfesor;
    String cedulaProfesor;
    String nombreCurso;
    String escuela;
    String facultad;
    String cupo;
    String matricula;
    String codigoCurso;
    String campus;

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public ColsFijas(String encuesta, String ciclo, String crn, String pidm, String tssc) {
        this.encuesta = encuesta;
        this.ciclo = ciclo;
        this.crn = crn;
        this.pidm = pidm;
        this.tssc = tssc;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getCedulaProfesor() {
        return cedulaProfesor;
    }

    public void setCedulaProfesor(String cedulaProfesor) {
        this.cedulaProfesor = cedulaProfesor;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getCupo() {
        return cupo;
    }

    public void setCupo(String cupo) {
        this.cupo = cupo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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
        return "ColsFijas{" + "encuesta=" + encuesta + ", ciclo=" + ciclo + ", crn=" + crn + ", pidm=" + pidm + ", tssc=" + tssc + ", nombreProfesor=" + nombreProfesor + ", cedulaProfesor=" + cedulaProfesor + ", nombreCurso=" + nombreCurso + ", escuela=" + escuela + ", facultad=" + facultad + ", cupo=" + cupo + ", matricula=" + matricula + ", codigoCurso=" + codigoCurso + ", campus=" + campus + '}';
    }


    
    
}
