<%-- 
    Document   : index
    Created on : Nov 19, 2018, 12:12:21 PM
    Author     : Guti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/Transponer/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/Transponer/css/general.css">
        <title>Login</title>
    </head>
    <body>
        <div class="header">                           
            <div class="backg-logo">   
                <img class="img-fluid" src="../images/logo.png" alt="logo-vicerrectoria" id="logo">  
                <h1>Transponer</h1>
            </div>
        </div>
        <div class="contenidoLogin">   

            <div class="card cardLogin text-white">
                <div class="card-header">
                    Inicio de sesión 
                </div>
                <div class="card-body">
                    <div class="loginform">
                        <form action="/Transponer/LoginTransponer" method="post">
                            <div class="form-group">
                                <label for="username">Nombre de usuario</label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="Ingrese su nombre de usuario">
                            </div>
                            <div class="form-group">
                                <label for="pass">Contraseña</label>
                                <input type="password" class="form-control" id="pass" name="pass" placeholder="Ingrese su contraseña">
                            </div>
                            <button type="submit" class="btn btn-primary">Ingresar</button>
                        </form>
                    </div>      
                </div>
            </div>
        </div> <!-- Container -->
        <footer class="footer">
        </footer>
        <script src="../js/jquery-3.3.1.min"></script>
    </body>
</html>
