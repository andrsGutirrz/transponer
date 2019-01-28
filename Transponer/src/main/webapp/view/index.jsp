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
        <div class="contenedor">   
            <%@ include file="header.jsp" %>

            <div class="contenido">

                <div class="cter">
                    <div class="row">
                        <div class="col-md-12">
                            <h1>Bienvenido</h1>
                            <h2><%= l.username%> </h2>
                        </div>
                    </div>
                </div>
                <div class="bienvenida ctrHor">
                    <p>
                        Transponer es un sistema informatico para el manejo de las evaluaciones academicas<br>
                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been 
                        the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley
                        of type and scrambled it to make a type specimen book. It has survived not only five centuries, 
                        but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised 
                        in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently 
                        with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                    </p>
                </div>

            </div>

            <%@ include file="footer.jsp" %>

        </div> <!-- Container -->
        <script src="/Transponer/js/jquery-3.3.1.min.js"></script>
    </body>
</html>

<style>

    .cter{
        text-align: center;
        margin-top: 30px;
    }

</style>