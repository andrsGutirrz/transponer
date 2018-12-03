<%-- 
    Document   : index
    Created on : Nov 19, 2018, 12:12:21 PM
    Author     : Guti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <form>
                        <div class="form-row">
                            <div class="form-group col-md-7">
                                <label for="inputCity">Nombre de la tabla</label>
                                <input type="text" class="form-control" id="inputCity">
                            </div>
                            <div class="form-group col-md-5">
                                <label for="inputState">Instrumento</label>
                                <select id="inputState" class="form-control">
                                    <option selected>Escoger...</option>
                                    <option>POSGR-03</option>
                                    <option>ECIDEA18</option>
                                    <option>EDDECEG3</option>
                                </select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Generar tabla</button>
                    </form>
                </div>                    
            </div>
            <footer class="footer">

            </footer>
        </div> <!-- Container -->
        <script src="/Transponer/js/jquery-3.3.1.min.js"></script>
    </body>
</html>
