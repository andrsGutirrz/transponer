<%-- 
    Document   : header
    Created on : Dec 3, 2018, 2:49:23 PM
    Author     : Andrés Gutiérrez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="header">                           
    <div class="backg-logo">   
        <img class="img-fluid" src="/Transponer/images/logo.png" alt="logo-vicerrectoria" id="logo">  
        <h1>Transponer</h1>
    </div>
    <nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/Transponer/view/index.jsp">Incio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/Transponer/view/transponer.jsp">Transponer</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/Transponer/view/consultar.jsp">Consultar</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
