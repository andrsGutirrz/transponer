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
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../css/general.css">
        <title>Evaluacion Docente</title>
    </head>
    <body>
        <div class="container">   
            <div class="header">                           
                <div class="backg-logo">   
                    <img class="img-fluid" src="../images/logo.png" alt="logo-vicerrectoria" id="logo">  
                    <h1>Evaluacion Docente</h1>
                </div>
                <nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarText">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="#">Incio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Transponer</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Consultar</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="contenido">
                <div class="formulario">
                    <form>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputCity">Nombre de la tabla</label>
                                <input type="text" class="form-control" id="inputCity">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="inputState">Instrumento</label>
                                <select id="inputState" class="form-control">
                                    <option selected>Escoger...</option>
                                    <option>POSGR-03</option>
                                    <option>ECIDEA18</option>
                                    <option>EDDECEG3</option>
                                </select>
                            </div>
                            <div class="form-group col-md-2">
                                <label for="inputZip">Zip</label>
                                <input type="text" class="form-control" id="inputZip">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Generar tabla</button>
                    </form>
                </div>                    
            </div>
            <footer class="">

            </footer>
        </div> <!-- Container -->
        <script src="../js/jquery-3.3.1.min"></script>
    </body>
</html>
