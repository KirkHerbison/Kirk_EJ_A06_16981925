<%-- 
    Document   : results
    Created on : Feb 3, 2022, 10:12:57 AM
    Author     : Kirk Herbison
    @author Kirk Herbison
    Kirk_BJ_A03_B_16981925
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import url="includes/header.jsp">
    <c:param name="title" value="Page Not Found"/>
    <c:param name="css" value="error_404.css"/>
</c:import>
        <main>
            <div>
                <h1>Oops! Page Not Found!</h1>
                <br>
                <p>The page you are looking for cannot be found. please contact
                the web administrator so the issue can be fixed.</p>
            </div>
        </main>
    <c:import url="includes/footer.jsp">
        <c:param name="page" value="error_404.jsp"/>
    </c:import>
