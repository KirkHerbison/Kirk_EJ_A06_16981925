<%-- 
    Document   : login
    Created on : Nov 3, 2022, 9:57:49 AM
    Author     : Kirk Herbison
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:import url="includes/header.jsp">
    <c:param name="title" value="Register"/>
    <c:param name="css" value="results.css"/>
</c:import>
<link href="<c:url value='js/jquery-ui-1.12.1.custom/jquery-ui.min.css'/>" rel="stylesheet"/>
<script defer src="<c:url value='js/register.js'/>"></script>
        <main>
            <form action="authenticate" method="POST">
                <input type="hidden" name="requestAction" value="login">   
                <fieldset>
                    <legend>Login Credentials</legend>
                    <div class="user_input">
                        <div class = "input_group">
                            <label class="parent">Username: </label>
                            <input type="text" required name="username" minlength="6"
                                value="<c:out value='${username}'/>">
                        </div>
                    </div>
                    <br>
                    <div class="user_input">
                        <div class = "input_group">
                            <label class="parent">Password: </label>
                            <input type="password" required id="password" name="password" minlength="1">
                        </div>                        
                    </div>
                    <div class="user_input">
                        <div class="left_button"></div>
                        <input type="submit" value="login"/> 
                    </div>
                </fieldset>
            </form>              
        </main>
    <c:import url="includes/footer.jsp">
        <c:param name="page" value="register.jsp"/>
    </c:import>
