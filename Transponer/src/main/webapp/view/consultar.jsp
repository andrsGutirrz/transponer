<%-- 
    Document   : index
    Created on : Nov 19, 2018, 12:12:21 PM
    Author     : Guti
--%>

<%@page import="una.cr.transponer.model.TablaGenerada"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="includes.jsp" %>
        <jsp:useBean id="tablas" scope="request" type="ArrayList<TablaGenerada>" class="java.util.ArrayList"/>
    </head>
    <body>
        <div class="contenedor">   
            <%@ include file="header.jsp" %>
            <div class="contenido">
                <div class="consultaForm">
                    <p>Buscar Tabla</p>
                    <form action="/Transponer/buscar-tabla" method="post">
                        <div class="form-row">
                            <div class="form-group col-md-8">
                                <select id="tablas" class="form-control" name="tablas">
                                    <option selected>Escoger...</option>
                                    <%for(TablaGenerada g : tablas){%>
                                    <option><%= g.getNombre() + " | " + g.getFecha()%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-primary">Buscar</button>
                            </div>
                        </div>
                    </form> 
                </div>
                <div class="resultados">
                    <p>Mundo</p>
                </div>
            </div>
            <footer class="footer">
            </footer>
        </div> <!-- Container -->
        <script src="/Transponer/js/jquery-3.3.1.min.js"></script>
    </body>
</html>
