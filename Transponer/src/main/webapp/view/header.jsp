<%-- 
    Document   : header
    Created on : Dec 3, 2018, 2:49:23 PM
    Author     : Andrés Gutiérrez
--%>

<%@page import="una.cr.transponer.model.Usuario"%>
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
                <li class="nav-item">
                    <a class="nav-link" href="/Transponer/index">Incio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/Transponer/transponer">Transponer</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/Transponer/consultar">Consultar</a>
                </li>
            </ul>
        </div>
        <% Usuario l = (Usuario) session.getAttribute("usuario");%>
        <div class="row">
            <div class="col-md-12">
                <span>Usuario:  <%= l.username%> </span>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <a href="logout">&nbsp;&nbsp;Salir</a>
            </div>
        </div>
    </nav>
</div>

<!-- Transponer/view/transponer.jsp -->