<%-- 
    Document   : index
    Created on : Nov 19, 2018, 12:12:21 PM
    Author     : Guti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="mensaje" scope="request" class="java.lang.String" />
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="includes.jsp" %>
    </head>
    <body>
        <div class="">   
            <%@ include file="header.jsp" %>
            <div class="contenido">
                <div class="formulario">
                    <form action="/Transponer/make-transponer" method="post">
                        <div class="form-row">
                            <div class="form-group col-md-7">
                                <label for="nombreTabla">Nombre de la tabla</label>
                                <input type="text" class="form-control" id="nombreTabla" name="nombreTabla">
                            </div>
                            <div class="form-group col-md-5">
                                <label for="instrumentos">Instrumento</label>
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
                        <button type="submit" class="btn btn-primary">Generar tabla</button>
                    </form>
                </div> 
                <div class="card" id="mensajeRespuesta">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Special title treatment</h5>
                        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                        <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>
                <%if (mensaje != null) {%>
                <h1><%= mensaje%></h1>
                <%}%>
            </div>
            <footer class="footer">

            </footer>
        </div> <!-- Container -->
        <script src="/Transponer/js/jquery-3.3.1.min.js"></script>
    </body>
</html>


<script>
// A $( document ).ready() block.
$( document ).ready(function() {
    console.log( "ready!" );
});    
</script>