<%-- 
    Document   : index
    Created on : Nov 19, 2018, 12:12:21 PM
    Author     : Guti
--%>

<%@page import="una.cr.transponer.model.TablaGenerada"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="errorBorrar" scope="request" class="java.lang.String"/>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="includes.jsp" %>
        <jsp:useBean id="tablas" scope="request" type="ArrayList<TablaGenerada>" class="java.util.ArrayList"/>
        <jsp:useBean id="datos" scope="request" type="ArrayList<String>" class="java.util.ArrayList"/>
        <jsp:useBean id="columnas" scope="request" type="ArrayList<String>" class="java.util.ArrayList"/>
        <link rel="stylesheet" type="text/css" href="/Transponer/css/datatables.css">
        <link rel="stylesheet" type="text/css" href="/Transponer/css/buttons.dataTables.css">
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
                                    <%for (TablaGenerada g : tablas) {%>
                                    <option><%= g.getNombre() + " | " + g.getFecha()%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-primary">Buscar</button>
                            </div>
                        </div>
                    </form> 
                    <!-- -->
                    <hr>
                    <p>Eliminar Tabla</p>
                    <form action="/Transponer/eliminar-tabla" method="post">
                        <div class="form-row">
                            <div class="form-group col-md-8">
                                <select id="tablas" class="form-control" name="tablas">
                                    <option selected>Escoger...</option>
                                    <%for (TablaGenerada g : tablas) {%>
                                    <option><%= g.getNombre() + " | " + g.getFecha()%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-primary">Eliminar</button>
                            </div>
                        </div>
                    </form> 
                </div>

                <div class="resultados">
                    <div class="mensajes">
                        <%if (!errorBorrar.isEmpty()) {%>
                        <div class="card" id="mensajeRespuesta">
                            <div class="card-header bg-secondary ">
                                Alerta!
                            </div>
                            <div class="card-body">
                                <p> <%= errorBorrar %> </p>
                            </div>
                        </div>
                        <%}%>
                    </div>
                    <%if (datos.size() != 0) {%>
                    <table class="table table-striped" id="tableResultados">
                        <thead class=""> <!-- thead-dark -->
                            <tr>                                
                                <%for (int i = 0; i < columnas.size(); i++) {%>
                                <th scope="col"><%=columnas.get(i)%></th>
                                    <%}%>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (int j = 0; j < datos.size(); j++) {%>
                            <tr>
                                <%for (int k = 0; k < columnas.size(); k++) {%>
                                <td><%=datos.get(j++)%></td>
                                <%}%>
                                <%j--;%>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <%}%>
                </div>
            </div>
            <%@ include file="footer.jsp" %>
        </div> <!-- Container -->
        <%@ include file="includeJQUERY.jsp" %>
        <script src="/Transponer/js/consultar.js"></script>
    </body>
</html>
