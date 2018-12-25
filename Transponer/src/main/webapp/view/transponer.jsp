<%-- 
    Document   : index
    Created on : Nov 19, 2018, 12:12:21 PM
    Author     : Guti
--%>

<%@page import="una.cr.transponer.model.Mensaje"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="mensaje" scope="request" class="una.cr.transponer.model.Mensaje"/>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="includes.jsp" %>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="contenido">
            <div class="formulario ctrHor">
                <form action="/Transponer/make-transponer" method="post" id="formTransponer">
                    <div class="form-row">
                        <div class="form-group col-md-7">
                            <label for="nombreTabla" id="labelNombreTabla" class="">Nombre de la tabla</label>
                            <input type="text" class="form-control" id="nombreTabla" name="nombreTabla">
                        </div>
                        <div class="form-group col-md-5">
                            <label for="instrumentos" id="labelInstrumentos">Instrumento</label>
                            <select id="instrumentos" class="form-control" name="instrumentos">
                                <option selected>Escoger...</option>
                                <option>POSGR-03</option>
                                <option>ECIDEA18</option>
                                <option>EDDECEG3</option>
                                <option>EDDLAB18</option>
                                <option>EDDMVLC5</option>
                                <option>EDDMVCC5</option>
                                <option>EVDBIM18</option>
                                <option>IGENER18</option>
                            </select>
                        </div>
                    </div>
                    <button id="enviar" type="submit" class="btn btn-primary">Generar tabla</button>
                </form>
            </div> 
            <%if (!mensaje.getMensaje().equals("-1")) {%>
            <div class="card" id="mensajeRespuesta">
                <div class="card-header bg-secondary">
                    Alerta!
                </div>
                <div class="card-body">
                    <h5 class="card-title"><%= mensaje.getMensaje()%></h5>
                    <p>Nombre tabla: <%= mensaje.getNombreTabla()%></p>
                    <p>Instrumento: <%= mensaje.getInstrumento()%></p>
                </div>
            </div>
            <%}%>
        </div> <!-- FIN CONTENDIO -->
        <%@ include file="footer.jsp" %>
        <script src="/Transponer/js/jquery-3.3.1.min.js"></script>
        <script src="/Transponer/js/transponer.js"></script>
    </body>
</html>
