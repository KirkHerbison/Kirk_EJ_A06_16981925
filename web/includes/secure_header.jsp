<%-- 
    Document   : header
    Created on : Feb 3, 2022, 8:39:27 AM
    Author     : Kirk Herbison
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link href="<c:url value='styles/main.css' />" rel="stylesheet"  />
        <link href="<c:url value='styles/header.css' />" rel="stylesheet"  />
        <link href="<c:url value='styles/footer.css' />" rel="stylesheet"  />
        <link href="<c:url value='styles/${param.css}' />" rel="stylesheet"  />
        <link rel="stylesheet" href="<c:url value='/styles/slicknav.css' />">
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="<c:url value='/js/jquery-ui-1.12.1.custom/jquery-ui.min.js'/>"></script>
        <script src="<c:url value='/js/jquery.slicknav.min.js'/>"></script>
        <script>
            $(document).ready(function(){
                $('#nav_menu').slicknav({prependTo:"#mobile_menu"});
            });
        </script>
        <title>${param.title}</title>
    </head>
    <body>
        <header>
            <h1>Reliable Vehicle Rental - Employees Only</h1>
            <nav id="mobile_menu"></nav>
            <nav id="nav_menu">
                <ul>
                    <li><a href="<c:url value='/admin?requestAction=index'/>">Home</a></li>
                    <li><a href="<c:url value='/admin?requestAction=employee'/>">Employee</a></li>
                    <li><a href="<c:url value='/authenticate?requestAction=logout'/>">Logout</a></li>
                </ul>
            </nav>
        </header>
